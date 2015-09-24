package daos;

import java.util.List;

import models.Aluno;
import models.Instrutor;
import models.TelefonePessoa;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class TelefonePessoaDaoImp implements TelefonePessoaDao{
	 private Session session;
	  
	  public void setSession(Session session)
	  {
	    this.session = session;
	  }
	  
	  public void save(TelefonePessoa tp)
	  {
	    Transaction t = this.session.beginTransaction();
	    this.session.save(tp);
	    t.commit();
	  }
	  
	  public List<TelefonePessoa> list()
	  {
	    Transaction t = this.session.beginTransaction();
	    List lista = this.session.createQuery("from TelefonePessoa").list();
	    t.commit();
	    return lista;
	  }
	  
	  public List<TelefonePessoa> list(Aluno aluno)
	  {
	    Transaction t = this.session.beginTransaction();
	    Criteria criteria = session.createCriteria(TelefonePessoa.class);
	    criteria.add(Restrictions.eq("pessoa.cpf", aluno.getCpf()));
	    criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	    List lista = criteria.list();
	    t.commit();
	    return lista;
	  }
	 
	  public List<TelefonePessoa> list(Instrutor instrutor)
	  {
	    Transaction t = this.session.beginTransaction();
	    Criteria criteria = session.createCriteria(TelefonePessoa.class);
	    criteria.add(Restrictions.eq("pessoa.cpf", instrutor.getCpf()));
	    criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	    List lista = criteria.list();
	    t.commit();
	    return lista;
	  }
	  
	  public void remove(TelefonePessoa tp)
	  {
	    Transaction t = this.session.beginTransaction();
	    this.session.delete(tp);
	    t.commit();
	  }
	  
	  public void update(TelefonePessoa tp)
	  {
	    Transaction t = this.session.beginTransaction();
	    this.session.update(tp);
	    t.commit();
	  }
	  
	  public TelefonePessoa getTelefonePessoa(TelefonePessoa tp)
	  {
	    return (TelefonePessoa)this.session.load(TelefonePessoa.class, tp.getIdTelefone());
	  }
}
