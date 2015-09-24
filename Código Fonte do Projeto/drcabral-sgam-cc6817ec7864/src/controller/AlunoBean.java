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

import models.Acesso;
import models.Aluno;
import models.EnderecoPessoa;
import models.InstrutorModalidade;
import models.InstrutorModalidadeAluno;
import models.InstrutorModalidadeUnidade;
import models.LogAcesso;
import models.Modalidade;
import models.Pagamento;
import models.TelefonePessoa;
import models.Unidade;
import models.UnidadeAluno;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.primefaces.event.FileUploadEvent;

import util.UploadFile;
import business.AcessoBusiness;
import business.AlunoBusiness;
import business.EnderecoPessoaBusiness;
import business.InstrutorModalidadeAlunoBusiness;
import business.InstrutorModalidadeBusiness;
import business.InstrutorModalidadeUnidadeBusiness;
import business.LogAcessoBusiness;
import business.PagamentoBusiness;
import business.TelefonePessoaBusiness;
import business.UnidadeAlunoBusiness;

@ManagedBean(name = "aluno")
@SessionScoped
public class AlunoBean {

	@ManagedProperty(value="#{login}")
	private LoginBean loginBean;

	private Aluno aluno = null;

	private Date dataInicio = null;
	private Date dataFim = null;

	private UnidadeAluno unidadeAluno = null;
	private DataModel<UnidadeAluno> model;
	private EnderecoPessoa endereco = null;
	private List<TelefonePessoa> telefones = null;
	private TelefonePessoa telefone = null;
	private UnidadeAluno ua = null;
	private UploadFile scanAutorizacaoResponsavel = null;
	private UploadFile scanAvaliacaoFisica = null;
	private UploadFile scanQuestionario = null;
	private InstrutorModalidade instrutorModalidade = null;
	private List<InstrutorModalidade> instrutoresModalidade = null;
	private DataModel<InstrutorModalidadeAluno> modelImas;
	private DataModel<Pagamento> modelPagamento;
	private Pagamento pagamento = null;
	private InstrutorModalidadeAluno instrutorModalidadeAluno; //para o editar da visualização das modalidades
	private boolean mudouPDFQuestionario;
	private boolean mudouPDFAvaliacaoF;
	private boolean mudouPDFAutorizacao;
	private boolean checkAtrasados;
	private boolean checkPagos;
	private String nomePesquisado;
	private String nomeModalidade = null;

