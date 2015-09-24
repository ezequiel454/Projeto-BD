package daos;

import java.util.List;
import models.EnderecoPessoa;
import org.hibernate.*;

public class EnderecoPessoaDaoImp implements EnderecoPessoaDao{

	private Session session;
	
	 public EnderecoPessoaDaoImp()
	    {
	    }

	    public void setSession(Session session)
	    {
	        this.session = session;
	    }

	    public void save(EnderecoPessoa ep)
	    {
	        Transaction t = session.beginTransaction();
	        session.save(ep);
	        t.commit();
	    }

	    public List<EnderecoPessoa> list()
	    {
	        Transaction t = session.beginTransaction();
	        List lista = session.createQuery("from EnderecoPessoa").list();
	        t.commit();
	        return lista;
	    }

	    public void remove(EnderecoPessoa ep)
	    {
	        Transaction t = session.beginTransaction();
	        session.delete(ep);
	        t.commit();
	    }

	    public void update(EnderecoPessoa ep)
	    {
	        Transaction t = session.beginTransaction();
	        session.update(ep);
	        t.commit();
	    }

	    public EnderecoPessoa getEnderecoPessoa(EnderecoPessoa ep)
	    {
	        return (EnderecoPessoa)session.load(EnderecoPessoa.class, ep.getIdEndereco());
	    }

}
