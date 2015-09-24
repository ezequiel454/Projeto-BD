package daos;

import java.util.List;

import models.InstrutorModalidadeUnidade;
import models.Modalidade;
import models.Unidade;

public interface InstrutorModalidadeUnidadeDao {
	
	public void save(InstrutorModalidadeUnidade instrutorModalidadeUnidade);
	public InstrutorModalidadeUnidade getInstrutorModalidadeUnidade(InstrutorModalidadeUnidade instrutorModalidadeUnidade);
	public List<InstrutorModalidadeUnidade> list();
	public List<InstrutorModalidadeUnidade> list(Unidade unidade);
	public List<InstrutorModalidadeUnidade> list(Modalidade modalidade);
	public List<InstrutorModalidadeUnidade> listPorSalario(Unidade unidade);
	public List<InstrutorModalidadeUnidade> list(Modalidade modalidade, Unidade unidade);
	public void remove(InstrutorModalidadeUnidade instrutorModalidadeUnidade);
	public void update(InstrutorModalidadeUnidade instrutorModalidadeUnidade);
}
