package daos;

import java.io.Serializable;
import java.util.List;

import models.EnderecoUnidade;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class EnderecoUnidadeDaoImp implements EnderecoUnidadeDao{

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void save(EnderecoUnidade enderecoUnidade) {
		Transaction t = session.beginTransaction();
		session.save(enderecoUnidade);
		t.commit();
	}

	public List<EnderecoUnidade> list() {
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from EnderecoUnidade").list();
		t.commit();
		return lista;
	}

	public void remove(EnderecoUnidade enderecoUnidade) {
		Transaction t = session.beginTransaction();
		session.delete(enderecoUnidade);
		t.commit();
	}

	@Override
	public void update(EnderecoUnidade enderecoUnidade) {
		Transaction t = session.beginTransaction();
		session.update(enderecoUnidade);
		t.commit();
	}

	@Override
	public EnderecoUnidade getEnderecoUnidade(EnderecoUnidade enderecoUnidade) {
		session.beginTransaction();
		return (EnderecoUnidade) session.load(EnderecoUnidade.class, enderecoUnidade.getIdEndereco());
	}
}
