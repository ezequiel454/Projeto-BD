package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="cpf")  //dizendo a qual chave primaria o extends se relaciona na especialização
@Inheritance(strategy=InheritanceType.JOINED) //dizendo que é uma entidade "especializável" - caso entre AlunoMenor
public class Aluno extends Pessoa{

	private static final long serialVersionUID = 1L;

	//não tem id porque usa o cpf de pessoa
	private String profissao;
	private String turno;
	
	@OneToMany(mappedBy="aluno", targetEntity=UnidadeAluno.class, fetch=FetchType.LAZY)
	private List<UnidadeAluno> unidadeAluno;
	
	@OneToMany(mappedBy="aluno", targetEntity=InstrutorModalidadeAluno.class, fetch=FetchType.LAZY)
	private List<InstrutorModalidadeAluno> instrutorModalidadeAluno;
	
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}

	public List<UnidadeAluno> getUnidadeAluno() {
		return unidadeAluno;
	}
	public void setUnidadeAluno(List<UnidadeAluno> unidadeAluno) {
		this.unidadeAluno = unidadeAluno;
	}
}
