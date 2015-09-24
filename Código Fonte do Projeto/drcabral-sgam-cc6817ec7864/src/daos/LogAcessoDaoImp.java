package daos;

import java.util.List;

import models.LogAcesso;
import models.Unidade;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class LogAcessoDaoImp implements LogAcessoDao {
	private Session session;
	
	public void setSession(Session session) {
        this.session = session;
    }

	public void save(LogAcesso logAcesso) {
		if(session.getTransaction().isActive()){
			session.save(logAcesso);
			session.getTransaction().commit();
		}else{
			Transaction t = session.beginTransaction();
	        session.save(logAcesso);
	        t.commit();
		}
	}

	public LogAcesso getLogAcesso(LogAcesso logAcesso) {
		session.beginTransaction();
        return (LogAcesso)session.load(LogAcesso.class, logAcesso.getIdLogAcesso());
	}

	public List<LogAcesso> list() {
		Transaction t = session.beginTransaction();
        List lista = session.createQuery("from LogAcesso").list();
        t.commit();
        return lista;
	}

	public void remove(LogAcesso logAcesso) {
		Transaction t = session.beginTransaction();
        session.delete(logAcesso);
        t.commit();
	}

	public void update(LogAcesso logAcesso) {
		Transaction t = session.beginTransaction();
        session.update(logAcesso);
        t.commit();
	}

}
