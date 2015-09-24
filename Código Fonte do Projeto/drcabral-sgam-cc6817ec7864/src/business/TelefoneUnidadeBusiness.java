package business;

import java.util.List;

import models.TelefoneUnidade;
import models.Unidade;
import daos.DAOFactory;
import daos.TelefoneUnidadeDao;

public class TelefoneUnidadeBusiness {
	
	private TelefoneUnidadeDao telefoneUnidadeDao;      
    
    public TelefoneUnidadeBusiness() {
    	setTelefoneUnidadeDao(DAOFactory.createTelefoneUnidade());
    }
     
    public void setTelefoneUnidadeDao(TelefoneUnidadeDao telefoneUnidadeDao) {
        this.telefoneUnidadeDao = telefoneUnidadeDao;
    }     
    
    public void salvar(TelefoneUnidade telefoneUnidade) {
    	this.telefoneUnidadeDao.save(telefoneUnidade);
    }
    
    public void update(TelefoneUnidade telefoneUnidade) {
    	this.telefoneUnidadeDao.update(telefoneUnidade);
    }
    
    public void deletar(TelefoneUnidade telefoneUnidade) {
    	this.telefoneUnidadeDao.remove(telefoneUnidade);
    }
    
    public TelefoneUnidade procurar(TelefoneUnidade telefoneUnidade) {
    	return this.telefoneUnidadeDao.getTelefoneUnidade(telefoneUnidade);
    }
    
    public List<TelefoneUnidade> listar() {
    	return this.telefoneUnidadeDao.list();
    }
    
    public List<TelefoneUnidade> listar(Unidade unidade) {
    	return this.telefoneUnidadeDao.list(unidade);
    }

}
