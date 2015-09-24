package daos;

import java.util.List;

import models.Pagamento;
import models.UnidadeAluno;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class PagamentoDaoImp implements PagamentoDao{
	private Session session;
	
	public void setSession(Session session) {
        this.session = session;
    }

	public void save(Pagamento pagamento) {
		Transaction t = session.beginTransaction();
        session.save(pagamento);
        t.commit();		
	}

	public Pagamento getPagamento(Pagamento pagamento) {
		session.beginTransaction();
        return (Pagamento)session.load(Pagamento.class, pagamento.getIdPagamento());
	}

	public List<Pagamento> list() {
		Transaction t = session.beginTransaction();
        List lista = session.createQuery("from Pagamento").list();
        t.commit();
        return lista;
	}
	
	public List<Pagamento> list(UnidadeAluno unidadeAluno) {
		Transaction t = session.beginTransaction();
		Criteria criteria = session.createCriteria(Pagamento.class);
		criteria.add(Restrictions.eq("unidadeAluno.idUnidadeAluno", unidadeAluno.getIdUnidadeAluno()));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List lista = criteria.list();
        t.commit();
        return lista;
	}

	public void remove(Pagamento pagamento) {
		Transaction t = session.beginTransaction();
        session.delete(pagamento);
        t.commit();
	}

	public void update(Pagamento pagamento) {
		Transaction t = session.beginTransaction();
        session.update(pagamento);
        t.commit();		
	}
	
	
}
