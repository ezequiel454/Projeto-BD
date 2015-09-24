package daos;

import java.util.List;

import models.Despesa;
import models.Unidade;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class DespesaDaoImp implements DespesaDao{

		private Session session;

	    public void setSession(Session session)
	    {
	        this.session = session;
	    }

	    public void save(Despesa despesa)
	    {
	        Transaction t = session.beginTransaction();
	        session.save(despesa);
	        t.commit();
	    }

	    public List<Despesa> list()
	    {
	        Transaction t = session.beginTransaction();
	        List lista = session.createQuery("from Despesa").list();
	        t.commit();
	        return lista;
	    }

	    public void remove(Despesa despesa)
	    {
	        Transaction t = session.beginTransaction();
	        session.delete(despesa);
	        t.commit();
	    }

	    public void update(Despesa despesa)
	    {
	        Transaction t = session.beginTransaction();
	        session.update(despesa);
	        t.commit();
	    }

	    public Despesa getDespesa(Despesa despesa)
	    {
	    	session.beginTransaction();
	        return (Despesa)session.load(Despesa.class, despesa.getIdDespesa());
	    }

}
