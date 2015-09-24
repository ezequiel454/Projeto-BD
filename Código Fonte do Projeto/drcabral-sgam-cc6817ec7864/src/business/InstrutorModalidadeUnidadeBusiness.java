package business;

import java.util.List;

import models.InstrutorModalidadeUnidade;
import models.Modalidade;
import models.Unidade;
import daos.DAOFactory;
import daos.InstrutorModalidadeUnidadeDao;

public class InstrutorModalidadeUnidadeBusiness {
	
private InstrutorModalidadeUnidadeDao instrutorModalidadeUnidadeDao;      
    
    public InstrutorModalidadeUnidadeBusiness() {
    	setInstrutorModalidadeUnidadeDao(DAOFactory.createInstrutorModalidadeUnidade());
    }
     
    public void setInstrutorModalidadeUnidadeDao(InstrutorModalidadeUnidadeDao instrutorModalidadeUnidadeDao) {
        this.instrutorModalidadeUnidadeDao = instrutorModalidadeUnidadeDao;
    }     
    
    public void salvar(InstrutorModalidadeUnidade instrutorModalidadeUnidade) {
    	this.instrutorModalidadeUnidadeDao.save(instrutorModalidadeUnidade);
    }
    
    public void update(InstrutorModalidadeUnidade instrutorModalidadeUnidade) {
    	this.instrutorModalidadeUnidadeDao.update(instrutorModalidadeUnidade);
    }
    
    public void deletar(InstrutorModalidadeUnidade instrutorModalidadeUnidade) {
    	this.instrutorModalidadeUnidadeDao.remove(instrutorModalidadeUnidade);
    }
    
    public InstrutorModalidadeUnidade procurar(InstrutorModalidadeUnidade instrutorModalidadeUnidade) {
    	return this.instrutorModalidadeUnidadeDao.getInstrutorModalidadeUnidade(instrutorModalidadeUnidade);
    }
    
    public List<InstrutorModalidadeUnidade> listar() {
    	return this.instrutorModalidadeUnidadeDao.list();
    }
    
    public List<InstrutorModalidadeUnidade> listar(Unidade unidade) {
    	return this.instrutorModalidadeUnidadeDao.list(unidade);
    }
    
    public List<InstrutorModalidadeUnidade> listar(Modalidade modalidade) {
    	return this.instrutorModalidadeUnidadeDao.list(modalidade);
    }
    
    public List<InstrutorModalidadeUnidade> listarPorSalario(Unidade unidade) {
    	return this.instrutorModalidadeUnidadeDao.listPorSalario(unidade);
    }
    
    public List<InstrutorModalidadeUnidade> listar(Modalidade modalidade, Unidade unidade) {
    	return this.instrutorModalidadeUnidadeDao.list(modalidade, unidade);
    }
}
