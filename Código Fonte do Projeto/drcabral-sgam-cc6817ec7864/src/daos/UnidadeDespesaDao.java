package daos;

import java.util.Date;
import java.util.List;

import models.Unidade;
import models.UnidadeDespesa;

public interface UnidadeDespesaDao {

	public void save(UnidadeDespesa unidadeDespesa);
	public UnidadeDespesa getUnidadeDespesa(UnidadeDespesa unidadeDespesa);
	public List<UnidadeDespesa> list();
	public List<UnidadeDespesa> list(Unidade unidade);
	public List<UnidadeDespesa> list(Unidade unidade, Date inicio, Date fim);
	public void remove(UnidadeDespesa unidadeDespesa);
	public void update(UnidadeDespesa unidadeDespesa);
}
