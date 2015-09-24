package daos;

import java.util.List;

import models.InstrutorModalidadeAluno;
import models.Modalidade;
import models.Unidade;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class InstrutorModalidadeAlunoDaoImp implements InstrutorModalidadeAlunoDao{

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void save(InstrutorModalidadeAluno instrutorModalidadeAluno) {
		Transaction t = session.beginTransaction();
		session.save(instrutorModalidadeAluno);
		t.commit();
	}

	public List<InstrutorModalidadeAluno> list() {
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from InstrutorModalidadeAluno").list();
		t.commit();
		return lista;
	}
	
	public List<InstrutorModalidadeAluno> list(Modalidade modalidade, Unidade unidade) {
		Transaction t = this.session.beginTransaction();
		Criteria criteria = session.createCriteria(InstrutorModalidadeAluno.class, "ima");
		criteria.createAlias("ima.instrutorModalidade", "im");
		criteria.createAlias("ima.aluno", "a");
		criteria.createAlias("a.unidadeAluno", "ua");
		criteria.createAlias("im.modalidade", "m");
		criteria.add(Restrictions.eq("m.nome", modalidade.getNome()));
		criteria.add(Restrictions.eq("ua.unidade.nome", unidade.getNome()));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List lista = criteria.list();
		t.commit();
		return lista;
	}

	public void remove(InstrutorModalidadeAluno instrutorModalidadeAluno) {
		Transaction t = session.beginTransaction();
		session.delete(instrutorModalidadeAluno);
		t.commit();
	}

	@Override
	public void update(InstrutorModalidadeAluno instrutorModalidadeAluno) {
		Transaction t = session.beginTransaction();
		session.update(instrutorModalidadeAluno);
		t.commit();
	}

	@Override
	public InstrutorModalidadeAluno getInstrutorModalidadeAluno(InstrutorModalidadeAluno instrutorModalidadeAluno) {
		return (InstrutorModalidadeAluno) session.get(InstrutorModalidadeAluno.class, instrutorModalidadeAluno);
	}

}
