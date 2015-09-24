package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import models.Acesso;
import models.Instrutor;
import models.InstrutorModalidade;
import models.InstrutorModalidadeUnidade;
import models.LogAcesso;
import models.Modalidade;
import models.Unidade;
import models.UnidadeModalidade;
import business.AcessoBusiness;
import business.InstrutorBusiness;
import business.InstrutorModalidadeBusiness;
import business.InstrutorModalidadeUnidadeBusiness;
import business.LogAcessoBusiness;
import business.ModalidadeBusiness;
import business.UnidadeModalidadeBusiness;

@ManagedBean(name = "modalidade")
@SessionScoped
public class ModalidadeBean {

	@ManagedProperty(value="#{login}")
	private LoginBean loginBean;

	private Modalidade modalidade = null;
	private UnidadeModalidade unidadeModalidade = null;
	private InstrutorModalidade instrutorModalidade = null;
	private InstrutorModalidadeUnidade instrutorModalidadeUnidade = null;
	private List<String> instrutoresCpf = null;
	private String instrutorCpf = null;
	private String nomeModalidade = null;
	private DataModel<UnidadeModalidade> model;
	private DataModel<Instrutor> modelInstrutor;
	private List<Instrutor> listaInstrutores;
	private Instrutor instrutorDaModalidade;

	@PostConstruct
	public void init() {
		if (modalidade == null) {
			modalidade = new Modalidade();
			unidadeModalidade = new UnidadeModalidade();
			instrutorModalidade = new InstrutorModalidade();
			instrutoresCpf = new ArrayList<String>();
			instrutorCpf = "";
			nomeModalidade = "";
			instrutorModalidadeUnidade = new InstrutorModalidadeUnidade();
			listaInstrutores = new ArrayList<Instrutor>();
			instrutorDaModalidade = new Instrutor();
		}
	}

