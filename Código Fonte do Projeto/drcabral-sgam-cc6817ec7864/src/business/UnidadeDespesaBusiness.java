package business;

import java.util.Date;
import java.util.List;

import models.Unidade;
import models.UnidadeDespesa;
import daos.DAOFactory;
import daos.UnidadeDespesaDao;

public class UnidadeDespesaBusiness {

private UnidadeDespesaDao unidadeDespesaDao;      
    
    public UnidadeDespesaBusiness() {
    	setUnidadeDespesaDao(DAOFactory.createUnidadeDespesa());
    }
     
    public void setUnidadeDespesaDao(UnidadeDespesaDao unidadeDespesaDao) {
        this.unidadeDespesaDao = unidadeDespesaDao;
    }     
    
    public void salvar(UnidadeDespesa unidadeDespesa) {
    	this.unidadeDespesaDao.save(unidadeDespesa);
    }
    
    public void update(UnidadeDespesa unidadeDespesa) {
    	this.unidadeDespesaDao.update(unidadeDespesa);
    }
    
    public void deletar(UnidadeDespesa unidadeDespesa) {
    	this.unidadeDespesaDao.remove(unidadeDespesa);
    }
    
    public UnidadeDespesa procurar(UnidadeDespesa unidadeDespesa) {
    	return this.unidadeDespesaDao.getUnidadeDespesa(unidadeDespesa);
    }
    
    public List<UnidadeDespesa> listar() {
    	return this.unidadeDespesaDao.list();
    }
    
    public List<UnidadeDespesa> listar(Unidade unidade) {
    	return this.unidadeDespesaDao.list(unidade);
    }
    
    public List<UnidadeDespesa> listar(Unidade unidade, Date inicio, Date fim) {
    	return this.unidadeDespesaDao.list(unidade, inicio, fim);
    }
}
