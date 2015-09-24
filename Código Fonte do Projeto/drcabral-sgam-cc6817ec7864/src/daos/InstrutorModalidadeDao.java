package daos;

import java.util.List;

import models.InstrutorModalidade;
import models.Modalidade;

public interface InstrutorModalidadeDao {
	public void save(InstrutorModalidade instrutorModalidade);
	public InstrutorModalidade getInstrutorModalidade(InstrutorModalidade instrutorModalidade);
	public List<InstrutorModalidade> list();
	public List<InstrutorModalidade> list(Modalidade modalidade);
	public void remove(InstrutorModalidade instrutorModalidade);
	public void update(InstrutorModalidade instrutorModalidade);
}
