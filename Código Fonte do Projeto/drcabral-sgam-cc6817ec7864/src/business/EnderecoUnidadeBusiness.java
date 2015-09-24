package business;

import java.io.Serializable;
import java.util.List;

import models.EnderecoUnidade;
import daos.EnderecoUnidadeDao;
import daos.DAOFactory;

public class EnderecoUnidadeBusiness {
	
	private EnderecoUnidadeDao enderecoUnidadeDao;      
    
    public EnderecoUnidadeBusiness() {
    	setEnderecoUnidadeDao(DAOFactory.createEnderecoUnidade());
    }
     
    public void setEnderecoUnidadeDao(EnderecoUnidadeDao enderecoUnidadeDao) {
        this.enderecoUnidadeDao = enderecoUnidadeDao;
    }     
    
    public void salvar(EnderecoUnidade enderecoUnidade) {
    	this.enderecoUnidadeDao.save(enderecoUnidade);
    }
    
    public void update(EnderecoUnidade enderecoUnidade) {
    	this.enderecoUnidadeDao.update(enderecoUnidade);
    }
    
    public void deletar(EnderecoUnidade enderecoUnidade) {
    	this.enderecoUnidadeDao.remove(enderecoUnidade);
    }
    
    public EnderecoUnidade procurar(EnderecoUnidade enderecoUnidade) {
    	return this.enderecoUnidadeDao.getEnderecoUnidade(enderecoUnidade);
    }
    
    public List<EnderecoUnidade> listar() {
    	return this.enderecoUnidadeDao.list();
    }

}
