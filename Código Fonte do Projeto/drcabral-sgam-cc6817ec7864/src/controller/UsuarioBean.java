package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import models.Acesso;
import models.LogAcesso;
import models.Usuario;
import models.Unidade;
import models.UnidadeUsuario;
import business.AcessoBusiness;
import business.LogAcessoBusiness;
import business.UsuarioBusiness;
import business.UnidadeBusiness;
import business.UnidadeUsuarioBusiness;

@ManagedBean(name = "usuario")
@SessionScoped

public class UsuarioBean {

	@ManagedProperty(value="#{login}")
	private LoginBean loginBean;

	private Usuario usuario = null;
	private UnidadeUsuario unidadeUsuario = null;
	private Unidade unidade = null;
	private DataModel<UnidadeUsuario> model;

	@PostConstruct
	public void init() {
		if (usuario == null) {
			usuario = new Usuario();
			unidadeUsuario = new UnidadeUsuario();
			unidade = new Unidade();
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void cadastrarUsuario(){
		UsuarioBusiness usuarioBusiness = new UsuarioBusiness();
		usuarioBusiness.salvar(usuario);

		cadastrarUnidadeUsuario();

		//inserindo na tabela de log
		AcessoBusiness aB = new AcessoBusiness();
		Acesso acesso = new Acesso();
		acesso.setDataAcesso(new Date(System.currentTimeMillis()));
		acesso.setTipo("Cadastro");
		acesso.setNomeTabelaAfetada("Usuario");
		aB.salvar(acesso);

		LogAcessoBusiness laB = new LogAcessoBusiness();
		LogAcesso la = new LogAcesso();
		la.setAcesso(acesso);
		la.setUsuario(loginBean.getUsuario());
		laB.salvar(la);

		//zerando o bean
		usuario = new Usuario();
		unidadeUsuario = new UnidadeUsuario();
		unidade = new Unidade();

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("listarUsuario.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String editar(){
		return "editarUsuario?faces-redirect=true";
	}

	public void cadastrarUnidadeUsuario(){
		unidadeUsuario.setUnidade(unidade);
		unidadeUsuario.setUsuario(usuario);
		UnidadeUsuarioBusiness uuBusiness = new UnidadeUsuarioBusiness();
		uuBusiness.salvar(unidadeUsuario);
	}

	public List<Unidade> getTodasUnidades(){
		UnidadeBusiness uB = new UnidadeBusiness();
		return uB.listar();
	}

	public DataModel<UnidadeUsuario> getTodos(){
		UnidadeUsuarioBusiness uuB = new UnidadeUsuarioBusiness();
		Unidade unidade= new Unidade();
		unidade.setNome(loginBean.getNomeUnidade());
		ArrayList<UnidadeUsuario> unidadeUsuarios = new ArrayList<UnidadeUsuario>();
		unidadeUsuarios = (ArrayList<UnidadeUsuario>) uuB.listar(unidade);

		model = new ListDataModel<UnidadeUsuario>(unidadeUsuarios);
		return this.model;
	}

	public void setUnidadeUsuario(UnidadeUsuario unidadeUsuario) {
		this.unidadeUsuario = unidadeUsuario;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public UnidadeUsuario getUnidadeUsuario() {
		return unidadeUsuario;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public DataModel<UnidadeUsuario> getModel() {
		return model;
	}

	public void setModel(DataModel<UnidadeUsuario> model) {
		this.model = model;
	}

	public void atualizarUsuario(){
		atualizarUnidadeUsuario();

		UsuarioBusiness usuarioBusiness = new UsuarioBusiness();
		usuarioBusiness.update(unidadeUsuario.getUsuario());

		//inserindo na tabela de log
		AcessoBusiness aB = new AcessoBusiness();
		Acesso acesso = new Acesso();
		acesso.setDataAcesso(new Date(System.currentTimeMillis()));
		acesso.setTipo("Atualização");
		acesso.setNomeTabelaAfetada("Usuario");
		aB.salvar(acesso);

		LogAcessoBusiness laB = new LogAcessoBusiness();
		LogAcesso la = new LogAcesso();
		la.setAcesso(acesso);
		la.setUsuario(loginBean.getUsuario());
		laB.salvar(la);

		usuario = new Usuario();
		unidadeUsuario = new UnidadeUsuario();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("listarUsuario.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void atualizarUnidadeUsuario(){
		UnidadeUsuarioBusiness udB = new UnidadeUsuarioBusiness();
		udB.update(unidadeUsuario);
	}

	public String voltarListarUsuario(){
		return "listarUsuario?faces-redirect=true";
	}
}