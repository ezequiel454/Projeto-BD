package daos;

import java.util.List;

import models.Despesa;
import models.Unidade;

public interface DespesaDao {
	public void save(Despesa despesa);
	public Despesa getDespesa(Despesa despesa);
	public List<Despesa> list();
	public void remove(Despesa despesa);
	public void update(Despesa despesa);
}
