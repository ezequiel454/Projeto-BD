package business;

import java.util.List;

import models.Pagamento;
import models.UnidadeAluno;
import daos.DAOFactory;
import daos.PagamentoDao;

public class PagamentoBusiness {	
	private PagamentoDao pagamentoDao;
	
	public PagamentoBusiness() {
    	setPagamentoDao(DAOFactory.createPagamento());
    }
     
    public void setPagamentoDao(PagamentoDao PagamentoDao) {
        this.pagamentoDao = PagamentoDao;
    }     
    
    public void salvar(Pagamento Pagamento) {
    	this.pagamentoDao.save(Pagamento);
    }
    
    public void update(Pagamento Pagamento) {
    	this.pagamentoDao.update(Pagamento);
    }
    
    public void deletar(Pagamento Pagamento) {
    	this.pagamentoDao.remove(Pagamento);
    }
    
    public Pagamento procurar(Pagamento Pagamento) {
    	return this.pagamentoDao.getPagamento(Pagamento);
    }
    
    public List<Pagamento> listar() {
    	return this.pagamentoDao.list();
    }
    
    public List<Pagamento> listar(UnidadeAluno unidadeAluno) {
    	return this.pagamentoDao.list(unidadeAluno);
    }
}
