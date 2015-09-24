package controller;

import business.TelefonePessoaBusiness;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import models.TelefonePessoa;

@ManagedBean(name = "TelefonePessoa")
@SessionScoped
public class TelefonePessoaBean {
	private TelefonePessoa tp = null;

	@PostConstruct
	public void init() {
		if (this.tp == null) {
			this.tp = new TelefonePessoa();
		}
	}

	public TelefonePessoa getTelefonePessoa() {
		return this.tp;
	}

	public void setTelefonePessoa(TelefonePessoa tp) {
		this.tp = tp;
	}

	public void cadastrarTelefonePessoa() {
		TelefonePessoaBusiness telefonePessoaBusiness = new TelefonePessoaBusiness();
		telefonePessoaBusiness.salvar(this.tp);
	}

	public String editarTelefonePessoa() {
		return "editarTelefonePessoa";
	}
}
