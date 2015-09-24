package business;

import java.util.List;

import models.Instrutor;
import daos.DAOFactory;
import daos.InstrutorDao;

public class InstrutorBusiness {
	
	private InstrutorDao instrutorDao;      
    
    public InstrutorBusiness() {
    	setInstrutorDao(DAOFactory.createInstrutor());
    }
     
    public void setInstrutorDao(InstrutorDao instrutorDao) {
        this.instrutorDao = instrutorDao;
    }     
    
    public void salvar(Instrutor instrutor) {
    	this.instrutorDao.save(instrutor);
    }
    
    public void update(Instrutor instrutor) {
    	this.instrutorDao.update(instrutor);
    }
    
    public void deletar(Instrutor instrutor) {
    	this.instrutorDao.remove(instrutor);
    }
    
    public Instrutor procurar(Instrutor instrutor) {
    	return this.instrutorDao.getInstrutor(instrutor);
    }
    
    public List<Instrutor> listar() {
    	return this.instrutorDao.list();
    }

}
