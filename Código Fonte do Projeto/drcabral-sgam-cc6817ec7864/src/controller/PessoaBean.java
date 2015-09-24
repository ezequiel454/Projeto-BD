package controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import business.PessoaBusiness;
import models.Pessoa;

@ManagedBean(name = "Pessoa")
@SessionScoped
public class PessoaBean {
	public PessoaBean() {
		pessoa = null;
	}

	@PostConstruct
	public void init() {
		if (pessoa == null)
			pessoa = new Pessoa();
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public void cadastrarPessoa() {
		PessoaBusiness pessoaBusiness = new PessoaBusiness();
		pessoaBusiness.salvar(pessoa);
	}

	public String editarPessoa() {
		return "editarPessoa";
	}

	private Pessoa pessoa;

}
