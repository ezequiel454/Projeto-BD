package models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class InstrutorModalidadeAluno implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idInstrutorModalidadeAluno;
	
	@ManyToOne
	@JoinColumn(name="cpf")
	private Aluno aluno;
	
	@ManyToOne
	@JoinColumn(name="idInstrutorModalidade")
	private InstrutorModalidade instrutorModalidade;

	public int getIdInstrutorModalidadeAluno() {
		return idInstrutorModalidadeAluno;
	}

	public void setIdInstrutorModalidadeAluno(int idInstrutorModalidadeAluno) {
		this.idInstrutorModalidadeAluno = idInstrutorModalidadeAluno;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public InstrutorModalidade getInstrutorModalidade() {
		return instrutorModalidade;
	}

	public void setInstrutorModalidade(InstrutorModalidade instrutorModalidade) {
		this.instrutorModalidade = instrutorModalidade;
	}
}
