package models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Modalidade implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private int idModalidade;
	
	@OneToMany(mappedBy="modalidade" , targetEntity=InstrutorModalidade.class, fetch=FetchType.LAZY)
	private List<InstrutorModalidade> instrutorModalidades;
	
	@OneToMany(mappedBy="modalidade", targetEntity=UnidadeModalidade.class, fetch=FetchType.LAZY)
	private List<UnidadeModalidade> unidadeModalidade;
	
	
	private String nome;

	public int getIdModalidade() {
		return idModalidade;
	}

	public void setIdModalidade(int idModalidade) {
		this.idModalidade = idModalidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<InstrutorModalidade> getInstrutorModalidades() {
		return instrutorModalidades;
	}

	public void setInstrutorModalidades(
			List<InstrutorModalidade> instrutorModalidades) {
		this.instrutorModalidades = instrutorModalidades;
	}

	public List<UnidadeModalidade> getUnidadeModalidade() {
		return unidadeModalidade;
	}

	public void setUnidadeModalidade(List<UnidadeModalidade> unidadeModalidade) {
		this.unidadeModalidade = unidadeModalidade;
	}
}
