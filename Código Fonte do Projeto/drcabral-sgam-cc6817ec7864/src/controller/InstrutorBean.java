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
import models.EnderecoPessoa;
import models.Instrutor;
import models.InstrutorModalidade;
import models.InstrutorModalidadeUnidade;
import models.LogAcesso;
import models.Modalidade;
import models.TelefonePessoa;
import models.Unidade;
import business.AcessoBusiness;
import business.EnderecoPessoaBusiness;
import business.InstrutorBusiness;
import business.InstrutorModalidadeBusiness;
import business.InstrutorModalidadeUnidadeBusiness;
import business.LogAcessoBusiness;
import business.TelefonePessoaBusiness;

@ManagedBean(name = "instrutor")
@SessionScoped
public class InstrutorBean {

	@ManagedProperty(value="#{login}")
	private LoginBean loginBean;

	private Instrutor instrutor = null;
	private EnderecoPessoa endereco = null;
	private List<TelefonePessoa> telefones = null;
	private TelefonePessoa telefone = null;
	private DataModel<Instrutor> model;
	private DataModel<InstrutorModalidadeUnidade> modelImus;
	private InstrutorModalidadeUnidade instrutorModalidadeUnidade; //para o editar da visualização das modalidades
	private int idModalidadePesquisa;

	@PostConstruct
	public void init() {
		if (instrutor == null) {
			instrutor = new Instrutor();
			endereco = new EnderecoPessoa();
			telefones = new ArrayList<TelefonePessoa>();
			telefone = new TelefonePessoa();
			instrutorModalidadeUnidade = new InstrutorModalidadeUnidade();
		}
	}

	public Instrutor getInstrutor() {
		return instrutor;
	}

	public void setInstrutor(Instrutor instrutor) {
		this.instrutor = instrutor;
	}

	public void atualizarInstrutor(){
		EnderecoPessoaBusiness epB = new EnderecoPessoaBusiness();
		epB.update(instrutor.getEndereco());

		InstrutorBusiness iB = new InstrutorBusiness();
		iB.update(instrutor);

		//inserindo na tabela de log
		AcessoBusiness aB = new AcessoBusiness();
		Acesso acesso = new Acesso();
		acesso.setDataAcesso(new Date(System.currentTimeMillis()));
		acesso.setTipo("Atualização");
		acesso.setNomeTabelaAfetada("Instrutor");
		aB.salvar(acesso);

		LogAcessoBusiness laB = new LogAcessoBusiness();
		LogAcesso la = new LogAcesso();
		la.setAcesso(acesso);
		la.setUsuario(loginBean.getUsuario());
		laB.salvar(la);

		//zerando o bean
		instrutor = new Instrutor();
		endereco = new EnderecoPessoa();
		telefones = new ArrayList<TelefonePessoa>();
		telefone = new TelefonePessoa();
		instrutorModalidadeUnidade = new InstrutorModalidadeUnidade();

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("listarInstrutor.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cadastrarInstrutor(){

		cadastrarEndereco();

		instrutor.setEndereco(endereco);
		InstrutorBusiness instrutorBusiness = new InstrutorBusiness();
		instrutorBusiness.salvar(instrutor);

		cadastrarTelefones();

		//inserindo na tabela de log
		AcessoBusiness aB = new AcessoBusiness();
		Acesso acesso = new Acesso();
		acesso.setDataAcesso(new Date(System.currentTimeMillis()));
		acesso.setTipo("Cadastro");
		acesso.setNomeTabelaAfetada("Instrutor");
		aB.salvar(acesso);

		LogAcessoBusiness laB = new LogAcessoBusiness();
		LogAcesso la = new LogAcesso();
		la.setAcesso(acesso);
		la.setUsuario(loginBean.getUsuario());
		laB.salvar(la);

		//zerando o bean
		instrutor = new Instrutor();
		endereco = new EnderecoPessoa();
		telefones = new ArrayList<TelefonePessoa>();
		telefone = new TelefonePessoa();
		instrutorModalidadeUnidade = new InstrutorModalidadeUnidade();

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("listarInstrutor.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cadastrarTelefones(){
		TelefonePessoaBusiness telefoneUnidadeBusiness = new TelefonePessoaBusiness();
		telefone.setPessoa(instrutor);
		telefoneUnidadeBusiness.salvar(telefone);

	}

	public void cadastrarEndereco(){
		EnderecoPessoaBusiness enderecoBusiness = new EnderecoPessoaBusiness();
		enderecoBusiness.salvar(endereco);
	}

	public void addTelefoneEditar(){
		telefone.setPessoa(instrutor);
		telefones.add(telefone);

		//salvando o novo telefone no banco
		TelefonePessoaBusiness tpB = new TelefonePessoaBusiness();
		tpB.salvar(telefone);

		//zerando o bean
		telefone = new TelefonePessoa();
	}

	public String removerTelefone(){
		for (int i = 0; i < telefones.size(); i++){
			if (telefones.get(i).getNumero().equals(telefone.getNumero())){
				telefones.remove(i);
				i--;
			}
		}

		TelefonePessoaBusiness tpB = new TelefonePessoaBusiness();
		tpB.deletar(telefone);

		//zerando o bean
		telefone = new TelefonePessoa();

		return "";
	}

	public String editarIMU(){
		return "editarInstrutorModalidadeUnidade";
	}

	public String editar(){
		return "editarInstrutor?faces-redirect=true";
	}

	public String verModalidades(){
		return "modalidadesInstrutor?faces-redirect=true";
	}

	public String verTelefones(){
		return "telefonesInstrutor?faces-redirect=true";
	}

	public String voltarListarInstrutor(){
		return "listarInstrutor?faces-redirect=true";
	}

	public DataModel<InstrutorModalidadeUnidade> getTodosModalidadeInstrutor() throws Exception {
		InstrutorModalidadeUnidadeBusiness imuB = new InstrutorModalidadeUnidadeBusiness();
		Unidade unidade= new Unidade();
		unidade.setNome(loginBean.getNomeUnidade());
		ArrayList<InstrutorModalidadeUnidade> imus = new ArrayList<InstrutorModalidadeUnidade>();
		imus = (ArrayList<InstrutorModalidadeUnidade>) imuB.listar(unidade); //todos os instrutoresModalidadeUnidade

		//eliminando objetos que tem o instrutor diferente do selecionado
		for (int i = 0; i < imus.size(); i++) {
			if(!imus.get(i).getInstrutorModalidade().getInstrutor().getCpf().equals(instrutor.getCpf())){
				imus.remove(i);
				i--;
			}
		}

		modelImus = new ListDataModel<InstrutorModalidadeUnidade>(imus);
		return modelImus;
	}

	public List<InstrutorModalidade> getTodosInstrutorModalidade(){
		InstrutorModalidadeBusiness imB = new InstrutorModalidadeBusiness();
		return imB.listar();
	}

	public List<TelefonePessoa> getTodosTelefones(){
		TelefonePessoaBusiness tpB = new TelefonePessoaBusiness();
		ArrayList<TelefonePessoa> retorno = (ArrayList<TelefonePessoa>) tpB.listar(instrutor);
		instrutor.setTelefones(retorno);
		telefones = retorno;
		return retorno;
	}

	public DataModel<Instrutor> getTodos() throws Exception {

		if(idModalidadePesquisa != 0){
			InstrutorModalidadeBusiness imB = new InstrutorModalidadeBusiness();
			Modalidade modalidade = new Modalidade();
			modalidade.setIdModalidade(idModalidadePesquisa);
			ArrayList<InstrutorModalidade> instrutoresModalidade = (ArrayList<InstrutorModalidade>) imB.listar(modalidade);
			ArrayList<Instrutor> instrutores = new ArrayList<Instrutor>();
			for (int i = 0; i < instrutoresModalidade.size(); i++) {
				instrutores.add(instrutoresModalidade.get(i).getInstrutor());
			}
			model = new ListDataModel<Instrutor>(instrutores);
		}else{
			InstrutorBusiness iB = new InstrutorBusiness();
			model = new ListDataModel<Instrutor>(iB.listar());
		}

		return model;
	}

	public EnderecoPessoa getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoPessoa endereco) {
		this.endereco = endereco;
	}

	public List<TelefonePessoa> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<TelefonePessoa> telefones) {
		this.telefones = telefones;
	}

