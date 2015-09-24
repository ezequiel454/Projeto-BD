package daos;

import java.util.Date;
import java.util.List;

import models.Unidade;
import models.UnidadeDespesa;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class UnidadeDespesaDaoImp implements UnidadeDespesaDao{

	private Session session;

    public void setSession(Session session)
    {
        this.session = session;
    }

    public void save(UnidadeDespesa unidadeDespesa)
    {
        Transaction t = session.beginTransaction();
        session.save(unidadeDespesa);
        t.commit();
    }

    public List<UnidadeDespesa> list()
    {
        Transaction t = session.beginTransaction();
        List lista = session.createQuery("from UnidadeDespesa").list();
        t.commit();
        return lista;
    }
    
    public List<UnidadeDespesa> list(Unidade unidade)
    {
    	Transaction t = session.beginTransaction();
    	Criteria criteria = session.createCriteria(UnidadeDespesa.class);
        criteria.add(Restrictions.eq("unidade.nome", unidade.getNome()));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List lista = criteria.list();
        t.commit();
        return lista;
    }
    
    public List<UnidadeDespesa> list(Unidade unidade, Date inicio, Date fim)
    {
    	Transaction t = session.beginTransaction();
    	Criteria criteria = session.createCriteria(UnidadeDespesa.class);
        criteria.add(Restrictions.eq("unidade.nome", unidade.getNome()));
        criteria.add(Restrictions.ge("data", inicio));
        criteria.add(Restrictions.le("data", fim));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List lista = criteria.list();
        t.commit();
        return lista;
    }

    public void remove(UnidadeDespesa unidadeDespesa)
    {
        Transaction t = session.beginTransaction();
        session.delete(unidadeDespesa);
        t.commit();
    }

    public void update(UnidadeDespesa unidadeDespesa)
    {
        Transaction t = session.beginTransaction();
        session.update(unidadeDespesa);
        t.commit();
    }

    public UnidadeDespesa getUnidadeDespesa(UnidadeDespesa unidadeDespesa)
    {
    	session.beginTransaction();
        return (UnidadeDespesa)session.load(UnidadeDespesa.class, unidadeDespesa.getIdUnidadeDespesa());
    }
}
