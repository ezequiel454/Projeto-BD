package daos;

import java.util.List;

import models.Aluno;
import models.TelefonePessoa;
import models.UnidadeAluno;

public interface AlunoDao {
	public void save(Aluno aluno);
	public Aluno getAluno(Aluno aluno);
	public List<Aluno> list();
	public List<Aluno> list(UnidadeAluno unidadeAluno);
	public void remove(Aluno aluno);
	public void update(Aluno aluno);
}
