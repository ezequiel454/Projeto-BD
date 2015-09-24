package business;

import java.util.List;

import models.Despesa;
import models.Unidade;
import daos.DAOFactory;
import daos.DespesaDao;

public class DespesaBusiness {
	
private DespesaDao despesaDao;      
    
    public DespesaBusiness() {
    	setDespesaDao(DAOFactory.createDespesa());
    }
     
    public void setDespesaDao(DespesaDao despesaDao) {
        this.despesaDao = despesaDao;
    }     
    
    public void salvar(Despesa despesa) {
    	this.despesaDao.save(despesa);
    }
    
    public void update(Despesa despesa) {
    	this.despesaDao.update(despesa);
    }
    
    public void deletar(Despesa despesa) {
    	this.despesaDao.remove(despesa);
    }
    
    public Despesa procurar(Despesa despesa) {
    	return this.despesaDao.getDespesa(despesa);
    }
    
    public List<Despesa> listar() {
    	return this.despesaDao.list();
    }
}
