package business;

import java.util.List;

import models.LogAcesso;
import models.Unidade;
import daos.DAOFactory;
import daos.LogAcessoDao;

public class LogAcessoBusiness {

	private LogAcessoDao logAcessoDao;
	
	public LogAcessoBusiness() {
    	setLogAcessoDao(DAOFactory.createLogAcesso());
    }
     
    public void setLogAcessoDao(LogAcessoDao logAcessoDao) {
        this.logAcessoDao = logAcessoDao;
    }     
    
    public void salvar(LogAcesso logAcesso) {
    	this.logAcessoDao.save(logAcesso);
    }
    
    public void update(LogAcesso logAcesso) {
    	this.logAcessoDao.update(logAcesso);
    }
    
    public void deletar(LogAcesso logAcesso) {
    	this.logAcessoDao.remove(logAcesso);
    }
    
    public LogAcesso procurar(LogAcesso logAcesso) {
    	return this.logAcessoDao.getLogAcesso(logAcesso);
    }
    
    public List<LogAcesso> listar() {
    	return this.logAcessoDao.list();
    }
}
