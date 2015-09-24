package daos;

import java.util.List;

import models.InstrutorModalidadeUnidade;
import models.Modalidade;
import models.Unidade;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class InstrutorModalidadeUnidadeDaoImp implements InstrutorModalidadeUnidadeDao{

	private Session session;

    public void setSession(Session session)
    {
        this.session = session;
    }

    public void save(InstrutorModalidadeUnidade instrutorModalidadeUnidade)
    {
        Transaction t = session.beginTransaction();
        session.save(instrutorModalidadeUnidade);
        t.commit();
    }

    public List<InstrutorModalidadeUnidade> list()
    {
        Transaction t = session.beginTransaction();
        List lista = session.createQuery("from InstrutorModalidadeUnidade").list();
        t.commit();
        return lista;
    }
    
    public List<InstrutorModalidadeUnidade> list(Unidade unidade)
    {
        Transaction t = session.beginTransaction();
        Criteria criteria = session.createCriteria(InstrutorModalidadeUnidade.class);
        criteria.add(Restrictions.eq("unidade.nome", unidade.getNome()));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List lista = criteria.list();
        t.commit();
        return lista;
    }

    public void remove(InstrutorModalidadeUnidade instrutorModalidadeUnidade)
    {
        Transaction t = session.beginTransaction();
        session.delete(instrutorModalidadeUnidade);
        t.commit();
    }

    public void update(InstrutorModalidadeUnidade instrutorModalidadeUnidade)
    {
        Transaction t = session.beginTransaction();
        session.update(instrutorModalidadeUnidade);
        t.commit();
    }
    
    //not working
    public List<InstrutorModalidadeUnidade> list(Modalidade modalidade){
    	 Transaction t = session.beginTransaction();
         Criteria criteria = session.createCriteria(InstrutorModalidadeUnidade.class);
         criteria.add(Restrictions.eq("instrutorModalidade.modalidade.idModalidade", modalidade.getIdModalidade()));
         criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
         List lista = criteria.list();
         t.commit();
         return lista;
    }
    
    public List<InstrutorModalidadeUnidade> listPorSalario(Unidade unidade)
    {
        Transaction t = session.beginTransaction();
        Criteria criteria = session.createCriteria(InstrutorModalidadeUnidade.class);
        criteria.add(Restrictions.eq("unidade.nome", unidade.getNome()));
        criteria.addOrder(Order.desc("salarioInstrutor"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List lista = criteria.list();
        t.commit();
        return lista;
    }
    
	public List<InstrutorModalidadeUnidade> list(Modalidade modalidade, Unidade unidade) {
		Transaction t = this.session.beginTransaction();
		Criteria criteria = session.createCriteria(InstrutorModalidadeUnidade.class, "imu");
		criteria.createAlias("imu.instrutorModalidade", "im");
		criteria.createAlias("im.modalidade", "m");
		criteria.add(Restrictions.eq("m.nome", modalidade.getNome()));
		criteria.add(Restrictions.eq("imu.unidade.nome", unidade.getNome()));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List lista = criteria.list();
		t.commit();
		return lista;
	}

    public InstrutorModalidadeUnidade getInstrutorModalidadeUnidade(InstrutorModalidadeUnidade instrutorModalidadeUnidade)
    {
    	Transaction t = session.beginTransaction();
        return (InstrutorModalidadeUnidade)session.load(InstrutorModalidadeUnidade.class, instrutorModalidadeUnidade.getIdInstrutorModalidadeUnidade());
    }
}
