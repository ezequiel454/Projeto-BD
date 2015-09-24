package controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import models.InstrutorModalidadeAluno;
import business.InstrutorModalidadeAlunoBusiness;

@ManagedBean(name = "instrutorModalidadeAluno")
@SessionScoped
public class InstrutorModalidadeAlunoBean {

	private InstrutorModalidadeAluno instrutorModalidadeAluno = null;
	
	@PostConstruct
	public void init() {
		if (instrutorModalidadeAluno == null) {
			instrutorModalidadeAluno = new InstrutorModalidadeAluno();
		}
	}
	
	public InstrutorModalidadeAluno getInstrutorModalidadeAluno() {
		return instrutorModalidadeAluno;
	}

	public void setInstrutorModalidadeAluno(InstrutorModalidadeAluno instrutorModalidadeAluno) {
		this.instrutorModalidadeAluno = instrutorModalidadeAluno;
	}
	
	public void cadastrarInstrutorModalidadeAluno(){
		InstrutorModalidadeAlunoBusiness instrutorModalidadeAlunoBusiness = new InstrutorModalidadeAlunoBusiness();
		instrutorModalidadeAlunoBusiness.salvar(instrutorModalidadeAluno);
	}
	
	public String editar(){
		return "editarInstrutorModalidadeAluno";
	}
}
