package daos;

import java.util.List;

import models.TelefoneUnidade;
import models.Unidade;

public interface TelefoneUnidadeDao {
	public void save(TelefoneUnidade telefoneUnidade);
	public TelefoneUnidade getTelefoneUnidade(TelefoneUnidade telefoneUnidade);
	public List<TelefoneUnidade> list();
	public List<TelefoneUnidade> list(Unidade unidade);
	public void remove(TelefoneUnidade telefoneUnidade);
	public void update(TelefoneUnidade telefoneUnidade);
}
