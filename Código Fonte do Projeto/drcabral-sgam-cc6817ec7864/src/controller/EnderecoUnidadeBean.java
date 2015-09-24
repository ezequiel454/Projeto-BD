package controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import business.EnderecoUnidadeBusiness;
import models.EnderecoUnidade;

@ManagedBean(name = "enderecoUnidade")
@SessionScoped
public class EnderecoUnidadeBean {

	private EnderecoUnidade enderecoUnidade = null;
	
	@PostConstruct
	public void init() {
		if (enderecoUnidade == null) {
			enderecoUnidade = new EnderecoUnidade();
		}
	}
	
	public EnderecoUnidade getEnderecoUnidade() {
		return enderecoUnidade;
	}

	public void setEnderecoUnidade(EnderecoUnidade enderecoUnidade) {
		this.enderecoUnidade = enderecoUnidade;
	}
	
	public void cadastrarEnderecoUnidade(){
		EnderecoUnidadeBusiness EnderecoUnidadeBusiness = new EnderecoUnidadeBusiness();
		EnderecoUnidadeBusiness.salvar(enderecoUnidade);
	}
	
	public String editar(){
		return "editarEnderecoUnidade";
	}
}
