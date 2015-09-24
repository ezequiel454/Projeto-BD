package daos;

import java.util.List;

import models.Unidade;
import models.UnidadeModalidade;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class UnidadeModalidadeDaoImp implements UnidadeModalidadeDao{
	 private Session session;
	  
	  public void setSession(Session session)
	  {
	    this.session = session;
	  }
	  
	  public void save(UnidadeModalidade um)
	  {
	    Transaction t = this.session.beginTransaction();
	    this.session.save(um);
	    t.commit();
	  }
	  
	  public List<UnidadeModalidade> list()
	  {
	    Transaction t = this.session.beginTransaction();
	    List lista = this.session.createQuery("from UnidadeModalidade").list();
	    t.commit();
	    return lista;
	  }
	  
	  public List<UnidadeModalidade> list(Unidade unidade)
	  {
	    Transaction t = this.session.beginTransaction();
	    Criteria criteria = session.createCriteria(UnidadeModalidade.class);
	    criteria.add(Restrictions.eq("unidade.nome", unidade.getNome()));
	    criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	    List lista = criteria.list();
	    t.commit();
	    return lista;
	  }
	  
	  public List<UnidadeModalidade> listPorMensalidade(Unidade unidade)
	  {
	    Transaction t = this.session.beginTransaction();
	    Criteria criteria = session.createCriteria(UnidadeModalidade.class);
	    criteria.add(Restrictions.eq("unidade.nome", unidade.getNome()));
	    criteria.addOrder(Order.desc("valorMensalidade"));
	    criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	    List lista = criteria.list();
	    t.commit();
	    return lista;
	  }
	  
	  public void remove(UnidadeModalidade um)
	  {
	    Transaction t = this.session.beginTransaction();
	    this.session.delete(um);
	    t.commit();
	  }
	  
	  public void update(UnidadeModalidade um)
	  {
	    Transaction t = this.session.beginTransaction();
	    this.session.update(um);
	    t.commit();
	  }
	  
	  public UnidadeModalidade getUnidadeModalidade(UnidadeModalidade um)
	  {
	    return (UnidadeModalidade)this.session.load(UnidadeModalidade.class, um.getIdUnidadeModalidade());
	  }
}
