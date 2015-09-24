package daos;

import java.util.List;

import models.TelefoneUnidade;
import models.Unidade;
import models.UnidadeAluno;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class TelefoneUnidadeDaoImp implements TelefoneUnidadeDao{

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void save(TelefoneUnidade telefoneUnidade) {
		Transaction t = session.beginTransaction();
		session.save(telefoneUnidade);
		t.commit();
	}

	public List<TelefoneUnidade> list() {
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from TelefoneUnidade").list();
		t.commit();
		return lista;
	}
	
	public List<TelefoneUnidade> list(Unidade unidade) {
		Transaction t = session.beginTransaction();
		Criteria criteria = session.createCriteria(TelefoneUnidade.class);
		criteria.add(Restrictions.eq("unidade.nome", unidade.getNome()));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List lista = criteria.list();
		t.commit();
		return lista;
	}

	public void remove(TelefoneUnidade telefoneUnidade) {
		Transaction t = session.beginTransaction();
		session.delete(telefoneUnidade);
		t.commit();
	}

	@Override
	public void update(TelefoneUnidade telefoneUnidade) {
		Transaction t = session.beginTransaction();
		session.update(telefoneUnidade);
		t.commit();
	}

	@Override
	public TelefoneUnidade getTelefoneUnidade(TelefoneUnidade telefoneUnidade) {
		session.beginTransaction();
		return (TelefoneUnidade) session.load(TelefoneUnidade.class, telefoneUnidade.getIdTelefone());
	}
}
