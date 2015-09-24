package daos;

import java.util.List;

import models.InstrutorModalidadeAluno;
import models.Modalidade;
import models.Unidade;

public interface InstrutorModalidadeAlunoDao {
	public void save(InstrutorModalidadeAluno instrutorModalidadeAluno);
	public InstrutorModalidadeAluno getInstrutorModalidadeAluno(InstrutorModalidadeAluno instrutorModalidadeAluno);
	public List<InstrutorModalidadeAluno> list();
	public List<InstrutorModalidadeAluno> list(Modalidade modalidade, Unidade unidade);
	public void remove(InstrutorModalidadeAluno instrutorModalidadeAluno);
	public void update(InstrutorModalidadeAluno instrutorModalidadeAluno);
}
