package daos;

import java.util.List;
import models.Acesso;

public interface AcessoDao {
	public void save(Acesso acesso);
	public Acesso getAcesso(Acesso acesso);
	public List<Acesso> list();
	public void remove(Acesso acesso);
	public void update(Acesso acesso);
}
