package daos;

import java.util.List;

import models.InstrutorModalidade;
import models.Modalidade;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class InstrutorModalidadeDaoImp implements InstrutorModalidadeDao{

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void save(InstrutorModalidade instrutorModalidade) {
		if(session.getTransaction().isActive()){
			session.save(instrutorModalidade);
			session.getTransaction().commit();
		}else{
			Transaction t = session.beginTransaction();
			session.save(instrutorModalidade);
			t.commit();
		}
	}

	public List<InstrutorModalidade> list() {
		List lista;
		if(session.getTransaction().isActive()){
			lista = session.createQuery("from InstrutorModalidade").list();
			session.getTransaction().commit();
		}else{
			Transaction t = session.beginTransaction();
			lista = session.createQuery("from InstrutorModalidade").list();
			t.commit();
		}
		
		
		return lista;
	}
	
	public List<InstrutorModalidade> list(Modalidade modalidade) {
		Transaction t = session.beginTransaction();
		Criteria criteria = session.createCriteria(InstrutorModalidade.class);
		criteria.add(Restrictions.eq("modalidade.idModalidade", modalidade.getIdModalidade()));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List lista = criteria.list();
		t.commit();
		return lista;
	}

	public void remove(InstrutorModalidade instrutorModalidade) {
		Transaction t = session.beginTransaction();
		session.delete(instrutorModalidade);
		t.commit();
	}

	@Override
	public void update(InstrutorModalidade instrutorModalidade) {
		Transaction t = session.beginTransaction();
		session.update(instrutorModalidade);
		t.commit();
	}

	@Override
	public InstrutorModalidade getInstrutorModalidade(InstrutorModalidade instrutorModalidade) {
		Transaction t = session.beginTransaction();
		InstrutorModalidade retorno = (InstrutorModalidade) session.get(InstrutorModalidade.class, instrutorModalidade.getIdInstrutorModalidade());
		t.commit();
		return retorno;
	}

}
