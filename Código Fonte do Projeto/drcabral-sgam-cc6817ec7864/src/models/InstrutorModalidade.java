package models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class InstrutorModalidade implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idInstrutorModalidade;
	
	@ManyToOne
	@JoinColumn(name="cpf")
	private Instrutor instrutor;
	
	@ManyToOne
	@JoinColumn(name="idModalidade")
	private Modalidade modalidade;
	
	@OneToMany(mappedBy="instrutorModalidade", targetEntity=InstrutorModalidadeAluno.class, fetch=FetchType.LAZY)
	private List<InstrutorModalidadeAluno> instrutorModalidadeAluno;
	
	@OneToMany(mappedBy="instrutorModalidade", targetEntity=InstrutorModalidadeUnidade.class, fetch=FetchType.LAZY)
	private List<InstrutorModalidadeUnidade> instrutorModalidadeUnidade;

	public int getIdInstrutorModalidade() {
		return idInstrutorModalidade;
	}

	public void setIdInstrutorModalidade(int idInstrutorModalidade) {
		this.idInstrutorModalidade = idInstrutorModalidade;
	}

	public Instrutor getInstrutor() {
		return instrutor;
	}

	public void setInstrutor(Instrutor instrutor) {
		this.instrutor = instrutor;
	}

	public Modalidade getModalidade() {
		return modalidade;
	}

	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}

	public List<InstrutorModalidadeUnidade> getInstrutorModalidadeUnidade() {
		return instrutorModalidadeUnidade;
	}

	public void setInstrutorModalidadeUnidade(
			List<InstrutorModalidadeUnidade> instrutorModalidadeUnidade) {
		this.instrutorModalidadeUnidade = instrutorModalidadeUnidade;
	}

}