	public TelefonePessoa getTelefone() {
		return telefone;
	}

	public void setTelefone(TelefonePessoa telefone) {
		this.telefone = telefone;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public DataModel<Instrutor> getModel() {
		return model;
	}

	public void setModel(DataModel<Instrutor> model) {
		this.model = model;
	}

	public DataModel<InstrutorModalidadeUnidade> getModelImus() {
		return modelImus;
	}

	public int getIdModalidadePesquisa() {
		return idModalidadePesquisa;
	}

	public void setIdModalidadePesquisa(int idModalidadePesquisa) {
		this.idModalidadePesquisa = idModalidadePesquisa;
	}

	public void setModelImus(DataModel<InstrutorModalidadeUnidade> modelImus) {
		this.modelImus = modelImus;
	}

	public InstrutorModalidadeUnidade getInstrutorModalidadeUnidade() {
		return instrutorModalidadeUnidade;
	}

	public void setInstrutorModalidadeUnidade(
			InstrutorModalidadeUnidade instrutorModalidadeUnidade) {
		this.instrutorModalidadeUnidade = instrutorModalidadeUnidade;
	}
	
	public String gerarInstrutor() {
		try {
			Unidade u = new Unidade();
			u.setNome(loginBean.getNomeUnidade());
			
			InstrutorModalidadeUnidadeBusiness um = new InstrutorModalidadeUnidadeBusiness();
			ArrayList<InstrutorModalidadeUnidade> instrutorModalidadeUnidade = (ArrayList<InstrutorModalidadeUnidade>) um.listarPorSalario(u);
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("date", new Date());
			params.put(JRParameter.REPORT_LOCALE, new Locale("pt","BR"));

			//recebe o cmainho do esquema de relatorio escolhido 
			JasperReport report = JasperCompileManager.compileReport(
					((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("/resources/modelos/InstrutorSalario.jrxml"));  

			//recebe a lista
			JasperPrint print = JasperFillManager.fillReport(report, params, new JRBeanCollectionDataSource(instrutorModalidadeUnidade)); 

			JasperExportManager.exportReportToPdfFile(print, 
					((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("/resources/relatorios/InstrutorPorSalario.pdf"));

			
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "pdfInstrutores.xhtml";
	}
}
