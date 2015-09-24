package business;

import java.util.List;

import models.Acesso;
import daos.DAOFactory;
import daos.AcessoDao;

public class AcessoBusiness {
	private AcessoDao acessoDao;
	
	public AcessoBusiness() {
    	setAcessoDao(DAOFactory.createAcesso());
    }
     
    public void setAcessoDao(AcessoDao acessoDao) {
        this.acessoDao = acessoDao;
    }     
    
    public void salvar(Acesso acesso) {
    	this.acessoDao.save(acesso);
    }
    
    public void update(Acesso acesso) {
    	this.acessoDao.update(acesso);
    }
    
    public void deletar(Acesso acesso) {
    	this.acessoDao.remove(acesso);
    }
    
    public Acesso procurar(Acesso acesso) {
    	return this.acessoDao.getAcesso(acesso);
    }
    
    public List<Acesso> listar() {
    	return this.acessoDao.list();
    }
}
