package business;

import java.util.List;

import models.InstrutorModalidade;
import models.Modalidade;
import daos.DAOFactory;
import daos.InstrutorModalidadeDao;

public class InstrutorModalidadeBusiness {
	
	private InstrutorModalidadeDao instrutorModalidadeDao;      
    
    public InstrutorModalidadeBusiness() {
    	setInstrutorModalidadeDao(DAOFactory.createInstrutorModalidade());
    }
     
    public void setInstrutorModalidadeDao(InstrutorModalidadeDao instrutorModalidadeDao) {
        this.instrutorModalidadeDao = instrutorModalidadeDao;
    }     
    
    public void salvar(InstrutorModalidade instrutorModalidade) {
    	this.instrutorModalidadeDao.save(instrutorModalidade);
    }
    
    public void update(InstrutorModalidade instrutorModalidade) {
    	this.instrutorModalidadeDao.update(instrutorModalidade);
    }
    
    public void deletar(InstrutorModalidade instrutorModalidade) {
    	this.instrutorModalidadeDao.remove(instrutorModalidade);
    }
    
    public InstrutorModalidade procurar(InstrutorModalidade instrutorModalidade) {
    	return this.instrutorModalidadeDao.getInstrutorModalidade(instrutorModalidade);
    }
    
    public List<InstrutorModalidade> listar() {
    	return this.instrutorModalidadeDao.list();
    }
    
    public List<InstrutorModalidade> listar(Modalidade modalidade) {
    	return this.instrutorModalidadeDao.list(modalidade);
    }

}
