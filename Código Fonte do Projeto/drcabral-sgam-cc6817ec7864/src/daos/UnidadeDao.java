package daos;

import java.io.Serializable;
import java.util.List;

import models.Unidade;

public interface UnidadeDao {
	public void save(Unidade unidade);
	public Unidade getUnidade(Unidade unidade);
	public List<Unidade> list();
	public void remove(Unidade unidade);
	public void update(Unidade unidade);
}
