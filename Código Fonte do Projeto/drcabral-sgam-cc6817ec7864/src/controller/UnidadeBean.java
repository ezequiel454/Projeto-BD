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
import models.EnderecoUnidade;
import models.LogAcesso;
import models.TelefoneUnidade;
import models.Unidade;
import business.AcessoBusiness;
import business.EnderecoUnidadeBusiness;
import business.LogAcessoBusiness;
import business.TelefoneUnidadeBusiness;
import business.UnidadeBusiness;

@ManagedBean(name = "unidade")
@SessionScoped
public class UnidadeBean {

	@ManagedProperty(value="#{login}")
	private LoginBean loginBean;

	private Unidade unidade = null;
	private EnderecoUnidade endereco = null;
	private List<TelefoneUnidade> telefones = null;
	private TelefoneUnidade telefone = null;
	private DataModel<Unidade> model;
	private DataModel<TelefoneUnidade> modelT;

	@PostConstruct
	public void init() {
		if (unidade == null) {
			unidade = new Unidade();
			endereco = new EnderecoUnidade();
			telefone = new TelefoneUnidade();
			telefones = new ArrayList<TelefoneUnidade>();
		}
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public void cadastrarUnidade() {

		cadastrarEndereco();

		UnidadeBusiness UnidadeBusiness = new UnidadeBusiness();
		unidade.setEndereco(endereco);
		UnidadeBusiness.salvar(unidade);
		cadastrarTelefones();

		//inserindo na tabela de log
		AcessoBusiness aB = new AcessoBusiness();
		Acesso acesso = new Acesso();
		acesso.setDataAcesso(new Date(System.currentTimeMillis()));
		acesso.setTipo("Cadastro");
		acesso.setNomeTabelaAfetada("Unidade");
		aB.salvar(acesso);

		LogAcessoBusiness laB = new LogAcessoBusiness();
		LogAcesso la = new LogAcesso();
		la.setAcesso(acesso);
		la.setUsuario(loginBean.getUsuario());
		laB.salvar(la);

		// zerando o bean
		unidade = new Unidade();
		endereco = new EnderecoUnidade();
		telefone = new TelefoneUnidade();
		telefones = new ArrayList<TelefoneUnidade>();

		try {
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect("listarUnidade.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cadastrarTelefones() {
		TelefoneUnidadeBusiness telefoneUnidadeBusiness = new TelefoneUnidadeBusiness();
		telefone.setUnidade(unidade);
		telefoneUnidadeBusiness.salvar(telefone);

	}

	public void cadastrarEndereco() {
		EnderecoUnidadeBusiness enderecoUnidadeBusiness = new EnderecoUnidadeBusiness();
		enderecoUnidadeBusiness.salvar(endereco);
	}

	public DataModel<Unidade> getTodos() {
		UnidadeBusiness uB = new UnidadeBusiness();
		ArrayList<Unidade> unidades = new ArrayList<Unidade>();
		unidades = (ArrayList<Unidade>) uB.listar();

		model = new ListDataModel<Unidade>(unidades);
		return this.model;
	}

	public List<TelefoneUnidade> getTodosTelefones() {
		TelefoneUnidadeBusiness tuB = new TelefoneUnidadeBusiness();
		return tuB.listar(unidade);
	}

	public void atualizarUnidade() {

		EnderecoUnidadeBusiness enderecoUnidadeBusiness = new EnderecoUnidadeBusiness();
		enderecoUnidadeBusiness.update(unidade.getEndereco());

		UnidadeBusiness unidadeBusiness = new UnidadeBusiness();

		unidadeBusiness.update(unidade);

		//inserindo na tabela de log
		AcessoBusiness aB = new AcessoBusiness();
		Acesso acesso = new Acesso();
		acesso.setDataAcesso(new Date(System.currentTimeMillis()));
		acesso.setTipo("Atualização");
		acesso.setNomeTabelaAfetada("Unidade");
		aB.salvar(acesso);

		LogAcessoBusiness laB = new LogAcessoBusiness();
		LogAcesso la = new LogAcesso();
		la.setAcesso(acesso);
		la.setUsuario(loginBean.getUsuario());
		laB.salvar(la);

		// ZERAR

		unidade = new Unidade();
		endereco = new EnderecoUnidade();
		telefone = new TelefoneUnidade();
		unidade = new Unidade();

		try {
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect("listarUnidade.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String removerTelefone() {
		// removendo o telefone da lista base do getTodosTelefones
		for (int i = 0; i < telefones.size(); i++) {
			if (telefones.get(i).getNumero().equals(telefone.getNumero())) {
				telefones.remove(i);
				i--;
			}
		}


		TelefoneUnidadeBusiness tpB = new TelefoneUnidadeBusiness();
		tpB.deletar(telefone);

		// ZERAR


		telefone = new TelefoneUnidade();


		return "ok";
	}

	public void addTelefoneEditar() {

		telefone.setUnidade(unidade);
		telefones.add(telefone);

		// salvando o novo telefone no banco
		TelefoneUnidadeBusiness tpB = new TelefoneUnidadeBusiness();
		tpB.salvar(telefone);

		// ZERAR

		telefone = new TelefoneUnidade();



	}

	public String editar() {
		return "editarUnidade?faces-redirect=true";
	}

	public String voltarListarUnidade() {
		return "listarUnidade?faces-redirect=true";
	}

	public String editarTelefone() {
		return "editarTelefoneUnidade?faces-redirect=true";
	}

	public String verTelefones() {
		return "telefonesUnidade?faces-redirect=true";
	}

	public EnderecoUnidade getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoUnidade endereco) {
		this.endereco = endereco;
	}

	public List<TelefoneUnidade> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<TelefoneUnidade> telefones) {
		this.telefones = telefones;
	}

	public TelefoneUnidade getTelefone() {
		return telefone;
	}

	public void setTelefone(TelefoneUnidade telefone) {
		this.telefone = telefone;
	}

	public DataModel<Unidade> getModel() {
		return model;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public void setModel(DataModel<Unidade> model) {
		this.model = model;
	}

	public DataModel<TelefoneUnidade> getModelT() {
		return modelT;
	}

	public void setModelT(DataModel<TelefoneUnidade> modelT) {
		this.modelT = modelT;
	}
}
