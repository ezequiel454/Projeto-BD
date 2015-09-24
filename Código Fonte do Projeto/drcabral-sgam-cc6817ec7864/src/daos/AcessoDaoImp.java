package daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import models.Acesso;

public class AcessoDaoImp implements AcessoDao{
	private Session session;
	
	public void setSession(Session session) {
        this.session = session;
    }

	public void save(Acesso acesso) {
		if(session.getTransaction().isActive()){
			session.save(acesso);
			session.getTransaction().commit();
		}else{
			Transaction t = session.beginTransaction();
	        session.save(acesso);
	        t.commit();
		}	
	}

	public Acesso getAcesso(Acesso acesso) {
		session.beginTransaction();
        return (Acesso)session.load(Acesso.class, acesso.getIdAcesso());
	}

	public List<Acesso> list() {
		Transaction t = session.beginTransaction();
        List lista = session.createQuery("from Acesso").list();
        t.commit();
        return lista;
	}

	public void remove(Acesso acesso) {
		Transaction t = session.beginTransaction();
        session.delete(acesso);
        t.commit();
	}

	public void update(Acesso acesso) {
		Transaction t = session.beginTransaction();
        session.update(acesso);
        t.commit();	
	}

}
