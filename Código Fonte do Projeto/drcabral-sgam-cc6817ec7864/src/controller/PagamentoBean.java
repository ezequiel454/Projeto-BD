package controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import models.Acesso;
import models.LogAcesso;
import models.Pagamento;
import models.Unidade;
import models.UnidadeAluno;
import business.AcessoBusiness;
import business.LogAcessoBusiness;
import business.PagamentoBusiness;
import business.UnidadeAlunoBusiness;

@ManagedBean(name = "pagamento")
@SessionScoped
public class PagamentoBean {

	@ManagedProperty(value="#{login}")
	private LoginBean loginBean;

	private Pagamento pagamento = null;
	private UnidadeAluno unidadeAluno = null;

	@PostConstruct
	public void init() {
		if (pagamento == null) {
			pagamento = new Pagamento();
			unidadeAluno = new UnidadeAluno();
		}
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public void cadastrarPagamento(){
		PagamentoBusiness pagamentoBusiness = new PagamentoBusiness();
		pagamento.setUnidadeAluno(unidadeAluno);
		pagamentoBusiness.salvar(pagamento);

		//inserindo na tabela de log
		AcessoBusiness aB = new AcessoBusiness();
		Acesso acesso = new Acesso();
		acesso.setDataAcesso(new Date(System.currentTimeMillis()));
		acesso.setTipo("Cadastro");
		acesso.setNomeTabelaAfetada("Pagamento");
		aB.salvar(acesso);

		LogAcessoBusiness laB = new LogAcessoBusiness();
		LogAcesso la = new LogAcesso();
		la.setAcesso(acesso);
		la.setUsuario(loginBean.getUsuario());
		laB.salvar(la);

		//zerando o bean
		pagamento = new Pagamento();
		unidadeAluno = new UnidadeAluno();
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("listarAluno.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<UnidadeAluno> getTodosUnidadeAlunos(){
		UnidadeAlunoBusiness uaB = new UnidadeAlunoBusiness();
		Unidade unidadeAux = new Unidade();
		unidadeAux.setNome(loginBean.getNomeUnidade());
		return uaB.listar(unidadeAux);
	}

	public String editar(){
		return "editarPagamento";
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public UnidadeAluno getUnidadeAluno() {
		return unidadeAluno;
	}

	public void setUnidadeAluno(UnidadeAluno unidadeAluno) {
		this.unidadeAluno = unidadeAluno;
	}
}
