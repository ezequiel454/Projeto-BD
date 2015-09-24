package controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import business.TelefoneUnidadeBusiness;
import models.TelefoneUnidade;

@ManagedBean(name = "telefoneUnidade")
@SessionScoped
public class TelefoneUnidadeBean {

	private TelefoneUnidade telefoneUnidade = null;
	
	@PostConstruct
	public void init() {
		if (telefoneUnidade == null) {
			telefoneUnidade = new TelefoneUnidade();
		}
	}
	
	public TelefoneUnidade getTelefoneUnidade() {
		return telefoneUnidade;
	}

	public void setTelefoneUnidade(TelefoneUnidade telefoneUnidade) {
		this.telefoneUnidade = telefoneUnidade;
	}
	
	public void cadastrarTelefoneUnidade(){
		TelefoneUnidadeBusiness TelefoneUnidadeBusiness = new TelefoneUnidadeBusiness();
		TelefoneUnidadeBusiness.salvar(telefoneUnidade);
	}
	
	public String editar(){
		return "editarTelefoneUnidade";
	}
}
