package daos;

import java.io.Serializable;
import java.util.List;

import models.Unidade;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class UnidadeDaoImp implements UnidadeDao{

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void save(Unidade unidade) {
		Transaction t = session.beginTransaction();
		session.save(unidade);
		t.commit();
	}

	public List<Unidade> list() {
		List lista;
		if(session.getTransaction().isActive()){
			lista = session.createQuery("from Unidade").list();
			session.getTransaction().commit();
		}else{
			Transaction t = session.beginTransaction();
			lista = session.createQuery("from Unidade").list();
			t.commit();
		}
		
		
		return lista;
	}

	public void remove(Unidade unidade) {
		Transaction t = session.beginTransaction();
		session.delete(unidade);
		t.commit();
	}

	@Override
	public void update(Unidade unidade) {
		Transaction t = session.beginTransaction();
		session.update(unidade);
		t.commit();
	}

	@Override
	public Unidade getUnidade(Unidade unidade) {
		session.beginTransaction();
		return (Unidade) session.load(Unidade.class, unidade.getNome());
	}
}
