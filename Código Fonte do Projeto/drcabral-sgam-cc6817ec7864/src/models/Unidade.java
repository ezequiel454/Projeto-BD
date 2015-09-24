package models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Unidade implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String nome;
	
	@ManyToOne
	@JoinColumn(name="idEndereco")
	private EnderecoUnidade endereco;
	
	private String cnpj;
	
	@OneToMany(mappedBy="unidade", targetEntity=TelefoneUnidade.class, fetch=FetchType.EAGER)
	private List<TelefoneUnidade> telefones;
	
	@OneToMany(mappedBy="unidade", targetEntity=UnidadeUsuario.class, fetch=FetchType.LAZY)
	private List<UnidadeUsuario> unidadeUsuario;
	
	@OneToMany(mappedBy="unidade", targetEntity=UnidadeModalidade.class, fetch=FetchType.LAZY)
	private List<UnidadeModalidade> unidadeModalidade;
	
	@OneToMany(mappedBy="unidade", targetEntity=UnidadeAluno.class, fetch=FetchType.LAZY)
	private List<UnidadeAluno> unidadeAluno;
	
	@OneToMany(mappedBy="unidade", targetEntity=InstrutorModalidadeUnidade.class, fetch=FetchType.LAZY)
	private List<InstrutorModalidadeUnidade> instrutorModalidadeUnidade;
	
	@OneToMany(mappedBy="unidade", targetEntity=UnidadeDespesa.class, fetch=FetchType.LAZY)
	private List<UnidadeDespesa> unidadeDespesa;
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public List<UnidadeModalidade> getUnidadeModalidade() {
		return unidadeModalidade;
	}

	public void setUnidadeModalidade(List<UnidadeModalidade> unidadeModalidade) {
		this.unidadeModalidade = unidadeModalidade;
	}

	public List<UnidadeAluno> getUnidadeAluno() {
		return unidadeAluno;
	}

	public void setUnidadeAluno(List<UnidadeAluno> unidadeAluno) {
		this.unidadeAluno = unidadeAluno;
	}

	public List<InstrutorModalidadeUnidade> getInstrutorModalidadeUnidade() {
		return instrutorModalidadeUnidade;
	}

	public void setInstrutorModalidadeUnidade(
			List<InstrutorModalidadeUnidade> instrutorModalidadeUnidade) {
		this.instrutorModalidadeUnidade = instrutorModalidadeUnidade;
	}

	public List<UnidadeDespesa> getUnidadeDespesa() {
		return unidadeDespesa;
	}

	public void setUnidadeDespesa(List<UnidadeDespesa> unidadeDespesa) {
		this.unidadeDespesa = unidadeDespesa;
	}

	public EnderecoUnidade getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoUnidade endereco) {
		this.endereco = endereco;
	}

	public List<TelefoneUnidade> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<TelefoneUnidade> telefones) {
		this.telefones = telefones;
	}

	public List<UnidadeUsuario> getUnidadeUsuario() {
		return unidadeUsuario;
	}

	public void setUnidadeUsuario(List<UnidadeUsuario> unidadeUsuario) {
		this.unidadeUsuario = unidadeUsuario;
	}
}
