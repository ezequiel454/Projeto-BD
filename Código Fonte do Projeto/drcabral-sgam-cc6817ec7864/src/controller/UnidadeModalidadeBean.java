package controller;

import business.UnidadeModalidadeBusiness;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import models.UnidadeModalidade;

@ManagedBean(name = "UnidadeModalidade")
@SessionScoped
public class UnidadeModalidadeBean {
	private UnidadeModalidade um = null;

	@PostConstruct
	public void init() {
		if (this.um == null) {
			this.um = new UnidadeModalidade();
		}
	}

	public UnidadeModalidade getUnidadeModalidade() {
		return this.um;
	}

	public void setUnidadeModalidade(UnidadeModalidade um) {
		this.um = um;
	}

	public void cadastrarUnidadeModalidade() {
		UnidadeModalidadeBusiness unidadeModalidadeBusiness = new UnidadeModalidadeBusiness();
		unidadeModalidadeBusiness.salvar(this.um);
	}

	public String editarUnidadeModalidade() {
		return "editarUnidadeModalidade";
	}
}
