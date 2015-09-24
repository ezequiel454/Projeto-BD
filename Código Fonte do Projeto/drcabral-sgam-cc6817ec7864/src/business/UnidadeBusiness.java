package business;

import java.io.Serializable;
import java.util.List;

import models.Unidade;
import daos.UnidadeDao;
import daos.DAOFactory;

public class UnidadeBusiness {
	
	private UnidadeDao unidadeDao;      
    
    public UnidadeBusiness() {
    	setUnidadeDao(DAOFactory.createUnidade());
    }
     
    public void setUnidadeDao(UnidadeDao unidadeDao) {
        this.unidadeDao = unidadeDao;
    }     
    
    public void salvar(Unidade unidade) {
    	this.unidadeDao.save(unidade);
    }
    
    public void update(Unidade unidade) {
    	this.unidadeDao.update(unidade);
    }
    
    public void deletar(Unidade unidade) {
    	this.unidadeDao.remove(unidade);
    }
    
    public Unidade procurar(Unidade unidade) {
    	return this.unidadeDao.getUnidade(unidade);
    }
    
    public List<Unidade> listar() {
    	return this.unidadeDao.list();
    }

}
