package controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import models.InstrutorModalidadeUnidade;
import business.InstrutorModalidadeUnidadeBusiness;

@ManagedBean(name = "instrutorModalidadeUnidade")
@SessionScoped
public class InstrutorModalidadeUnidadeBean {

private InstrutorModalidadeUnidade instrutorModalidadeUnidade = null;
	
	@PostConstruct
	public void init() {
		if (instrutorModalidadeUnidade == null) {
			instrutorModalidadeUnidade = new InstrutorModalidadeUnidade();
		}
	}
	
	public InstrutorModalidadeUnidade getInstrutorModalidadeUnidade() {
		return instrutorModalidadeUnidade;
	}

	public void setInstrutorModalidadeUnidade(InstrutorModalidadeUnidade instrutorModalidadeUnidade) {
		this.instrutorModalidadeUnidade = instrutorModalidadeUnidade;
	}
	
	public void cadastrarInstrutorModalidadeUnidade(){
		InstrutorModalidadeUnidadeBusiness instrutorModalidadeUnidadeBusiness = new InstrutorModalidadeUnidadeBusiness();
		instrutorModalidadeUnidadeBusiness.salvar(instrutorModalidadeUnidade);
	}
	
	public String editar(){
		return "editarInstrutorModalidadeUnidade";
	}
}
