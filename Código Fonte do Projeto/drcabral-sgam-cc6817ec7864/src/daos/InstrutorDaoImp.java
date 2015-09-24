package daos;

import java.util.List;

import models.Instrutor;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class InstrutorDaoImp implements InstrutorDao{

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void save(Instrutor instrutor) {
		Transaction t = session.beginTransaction();
		session.save(instrutor);
		t.commit();
	}

	public List<Instrutor> list() {
		List lista;
		if(session.getTransaction().isActive()){
			lista = session.createQuery("from Instrutor").list();
			session.getTransaction().commit();
		}else{
			Transaction t = session.beginTransaction();
			lista = session.createQuery("from Instrutor").list();
			t.commit();
		}
		
		
		return lista;
	}

	public void remove(Instrutor instrutor) {
		Transaction t = session.beginTransaction();
		session.delete(instrutor);
		t.commit();
	}

	@Override
	public void update(Instrutor instrutor) {
		Transaction t = session.beginTransaction();
		session.update(instrutor);
		t.commit();
	}

	@Override
	public Instrutor getInstrutor(Instrutor instrutor) {
		Transaction t = session.beginTransaction();
		Instrutor instrutorRet = (Instrutor) session.get(Instrutor.class, instrutor.getCpf());
		t.commit();
		return instrutorRet;
	}

}