	@PostConstruct
	public void init() {
		if (aluno == null) {
			aluno = new Aluno();
			unidadeAluno = new UnidadeAluno();
			endereco = new EnderecoPessoa();
			telefones = new ArrayList<TelefonePessoa>();
			instrutoresModalidade = new ArrayList<InstrutorModalidade>();
			telefone = new TelefonePessoa();
			ua = new UnidadeAluno();
			scanAutorizacaoResponsavel = new UploadFile();
			scanAvaliacaoFisica = new UploadFile();
			scanQuestionario = new UploadFile();
			instrutorModalidade = new InstrutorModalidade();
			pagamento = new Pagamento();
			instrutorModalidadeAluno = new InstrutorModalidadeAluno();
			mudouPDFQuestionario = false;
			mudouPDFAvaliacaoF = false;
			mudouPDFAutorizacao = false;
			dataInicio = new Date();
			dataFim = new Date();
			nomeModalidade = "";
		}
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public void cadastrarAluno(){
		cadastrarEndereco();

		aluno.setEndereco(endereco);
		AlunoBusiness alunoBusiness = new AlunoBusiness();
		alunoBusiness.salvar(aluno);

		//inserindo na tabela de log
		AcessoBusiness aB = new AcessoBusiness();
		Acesso acesso = new Acesso();
		acesso.setDataAcesso(new Date(System.currentTimeMillis()));
		acesso.setTipo("Cadastro");
		acesso.setNomeTabelaAfetada("Aluno");
		aB.salvar(acesso);

		LogAcessoBusiness laB = new LogAcessoBusiness();
		LogAcesso la = new LogAcesso();
		la.setAcesso(acesso);
		la.setUsuario(loginBean.getUsuario());
		laB.salvar(la);

		cadastrarTelefones();

		cadastrarUnidadeAluno();

		cadastrarInstrutorModalidadeAluno();

		//zerando o bean
		aluno = new Aluno();
		dataInicio = new Date();
		dataFim = new Date();
		endereco = new EnderecoPessoa();
		telefones = new ArrayList<TelefonePessoa>();
		telefone = new TelefonePessoa();
		ua = new UnidadeAluno();
		scanAutorizacaoResponsavel = new UploadFile();
		scanAvaliacaoFisica = new UploadFile();
		scanQuestionario = new UploadFile();
		instrutorModalidade = new InstrutorModalidade();
		instrutorModalidadeAluno = new InstrutorModalidadeAluno();
		instrutoresModalidade = new ArrayList<InstrutorModalidade>();
		nomeModalidade = "";

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("listarAluno.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cadastrarInstrutorModalidadeAluno(){
		InstrutorModalidadeAlunoBusiness imaB = new InstrutorModalidadeAlunoBusiness();
		InstrutorModalidadeAluno ima = new InstrutorModalidadeAluno();
		ima.setAluno(aluno);
		ima.setInstrutorModalidade(instrutorModalidade);
		imaB.salvar(ima);
	}

	public void cadastrarUnidadeAluno(){
		UnidadeAlunoBusiness uaB = new UnidadeAlunoBusiness();
		ua.setAluno(aluno);

		Unidade unidade = new Unidade();
		unidade.setNome(loginBean.getNomeUnidade());

		ua.setUnidade(unidade);
		uaB.salvar(ua);
		scanAutorizacaoResponsavel.gravar(); //grava o pdf na pasta do servidor
		scanAvaliacaoFisica.gravar();
		scanQuestionario.gravar();

	}

	public void cadastrarTelefones(){
		TelefonePessoaBusiness telefoneUnidadeBusiness = new TelefonePessoaBusiness();
		telefone.setPessoa(aluno);
		telefoneUnidadeBusiness.salvar(telefone);
	}

	public void cadastrarEndereco(){
		EnderecoPessoaBusiness enderecoBusiness = new EnderecoPessoaBusiness();
		enderecoBusiness.salvar(endereco);
	}

	public void uploadActionScanAutorizacao (FileUploadEvent event){
		this.scanAutorizacaoResponsavel.fileUpload(event, ".pdf", "/pdfs/");
		this.ua.setScanAutorizacaoResponsavel(scanAutorizacaoResponsavel.getCaminho());
	}

	public void uploadActionScanAvaliacaoF (FileUploadEvent event){
		this.scanAvaliacaoFisica.fileUpload(event, ".pdf", "/pdfs/");
		this.ua.setScanAvaliacaoFisica(scanAvaliacaoFisica.getCaminho());
	}

	public void uploadActionScanQuestionario (FileUploadEvent event){
		this.scanQuestionario.fileUpload(event, ".pdf", "/pdfs/");
		this.ua.setScanQuestionario(scanQuestionario.getCaminho());
	}

	public void uploadActionScanAutorizacaoEditar (FileUploadEvent event){
		this.scanAutorizacaoResponsavel.fileUpload(event, ".pdf", "/pdfs/");
		this.unidadeAluno.setScanAutorizacaoResponsavel(scanAutorizacaoResponsavel.getCaminho());
		this.mudouPDFAutorizacao = true;
	}

	public void uploadActionScanAvaliacaoFEditar (FileUploadEvent event){
		this.scanAvaliacaoFisica.fileUpload(event, ".pdf", "/pdfs/");
		this.unidadeAluno.setScanAvaliacaoFisica(scanAvaliacaoFisica.getCaminho());
		this.mudouPDFAvaliacaoF = true;
	}

	public void uploadActionScanQuestionarioEditar (FileUploadEvent event){
		this.scanQuestionario.fileUpload(event, ".pdf", "/pdfs/");
		this.unidadeAluno.setScanQuestionario(scanQuestionario.getCaminho());
		this.mudouPDFQuestionario = true;
	}

	public List<InstrutorModalidade> getTodosInstrutorModalidade(){
		InstrutorModalidadeBusiness imB = new InstrutorModalidadeBusiness();
		return imB.listar();
	}

	public List<TelefonePessoa> getTodosTelefones(){
		TelefonePessoaBusiness tpB = new TelefonePessoaBusiness();
		ArrayList<TelefonePessoa> retorno = (ArrayList<TelefonePessoa>) tpB.listar(unidadeAluno.getAluno());
		unidadeAluno.getAluno().setTelefones(retorno);
		telefones = retorno;
		return retorno;
	}

	public String editar(){
		return "editarAluno?faces-redirect=true";
	}

	public List<InstrutorModalidade> getInstrutoresModalidade() {
		return instrutoresModalidade;
	}

	public void setInstrutoresModalidade(
			List<InstrutorModalidade> instrutoresModalidade) {
		this.instrutoresModalidade = instrutoresModalidade;
	}

	public String editarIMA(){
		return "editarInstrutorModalidadeAluno";
	}

	public String verTelefone(){
		return "telefonesAluno?faces-redirect=true";
	}

	public EnderecoPessoa getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoPessoa endereco) {
		this.endereco = endereco;
	}

	public UnidadeAluno getUnidadeAluno() {
		return unidadeAluno;
	}

	public DataModel<UnidadeAluno> getModel() {
		return model;
	}

	public void setModel(DataModel<UnidadeAluno> model) {
		this.model = model;
	}

	public boolean isMudouPDFQuestionario() {
		return mudouPDFQuestionario;
	}

	public void setMudouPDFQuestionario(boolean mudouPDFQuestionario) {
		this.mudouPDFQuestionario = mudouPDFQuestionario;
	}

	public boolean isMudouPDFAvaliacaoF() {
		return mudouPDFAvaliacaoF;
	}

	public void setMudouPDFAvaliacaoF(boolean mudouPDFAvaliacaoF) {
		this.mudouPDFAvaliacaoF = mudouPDFAvaliacaoF;
	}

	public String getNomePesquisado() {
		return nomePesquisado;
	}

	public void setNomePesquisado(String nomePesquisado) {
		this.nomePesquisado = nomePesquisado;
	}

	public boolean isCheckAtrasados() {
		return checkAtrasados;
	}

	public void setCheckAtrasados(boolean checkAtrasados) {
		this.checkAtrasados = checkAtrasados;
	}

	public boolean isCheckPagos() {
		return checkPagos;
	}

	public void setCheckPagos(boolean checkPagos) {
		this.checkPagos = checkPagos;
	}

	public boolean isMudouPDFAutorizacao() {
		return mudouPDFAutorizacao;
	}

	public void setMudouPDFAutorizacao(boolean mudouPDFAutorizacao) {
		this.mudouPDFAutorizacao = mudouPDFAutorizacao;
	}

	public InstrutorModalidadeAluno getInstrutorModalidadeAluno() {
		return instrutorModalidadeAluno;
	}

	public void setInstrutorModalidadeAluno(
			InstrutorModalidadeAluno instrutorModalidadeAluno) {
		this.instrutorModalidadeAluno = instrutorModalidadeAluno;
	}

	public void setUnidadeAluno(UnidadeAluno unidadeAluno) {
		this.unidadeAluno = unidadeAluno;
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

	public UnidadeAluno getUa() {
		return ua;
	}

	public void setUa(UnidadeAluno ua) {
		this.ua = ua;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public InstrutorModalidade getInstrutorModalidade() {
		return instrutorModalidade;
	}

	public void setInstrutorModalidade(InstrutorModalidade instrutorModalidade) {
		this.instrutorModalidade = instrutorModalidade;
	}

	public String getNomeModalidade() {
		return nomeModalidade;
	}

	public void setNomeModalidade(String nomeModalidade) {
		this.nomeModalidade = nomeModalidade;
	}

	public String verPagamentos(){
		return "pagamentosAluno?faces-redirect=true";
	}

	public String verModalidades(){
		return "modalidadesAluno?faces-redirect=true";
	}

	public DataModel<Pagamento> getModelPagamento() {
		return modelPagamento;
	}

	public void setModelPagamento(DataModel<Pagamento> modelPagamento) {
		this.modelPagamento = modelPagamento;
	}

	public String voltarListarAluno(){
		return "listarAluno?faces-redirect=true";
	}

	public void voltar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("relatorioAlunoTempo.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public DataModel<InstrutorModalidadeAluno> getTodosModalidadeAluno() throws Exception {
		InstrutorModalidadeUnidadeBusiness imuB = new InstrutorModalidadeUnidadeBusiness();
		Unidade unidade= new Unidade();
		unidade.setNome(loginBean.getNomeUnidade());
		ArrayList<InstrutorModalidadeUnidade> imus = new ArrayList<InstrutorModalidadeUnidade>();
		imus = (ArrayList<InstrutorModalidadeUnidade>) imuB.listar(unidade); //todos os instrutoresModalidadeUnidade

		InstrutorModalidadeAlunoBusiness imaB = new InstrutorModalidadeAlunoBusiness();
		ArrayList<InstrutorModalidadeAluno> imas = new ArrayList<InstrutorModalidadeAluno>();
		imas = (ArrayList<InstrutorModalidadeAluno>) imaB.listar(); //todos os instrutoresModalidadeAluno

		//eliminando objetos que tem o aluno diferente do selecionado
		for (int i = 0; i < imas.size(); i++) {
			if(!imas.get(i).getAluno().getCpf().equals(unidadeAluno.getAluno().getCpf())){
				imas.remove(i);
				i--;
			}
		}

		for (int i = 0; i < imas.size(); i++){
			boolean p = false;
			for (int j = 0; j < imus.size() && p == false; j++){
				if (imas.get(i).getInstrutorModalidade().getIdInstrutorModalidade() ==
						imus.get(j).getInstrutorModalidade().getIdInstrutorModalidade()){
					p = true;
				}
			}
			if (!p){
				imas.remove(i);
			}
		}

		modelImas = new ListDataModel<InstrutorModalidadeAluno>(imas);
		return modelImas;
	}

	public DataModel<Pagamento> getTodosPagamentos() throws Exception {
		PagamentoBusiness pB = new PagamentoBusiness();
		ArrayList<Pagamento> pgmtos = (ArrayList<Pagamento>) pB.listar(unidadeAluno);

		modelPagamento = new ListDataModel<Pagamento>(pgmtos);
		return modelPagamento;
	}

	public DataModel<UnidadeAluno> getTodos() throws Exception {
		UnidadeAlunoBusiness uaB = new UnidadeAlunoBusiness();
		Unidade unidade= new Unidade();
		unidade.setNome(loginBean.getNomeUnidade());
		ArrayList<UnidadeAluno> unidadeAlunos = new ArrayList<UnidadeAluno>();

		//ou pesquisa por nome, ou pelos checkboxes
		if(nomePesquisado == null || nomePesquisado.equals("")){
			if(checkAtrasados && !checkPagos){
				unidadeAlunos = (ArrayList<UnidadeAluno>) uaB.listarAtrasados(unidade);
			}else if(!checkAtrasados && checkPagos){
				unidadeAlunos = (ArrayList<UnidadeAluno>) uaB.listarPagos(unidade);
			}else{ //se os dois estão marcados ou nenhum deles esá marcado, lista todos
				unidadeAlunos = (ArrayList<UnidadeAluno>) uaB.listar(unidade);
			}
		}else{
			unidadeAlunos = (ArrayList<UnidadeAluno>) uaB.listar(unidade, nomePesquisado);
		}

		model = new ListDataModel<UnidadeAluno>(unidadeAlunos);
		return model;
	}

	//---------------------------------- parte do remover e adicionar novos telefones (editar)
	//usa a lista de telefones do parametro, pois ela é passada no getTodos de telefone

	public String removerTelefone(){
		//removendo o telefone da lista base do getTodosTelefones
		for (int i = 0; i < telefones.size(); i++) {
			if(telefones.get(i).getNumero().equals(telefone.getNumero())){
				telefones.remove(i);
				i--;
			}
		}

		//agora remove o telefone da tabela telefonePessoa
		TelefonePessoaBusiness tpB = new TelefonePessoaBusiness();
		tpB.deletar(telefone);


		telefone = new TelefonePessoa();


		return "ok";
	}

	public void addTelefoneEditar(){
		//adicionando o telefone no array de telefones (talvez isso seja inutil)
		telefone.setPessoa(unidadeAluno.getAluno());
		telefones.add(telefone);

		//salvando o novo telefone no banco
		TelefonePessoaBusiness tpB = new TelefonePessoaBusiness();
		tpB.salvar(telefone);

		//zerando o bean

		telefone = new TelefonePessoa();



	}

	public String listar(){
		return "listarAluno?faces-redirect=true";
	}

	//---------------------------------- parte do editar pagamento

	public void attUnidadeAluno(){
		UnidadeAlunoBusiness uaB = new UnidadeAlunoBusiness();
		uaB.update(unidadeAluno);

		//zerando o bean
		aluno = new Aluno();
		dataInicio = new Date();
		dataFim = new Date();
		endereco = new EnderecoPessoa();
		telefones = new ArrayList<TelefonePessoa>();
		telefone = new TelefonePessoa();
		ua = new UnidadeAluno();
		scanAutorizacaoResponsavel = new UploadFile();
		scanAvaliacaoFisica = new UploadFile();
		scanQuestionario = new UploadFile();
		instrutorModalidade = new InstrutorModalidade();
		instrutorModalidadeAluno = new InstrutorModalidadeAluno();
		instrutoresModalidade = new ArrayList<InstrutorModalidade>();
		nomeModalidade = "";


	}

	public String editarPagamento(){
		return "editarPagamento?faces-redirect=true";
	}

	public String voltarPagamentosAluno(){
		return "pagamentosAluno?faces-redirect=true";
	}

	public void atualizarPagamento(){
		PagamentoBusiness pB = new PagamentoBusiness();
		pB.update(pagamento);

		//zerando o bean
		aluno = new Aluno();
		dataInicio = new Date();
		dataFim = new Date();
		endereco = new EnderecoPessoa();
		telefones = new ArrayList<TelefonePessoa>();
		telefone = new TelefonePessoa();
		ua = new UnidadeAluno();
		scanAutorizacaoResponsavel = new UploadFile();
		scanAvaliacaoFisica = new UploadFile();
		scanQuestionario = new UploadFile();
		instrutorModalidade = new InstrutorModalidade();
		instrutorModalidadeAluno = new InstrutorModalidadeAluno();
		instrutoresModalidade = new ArrayList<InstrutorModalidade>();
		nomeModalidade = "";

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("listarAluno.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//------------------parte de inserir a modalidade e remover do aluno

	public void attModalidadeAluno(){
		InstrutorModalidadeAluno ima = new InstrutorModalidadeAluno();
		ima.setAluno(unidadeAluno.getAluno());
		ima.setInstrutorModalidade(instrutorModalidade);

		InstrutorModalidadeAlunoBusiness aux = new InstrutorModalidadeAlunoBusiness();
		ArrayList<InstrutorModalidadeAluno> imas = (ArrayList<InstrutorModalidadeAluno>) aux.listar();

		//verificando se não ja tem nenhum igual para inserir
		boolean jaTem = false;
		for (int i = 0; i < imas.size(); i++) {
			if(imas.get(i).getAluno().getCpf().equals(unidadeAluno.getAluno().getCpf()) && imas.get(i).getInstrutorModalidade().getIdInstrutorModalidade() == instrutorModalidade.getIdInstrutorModalidade()){
				jaTem = true;
			}
		}

		if(!jaTem){
			InstrutorModalidadeAlunoBusiness imaB = new InstrutorModalidadeAlunoBusiness();
			imaB.salvar(ima);
		}


		//zerando o bean
		aluno = new Aluno();
		dataInicio = new Date();
		dataFim = new Date();
		endereco = new EnderecoPessoa();
		telefones = new ArrayList<TelefonePessoa>();
		telefone = new TelefonePessoa();
		ua = new UnidadeAluno();
		scanAutorizacaoResponsavel = new UploadFile();
		scanAvaliacaoFisica = new UploadFile();
		scanQuestionario = new UploadFile();
		instrutorModalidade = new InstrutorModalidade();
		instrutorModalidadeAluno = new InstrutorModalidadeAluno();
		instrutoresModalidade = new ArrayList<InstrutorModalidade>();
		nomeModalidade = "";
	}

	public String removerIMA(){
		InstrutorModalidadeAlunoBusiness imaB = new InstrutorModalidadeAlunoBusiness();
		imaB.deletar(instrutorModalidadeAluno);


		return "ok";
	}

	//----------- finalmente atualizar aluno

	public void attAluno(){
		//endereco
		EnderecoPessoaBusiness epB = new EnderecoPessoaBusiness();
		epB.update(unidadeAluno.getAluno().getEndereco());
		//aluno
		AlunoBusiness alB = new AlunoBusiness();
		alB.update(unidadeAluno.getAluno());
		//unidadeAluno
		UnidadeAlunoBusiness uaB = new UnidadeAlunoBusiness();
		uaB.update(unidadeAluno);

		if(mudouPDFAutorizacao){
			scanAutorizacaoResponsavel.gravar();
		}

		if(mudouPDFAvaliacaoF){
			scanAvaliacaoFisica.gravar();
		}

		if(mudouPDFQuestionario){
			scanQuestionario.gravar();
		}

		//inserindo na tabela de log
		AcessoBusiness aB = new AcessoBusiness();
		Acesso acesso = new Acesso();
		acesso.setDataAcesso(new Date(System.currentTimeMillis()));
		acesso.setTipo("Atualização");
		acesso.setNomeTabelaAfetada("Aluno");
		aB.salvar(acesso);

		LogAcessoBusiness laB = new LogAcessoBusiness();
		LogAcesso la = new LogAcesso();
		la.setAcesso(acesso);
		la.setUsuario(loginBean.getUsuario());
		laB.salvar(la);


		//zerando o bean
		aluno = new Aluno();
		dataInicio = new Date();
		dataFim = new Date();
		endereco = new EnderecoPessoa();
		telefones = new ArrayList<TelefonePessoa>();
		telefone = new TelefonePessoa();
		ua = new UnidadeAluno();
		scanAutorizacaoResponsavel = new UploadFile();
		scanAvaliacaoFisica = new UploadFile();
		scanQuestionario = new UploadFile();
		instrutorModalidade = new InstrutorModalidade();
		instrutorModalidadeAluno = new InstrutorModalidadeAluno();
		instrutoresModalidade = new ArrayList<InstrutorModalidade>();
		nomeModalidade = "";

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("listarAluno.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	public void gerar() {
		try {
			Unidade u = new Unidade();
			u.setNome(loginBean.getNomeUnidade());

			UnidadeAlunoBusiness un = new UnidadeAlunoBusiness();
			ArrayList<UnidadeAluno> unidadeAluno = (ArrayList<UnidadeAluno>) un.listar(u, dataInicio, dataFim);

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("date", new Date());
			params.put(JRParameter.REPORT_LOCALE, new Locale("pt","BR"));

			//recebe o cmainho do esquema de relatorio escolhido 
			JasperReport report = JasperCompileManager.compileReport(
					((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("/resources/modelos/TempoAcademia.jrxml"));

			//recebe a lista
			JasperPrint print = JasperFillManager.fillReport(report, params, new JRBeanCollectionDataSource(unidadeAluno)); 

			JasperExportManager.exportReportToPdfFile(print, 
					((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("/resources/relatorios/AlunoPorTempo.pdf"));

			FacesContext.getCurrentInstance().getExternalContext().redirect("pdfAlunoTempo.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public void gerarPorModalidade() {
		try {
			Modalidade m = new Modalidade();
			m.setNome(nomeModalidade);
			Unidade u = new Unidade();
			u.setNome(loginBean.getNomeUnidade());

			System.out.println(nomeModalidade);
			
			InstrutorModalidadeAlunoBusiness a = new InstrutorModalidadeAlunoBusiness();
			ArrayList<InstrutorModalidadeAluno> instrutorModalidadeAluno = (ArrayList<InstrutorModalidadeAluno>) a.listar(m, u);

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("date", new Date());
			params.put(JRParameter.REPORT_LOCALE, new Locale("pt","BR"));

			//recebe o cmainho do esquema de relatorio escolhido 
			JasperReport report = JasperCompileManager.compileReport(
					((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("/resources/modelos/PorModalidade.jrxml"));  

			//recebe a lista
			JasperPrint print = JasperFillManager.fillReport(report, params, new JRBeanCollectionDataSource(instrutorModalidadeAluno)); 

			JasperExportManager.exportReportToPdfFile(print, 
					((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("/resources/relatorios/AlunoPorModalidade.pdf"));

			FacesContext.getCurrentInstance().getExternalContext().redirect("pdfAlunoModalidade.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String gerarComAvaliacao() {
		try {
			Unidade u = new Unidade();
			u.setNome(loginBean.getNomeUnidade());
			
			UnidadeAlunoBusiness un = new UnidadeAlunoBusiness();
			ArrayList<UnidadeAluno> unidadeAluno = (ArrayList<UnidadeAluno>) un.listarFezAvaliacao(u);

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("date", new Date());
			params.put(JRParameter.REPORT_LOCALE, new Locale("pt","BR"));

			//recebe o cmainho do esquema de relatorio escolhido 
			JasperReport report = JasperCompileManager.compileReport(
					((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("/resources/modelos/RelatorioFezAvaliacaoFisica.jrxml"));  

			//recebe a lista
			JasperPrint print = JasperFillManager.fillReport(report, params, new JRBeanCollectionDataSource(unidadeAluno)); 

			JasperExportManager.exportReportToPdfFile(print, 
					((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("/resources/relatorios/AlunoComAvaliacao.pdf"));
			
		}  catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return "pdfAlunoComAvaliacao.xhtml";	
	}

	public String gerarSemAvaliacao() {
		try {
			Unidade u = new Unidade();
			u.setNome(loginBean.getNomeUnidade());
			
			UnidadeAlunoBusiness un = new UnidadeAlunoBusiness();
			ArrayList<UnidadeAluno> unidadeAluno = (ArrayList<UnidadeAluno>) un.listarNaoFezAvaliacao(u);

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("date", new Date());
			params.put(JRParameter.REPORT_LOCALE, new Locale("pt","BR"));

			//recebe o cmainho do esquema de relatorio escolhido 
			JasperReport report = JasperCompileManager.compileReport(
					((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("/resources/modelos/RelatorioNaoFezAvaliacaoFisica.jrxml"));  

			//recebe a lista
			JasperPrint print = JasperFillManager.fillReport(report, params, new JRBeanCollectionDataSource(unidadeAluno)); 

			JasperExportManager.exportReportToPdfFile(print, 
					((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("/resources/relatorios/AlunoSemAvaliacao.pdf"));
			
			
		}  catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "pdfAlunoSemAvaliacao.xhtml";
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
}
