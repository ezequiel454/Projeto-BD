package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import models.Despesa;
import models.LogAcesso;
import models.Unidade;
import models.UnidadeDespesa;
import business.AcessoBusiness;
import business.DespesaBusiness;
import business.LogAcessoBusiness;
import business.UnidadeDespesaBusiness;

@ManagedBean(name="despesa")
@SessionScoped
public class DespesaBean {

	@ManagedProperty(value="#{login}")
	private LoginBean loginBean;

	private Despesa despesa = null;
	
	private Date dataInicio = null;
	private Date dataFim = null;
	
	UnidadeDespesa unidadeDespesa = null;
	private DataModel<UnidadeDespesa> model;

	@PostConstruct
	public void init() {
		if (despesa == null) {
			despesa = new Despesa();
			unidadeDespesa = new UnidadeDespesa();
			dataInicio = new Date();
			dataFim = new Date();
		}
	}

	public Despesa getDespesa() {
		return despesa;
	}

	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}

	public void atualizarDespesa(){
		atualizarUnidadeDespesa();

		DespesaBusiness despesaBusiness = new DespesaBusiness();
		despesaBusiness.update(unidadeDespesa.getDespesa());

		//inserindo na tabela de log
		AcessoBusiness aB = new AcessoBusiness();
		Acesso acesso = new Acesso();
		acesso.setDataAcesso(new Date(System.currentTimeMillis()));
		acesso.setTipo("Atualização");
		acesso.setNomeTabelaAfetada("Despesa");
		aB.salvar(acesso);

		LogAcessoBusiness laB = new LogAcessoBusiness();
		LogAcesso la = new LogAcesso();
		la.setAcesso(acesso);
		la.setUsuario(loginBean.getUsuario());
		laB.salvar(la);

		//zerando o bean depois de editar
		despesa = new Despesa();
		unidadeDespesa = new UnidadeDespesa();
		dataInicio = new Date();
		dataFim = new Date();

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("listarDespesa.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void atualizarUnidadeDespesa(){
		UnidadeDespesaBusiness udB = new UnidadeDespesaBusiness();
		udB.update(unidadeDespesa);
	}

	public void cadastrarDespesa(){
		DespesaBusiness despesaBusiness = new DespesaBusiness();
		despesaBusiness.salvar(despesa);

		cadastrarUnidadeDespesa();

		//inserindo na tabela de log
		AcessoBusiness aB = new AcessoBusiness();
		Acesso acesso = new Acesso();
		acesso.setDataAcesso(new Date(System.currentTimeMillis()));
		acesso.setTipo("Cadastro");
		acesso.setNomeTabelaAfetada("Despesa");
		aB.salvar(acesso);

		LogAcessoBusiness laB = new LogAcessoBusiness();
		LogAcesso la = new LogAcesso();
		la.setAcesso(acesso);
		la.setUsuario(loginBean.getUsuario());
		laB.salvar(la);

		//zerando o bean
		despesa = new Despesa();
		unidadeDespesa = new UnidadeDespesa();
		dataInicio = new Date();
		dataFim = new Date();

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("listarDespesa.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cadastrarUnidadeDespesa(){
		UnidadeDespesaBusiness udB = new UnidadeDespesaBusiness();
		unidadeDespesa.setDespesa(despesa);
		Unidade unidade = new Unidade();
		unidade.setNome(loginBean.getNomeUnidade());
		unidadeDespesa.setUnidade(unidade);
		udB.salvar(unidadeDespesa);
	}

	public String editar(){
		return "editarDespesa?faces-redirect=true";
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public UnidadeDespesa getUnidadeDespesa() {
		return unidadeDespesa;
	}

	public String voltarListarDespesa(){
		return "listarDespesa?faces-redirect=true";
	}

	public void setUnidadeDespesa(UnidadeDespesa unidadeDespesa) {
		this.unidadeDespesa = unidadeDespesa;
	}

	public DataModel<UnidadeDespesa> getTodos() throws Exception {
		UnidadeDespesaBusiness udB = new UnidadeDespesaBusiness();
		Unidade unidade= new Unidade();
		unidade.setNome(loginBean.getNomeUnidade());
		ArrayList<UnidadeDespesa> unidadeDespesas = new ArrayList<UnidadeDespesa>();
		unidadeDespesas = (ArrayList<UnidadeDespesa>) udB.listar(unidade);

		ArrayList<Despesa> despesas = new ArrayList<Despesa>();
		for (int i = 0; i < unidadeDespesas.size(); i++) {
			despesas.add(unidadeDespesas.get(i).getDespesa());
		}

		model = new ListDataModel<UnidadeDespesa>(unidadeDespesas);
		return model;
	}

	public DataModel<UnidadeDespesa> getModel() {
		return model;
	}

	public void setModel(DataModel<UnidadeDespesa> model) {
		this.model = model;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public void gerar() {
		try {
			Unidade u = new Unidade();
			u.setNome(loginBean.getNomeUnidade());
			
			UnidadeDespesaBusiness um = new UnidadeDespesaBusiness();
	    	ArrayList<UnidadeDespesa> unidadeDespesa = (ArrayList<UnidadeDespesa>) um.listar(u, dataInicio, dataFim);
			
	    	Map<String, Object> params = new HashMap<String, Object>();
			params.put("date", new Date());
			params.put(JRParameter.REPORT_LOCALE, new Locale("pt","BR"));

			//recebe o cmainho do esquema de relatorio escolhido 
			JasperReport report = JasperCompileManager.compileReport(
					((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("/resources/modelos/Gastos.jrxml"));  

			//recebe a lista
			JasperPrint print = JasperFillManager.fillReport(report, params, new JRBeanCollectionDataSource(unidadeDespesa)); 

			JasperExportManager.exportReportToPdfFile(print, 
					((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("/resources/relatorios/DespesaPorTempo.pdf"));

	    	
			FacesContext.getCurrentInstance().getExternalContext().redirect("pdfGastosTempo.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void voltar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("relatorioGastos.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
}
