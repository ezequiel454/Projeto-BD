package daos;

import java.util.List;

import models.LogAcesso;
import models.Unidade;

public interface LogAcessoDao {
	public void save(LogAcesso logAcesso);
	public LogAcesso getLogAcesso(LogAcesso logAcesso);
	public List<LogAcesso> list();
	public void remove(LogAcesso logAcesso);
	public void update(LogAcesso logAcesso);
}
