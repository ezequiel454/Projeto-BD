package business;

import java.util.List;

import models.Modalidade;
import daos.DAOFactory;
import daos.ModalidadeDao;

public class ModalidadeBusiness {

private ModalidadeDao modalidadeDao;      
    
    public ModalidadeBusiness() {
    	setModalidadeDao(DAOFactory.createModalidade());
    }
     
    public void setModalidadeDao(ModalidadeDao modalidadeDao) {
        this.modalidadeDao = modalidadeDao;
    }     
    
    public void salvar(Modalidade modalidade) {
    	this.modalidadeDao.save(modalidade);
    }
    
    public void update(Modalidade modalidade) {
    	this.modalidadeDao.update(modalidade);
    }
    
    public void deletar(Modalidade modalidade) {
    	this.modalidadeDao.remove(modalidade);
    }
    
    public Modalidade procurar(Modalidade modalidade) {
    	return this.modalidadeDao.getModalidade(modalidade);
    }
    
    public List<Modalidade> listar() {
    	return this.modalidadeDao.list();
    }
}
