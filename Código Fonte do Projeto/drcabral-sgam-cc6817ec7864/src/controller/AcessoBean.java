package controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import models.Acesso;
import business.AcessoBusiness;

@ManagedBean(name = "acesso")
@SessionScoped

public class AcessoBean {
	private Acesso acesso = null;
	
	@PostConstruct
	public void init() {
		if (acesso == null) {
			acesso = new Acesso();
		}
	}
	
	public Acesso getAcesso() {
		return acesso;
	}

	public void setAcesso(Acesso acesso) {
		this.acesso = acesso;
	}
	
	public void cadastrarAcesso(){
		AcessoBusiness acessoBusiness = new AcessoBusiness();
		acessoBusiness.salvar(acesso);
	}
	
	public String editar(){
		return "editarAcesso";
	}

}