	public Modalidade getModalidade() {
		return modalidade;
	}

	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}

	public void cadastrarModalidade(){
		ModalidadeBusiness modalidadeBusiness = new ModalidadeBusiness();
		modalidadeBusiness.salvar(modalidade);

		cadastrarUnidadeModalidade();
		cadastrarInstrutorModalidade();
		cadastrarInstrutorModalidadeUnidade();

		//inserindo na tabela de log
		AcessoBusiness aB = new AcessoBusiness();
		Acesso acesso = new Acesso();
		acesso.setDataAcesso(new Date(System.currentTimeMillis()));
		acesso.setTipo("Cadastro");
		acesso.setNomeTabelaAfetada("Modalidade");
		aB.salvar(acesso);

		LogAcessoBusiness laB = new LogAcessoBusiness();
		LogAcesso la = new LogAcesso();
		la.setAcesso(acesso);
		la.setUsuario(loginBean.getUsuario());
		laB.salvar(la);

		//zerando o bean
		modalidade = new Modalidade();
		unidadeModalidade = new UnidadeModalidade();
		instrutorModalidade = new InstrutorModalidade();
		instrutoresCpf = new ArrayList<String>();
		instrutorCpf = "";
		nomeModalidade = "";
		instrutorModalidadeUnidade = new InstrutorModalidadeUnidade();
		listaInstrutores = new ArrayList<Instrutor>();

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("listarModalidade.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cadastrarInstrutorModalidadeUnidade(){
		InstrutorModalidadeUnidadeBusiness imuB = new InstrutorModalidadeUnidadeBusiness();
		Unidade unidade = new Unidade();
		unidade.setNome(loginBean.getNomeUnidade());
		instrutorModalidadeUnidade.setUnidade(unidade);
		instrutorModalidadeUnidade.setInstrutorModalidade(instrutorModalidade);
		imuB.salvar(instrutorModalidadeUnidade);
	}

	public void cadastrarInstrutorModalidade(){
		instrutorModalidade.setModalidade(modalidade);
		Instrutor instAux = new Instrutor();
		instAux.setCpf(instrutorCpf);
		instrutorModalidade.setInstrutor(instAux);
		InstrutorModalidadeBusiness imBusiness = new InstrutorModalidadeBusiness();
		imBusiness.salvar(instrutorModalidade);
	}

	public void cadastrarUnidadeModalidade(){
		Unidade unidadeAux = new Unidade();
		unidadeAux.setNome(loginBean.getNomeUnidade());
		unidadeModalidade.setUnidade(unidadeAux);
		unidadeModalidade.setModalidade(modalidade);
		UnidadeModalidadeBusiness umBusiness = new UnidadeModalidadeBusiness();
		umBusiness.salvar(unidadeModalidade);
	}

	public List<Instrutor> getTodosInstrutor(){
		InstrutorBusiness ib = new InstrutorBusiness();
		return ib.listar();
	}

	public DataModel<UnidadeModalidade> getTodos() throws Exception {
		UnidadeModalidadeBusiness umB = new UnidadeModalidadeBusiness();
		Unidade unidade= new Unidade();
		unidade.setNome(loginBean.getNomeUnidade());
		ArrayList<UnidadeModalidade> unidadeModalidades = new ArrayList<UnidadeModalidade>();
		unidadeModalidades = (ArrayList<UnidadeModalidade>) umB.listar(unidade);

		model = new ListDataModel<UnidadeModalidade>(unidadeModalidades);
		return model;
	}
	
	public List<UnidadeModalidade> getTodosModalidade() throws Exception {
		UnidadeModalidadeBusiness umB = new UnidadeModalidadeBusiness();
		Unidade unidade= new Unidade();
		unidade.setNome(loginBean.getNomeUnidade());
		ArrayList<UnidadeModalidade> unidadeModalidades = new ArrayList<UnidadeModalidade>();
		unidadeModalidades = (ArrayList<UnidadeModalidade>) umB.listar(unidade);
		return unidadeModalidades;
	}
	
	public String verInstrutores(){
		//PARA O VER INSTRUTORES USO UMA LISTA AO INVES DE DATAMODEL POR SER MAIS FACIL DE MANIPULAR
		listaInstrutoresModalidade();
		return "editarInstrutoresModalidade?faces-redirect=true";
	}

	public void listaInstrutoresModalidade(){
		InstrutorModalidadeUnidadeBusiness iumB = new InstrutorModalidadeUnidadeBusiness();
		ArrayList<InstrutorModalidadeUnidade> instrutoresFull = (ArrayList<InstrutorModalidadeUnidade>) iumB.listar();

		for (int i = 0; i < instrutoresFull.size(); i++) {
			if(instrutoresFull.get(i).getInstrutorModalidade().getModalidade().getIdModalidade() != unidadeModalidade.getModalidade().getIdModalidade()){
				instrutoresFull.remove(i);
				i--;
			}

		}

		ArrayList<Instrutor> instrutores = new ArrayList<Instrutor>();

		for (int i = 0; i < instrutoresFull.size(); i++) {
			instrutores.add(instrutoresFull.get(i).getInstrutorModalidade().getInstrutor());
		}

		listaInstrutores = instrutores;
	}

	public void addInstrutorEditar(){
		//parte do editar
		Instrutor aux = new Instrutor();
		aux.setCpf(instrutorCpf);
		InstrutorBusiness iB = new InstrutorBusiness();
		aux = iB.procurar(aux);
		boolean jaTem = false;

		//para nao adicionar o mesmo instrutor duas vezes
		for (int i = 0; i < listaInstrutores.size(); i++) {
			if(listaInstrutores.get(i).getCpf().equals(instrutorCpf)){
				jaTem = true;
			}
		}
		if(!jaTem){
			listaInstrutores.add(aux);
		}

		inserirNovosInstrutoresModalidade();

	}

	public void inserirNovosInstrutoresModalidade(){
		//verifica se há novos instrutores e os cadastra
		InstrutorModalidadeUnidadeBusiness iumB = new InstrutorModalidadeUnidadeBusiness();


		ArrayList<InstrutorModalidadeUnidade> instrutoresFull = (ArrayList<InstrutorModalidadeUnidade>) iumB.listar();

		boolean jaExiste = false;

		for (int i = 0; i < listaInstrutores.size(); i++) {
			for (int j = 0; j < instrutoresFull.size(); j++) {
				if(instrutoresFull.get(j).getInstrutorModalidade().getInstrutor().getCpf().equals(listaInstrutores.get(i).getCpf()) && instrutoresFull.get(j).getInstrutorModalidade().getModalidade().getIdModalidade() == unidadeModalidade.getModalidade().getIdModalidade() && instrutoresFull.get(j).getUnidade().getNome().equals(loginBean.getNomeUnidade())){
					jaExiste = true;
				}

			}

			if(!jaExiste){ //se ainda não existe, cadastra
				instrutorModalidade.setModalidade(unidadeModalidade.getModalidade());
				instrutorModalidade.setInstrutor(listaInstrutores.get(i));
				InstrutorModalidadeBusiness imBusiness = new InstrutorModalidadeBusiness();
				imBusiness.salvar(instrutorModalidade);

				//da pra usar esse metodo pq os parametros necessarios estão setados corretamente nesse momento
				cadastrarInstrutorModalidadeUnidade();
			}

			jaExiste = false;
		}

		//zerando o bean
		/*modalidade = new Modalidade();
		unidadeModalidade = new UnidadeModalidade();
		instrutorModalidade = new InstrutorModalidade();
		instrutoresCpf = new ArrayList<String>();
		instrutorCpf = "";
		listaInstrutores = new ArrayList<Instrutor>();*/
		instrutorModalidadeUnidade = new InstrutorModalidadeUnidade();


	}

	public String removerInstrutor(){
		for (int i = 0; i < listaInstrutores.size(); i++) {
			if(listaInstrutores.get(i).getCpf().equals(instrutorDaModalidade.getCpf())){
				listaInstrutores.remove(i);
			}
		}

		removerInstrutoresDaModalidade();


		return "ok";

	}

	public void removerInstrutoresDaModalidade(){
		InstrutorModalidadeUnidadeBusiness iumB = new InstrutorModalidadeUnidadeBusiness();
		ArrayList<InstrutorModalidadeUnidade> instrutoresFull = (ArrayList<InstrutorModalidadeUnidade>) iumB.listar();

		//remove os que foram retirados (de instrutorModalidade e InstrutorModalidadeUnidade)
		boolean foiRemovido = true;

		//restringe os InstrutoresModalidadesUnidades aos especificos da modalidade
		for (int i = 0; i < instrutoresFull.size(); i++) {
			if(instrutoresFull.get(i).getInstrutorModalidade().getModalidade().getIdModalidade() != unidadeModalidade.getModalidade().getIdModalidade()){
				instrutoresFull.remove(i);
				i--;
			}

		}

		for (int j = 0; j < instrutoresFull.size(); j++) {
			for (int i = 0; i < listaInstrutores.size(); i++) {
				if(instrutoresFull.get(j).getInstrutorModalidade().getInstrutor().getCpf().equals(listaInstrutores.get(i).getCpf()) && instrutoresFull.get(j).getInstrutorModalidade().getModalidade().getIdModalidade() == unidadeModalidade.getModalidade().getIdModalidade() && instrutoresFull.get(j).getUnidade().getNome().equals(loginBean.getNomeUnidade())){
					foiRemovido = false;
				}

			}

			if(foiRemovido){ //se o jovem foi removido da listaInstrutores, deve ser removido do banco para essa modalidade
				InstrutorModalidadeUnidadeBusiness iumB2 = new InstrutorModalidadeUnidadeBusiness();
				iumB2.deletar(instrutoresFull.get(j)); //deleto primeiro InstrutorModalidadeUnidade

				InstrutorModalidadeBusiness imBusiness2 = new InstrutorModalidadeBusiness();
				imBusiness2.deletar(instrutoresFull.get(j).getInstrutorModalidade());

			}

			foiRemovido = true;
		}

		//zerando o bean
		modalidade = new Modalidade();
		unidadeModalidade = new UnidadeModalidade();
		instrutorModalidade = new InstrutorModalidade();
		instrutoresCpf = new ArrayList<String>();
		instrutorCpf = "";
		instrutorModalidadeUnidade = new InstrutorModalidadeUnidade();

	}

	public void atualizarModalidade(){
		ModalidadeBusiness mB = new ModalidadeBusiness();
		mB.update(unidadeModalidade.getModalidade());

		UnidadeModalidadeBusiness umB = new UnidadeModalidadeBusiness();
		umB.update(unidadeModalidade);

		//inserindo na tabela de log
		AcessoBusiness aB = new AcessoBusiness();
		Acesso acesso = new Acesso();
		acesso.setDataAcesso(new Date(System.currentTimeMillis()));
		acesso.setTipo("Atualização");
		acesso.setNomeTabelaAfetada("Modalidade");
		aB.salvar(acesso);

		LogAcessoBusiness laB = new LogAcessoBusiness();
		LogAcesso la = new LogAcesso();
		la.setAcesso(acesso);
		la.setUsuario(loginBean.getUsuario());
		laB.salvar(la);

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("listarModalidade.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String editar(){
		//LEMBRAR QUE A TELA TA SELECIONANDO UNIDADEMODALIDADE
		return "editarModalidade?faces-redirect=true";
	}

	public String gerarModalidade() {
		try {
			Unidade u = new Unidade();
			u.setNome(loginBean.getNomeUnidade());
			
			UnidadeModalidadeBusiness um = new UnidadeModalidadeBusiness();
			ArrayList<UnidadeModalidade> unidadeModalidade = (ArrayList<UnidadeModalidade>) um.listarPorMensalidade(u);
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("date", new Date());
			params.put(JRParameter.REPORT_LOCALE, new Locale("pt","BR"));

			//recebe o cmainho do esquema de relatorio escolhido 
			JasperReport report = JasperCompileManager.compileReport(
					((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("/resources/modelos/ModalidadeMensalidade.jrxml"));  

			//recebe a lista
			JasperPrint print = JasperFillManager.fillReport(report, params, new JRBeanCollectionDataSource(unidadeModalidade)); 

			JasperExportManager.exportReportToPdfFile(print, 
					((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("/resources/relatorios/ModalidadePorMensalidade.pdf"));

		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "pdfModalidades.xhtml";
	}
	
	public void voltar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("relatorioAlunoModalidade.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public String listar(){
		return "listarModalidade?faces-redirect=true";
	}

	public UnidadeModalidade getUnidadeModalidade() {
		return unidadeModalidade;
	}

	public void setUnidadeModalidade(UnidadeModalidade unidadeModalidade) {
		this.unidadeModalidade = unidadeModalidade;
	}

	public InstrutorModalidade getInstrutorModalidade() {
		return instrutorModalidade;
	}

	public void setInstrutorModalidade(InstrutorModalidade instrutorModalidade) {
		this.instrutorModalidade = instrutorModalidade;
	}

	public List<String> getInstrutoresCpf() {
		return instrutoresCpf;
	}

	public void setInstrutoresCpf(List<String> instrutoresCpf) {
		this.instrutoresCpf = instrutoresCpf;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public String getInstrutorCpf() {
		return instrutorCpf;
	}

	public void setInstrutorCpf(String instrutorCpf) {
		this.instrutorCpf = instrutorCpf;
	}

	public InstrutorModalidadeUnidade getInstrutorModalidadeUnidade() {
		return instrutorModalidadeUnidade;
	}

	public void setInstrutorModalidadeUnidade(
			InstrutorModalidadeUnidade instrutorModalidadeUnidade) {
		this.instrutorModalidadeUnidade = instrutorModalidadeUnidade;
	}

	public DataModel<UnidadeModalidade> getModel() {
		return model;
	}

	public void setModel(DataModel<UnidadeModalidade> model) {
		this.model = model;
	}

	public List<Instrutor> getListaInstrutores() {
		return listaInstrutores;
	}

	public void setListaInstrutores(List<Instrutor> listaInstrutores) {
		this.listaInstrutores = listaInstrutores;
	}

	public DataModel<Instrutor> getModelInstrutor() {
		return modelInstrutor;
	}

	public Instrutor getInstrutorDaModalidade() {
		return instrutorDaModalidade;
	}

	public void setInstrutorDaModalidade(Instrutor instrutorDaModalidade) {
		this.instrutorDaModalidade = instrutorDaModalidade;
	}

	public void setModelInstrutor(DataModel<Instrutor> modelInstrutor) {
		this.modelInstrutor = modelInstrutor;
	}
	
	public String getNomeModalidade() {
		return nomeModalidade;
	}

	public void setNomeModalidade(String nomeModalidade) {
		this.nomeModalidade = nomeModalidade;
	}

	public List<Instrutor> listInstrutoresAdicionados(){
		ArrayList<Instrutor> instrutores = new ArrayList<Instrutor>();

		for (int i = 0; i < instrutoresCpf.size(); i++) {
			InstrutorBusiness iB = new InstrutorBusiness();
			Instrutor inst = new Instrutor();
			inst.setCpf(instrutoresCpf.get(i));
			instrutores.add(iB.procurar(inst));
		}

		return instrutores;
	}
}
