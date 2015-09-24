package controller;

import business.UnidadeAlunoBusiness;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import models.UnidadeAluno;

@ManagedBean(name = "UnidadeAluno")
@SessionScoped
public class UnidadeAlunoBean {
	private UnidadeAluno unidadeAluno = null;

	@PostConstruct
	public void init() {
		if (this.unidadeAluno == null) {
			this.unidadeAluno = new UnidadeAluno();
		}
	}

	public UnidadeAluno getUnidadeAluno() {
		return this.unidadeAluno;
	}

	public void setUnidadeAluno(UnidadeAluno unidadeAluno) {
		this.unidadeAluno = unidadeAluno;
	}

	public void cadastrarUnidadeAluno() {
		UnidadeAlunoBusiness unidadeAlunoBusiness = new UnidadeAlunoBusiness();
		unidadeAlunoBusiness.salvar(this.unidadeAluno);
	}

	public String editarUnidadeAluno() {
		return "editarUnidadeAluno";
	}
}
