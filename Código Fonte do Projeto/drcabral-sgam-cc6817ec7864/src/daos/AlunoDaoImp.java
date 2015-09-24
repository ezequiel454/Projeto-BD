package daos;

import java.util.List;

import models.Aluno;
import models.UnidadeAluno;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class AlunoDaoImp implements AlunoDao{
	private Session session;
	
	public void setSession(Session session) {
        this.session = session;
    }
	
	public void save(Aluno aluno) {
		Transaction t = session.beginTransaction();
        session.save(aluno);
        t.commit();	
	}

	public Aluno getAluno(Aluno aluno) {
		Transaction t = session.beginTransaction();
		Aluno retorno = (Aluno)session.get(Aluno.class, aluno.getCpf());
        return retorno;
	}

	public List<Aluno> list() {
		Transaction t = session.beginTransaction();
        List lista = session.createQuery("from Aluno").list();
        t.commit();
        return lista;
	}
	
	public List<Aluno> list(UnidadeAluno unidadeAluno) {
		Transaction t = session.beginTransaction();
		Criteria criteria = session.createCriteria(Aluno.class);
		criteria.add(Restrictions.eq("cpf", unidadeAluno.getAluno().getCpf()));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List lista = criteria.list();
        t.commit();
        return lista;
	}

	public void remove(Aluno aluno) {
		Transaction t = session.beginTransaction();
        session.delete(aluno);
        t.commit();
	}

	public void update(Aluno aluno) {
		Transaction t = session.beginTransaction();
        session.update(aluno);
        t.commit();
	}
}
