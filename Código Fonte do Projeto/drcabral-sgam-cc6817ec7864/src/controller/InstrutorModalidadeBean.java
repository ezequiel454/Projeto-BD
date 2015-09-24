package controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import models.InstrutorModalidade;
import business.InstrutorModalidadeBusiness;

@ManagedBean(name = "instrutorModalidade")
@SessionScoped
public class InstrutorModalidadeBean {

	private InstrutorModalidade instrutorModalidade = null;
	
	@PostConstruct
	public void init() {
		if (instrutorModalidade == null) {
			instrutorModalidade = new InstrutorModalidade();
		}
	}
	
	public InstrutorModalidade getInstrutorModalidade() {
		return instrutorModalidade;
	}

	public void setInstrutorModalidade(InstrutorModalidade instrutorModalidade) {
		this.instrutorModalidade = instrutorModalidade;
	}
	
	public void cadastrarInstrutorModalidade(){
		InstrutorModalidadeBusiness instrutorModalidadeBusiness = new InstrutorModalidadeBusiness();
		instrutorModalidadeBusiness.salvar(instrutorModalidade);
	}
	
	public String editar(){
		return "editarInstrutorModalidade";
	}
}
