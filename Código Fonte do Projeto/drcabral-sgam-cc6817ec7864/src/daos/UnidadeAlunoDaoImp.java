package daos;

import java.util.Date;
import java.util.List;

import models.Unidade;
import models.UnidadeAluno;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class UnidadeAlunoDaoImp implements UnidadeAlunoDao{
	private Session session;
	
	public void setSession(Session session) {
	    this.session = session;
	}
	  
	public void save(UnidadeAluno unidadeAluno) {
		if(session.getTransaction().isActive()){
			this.session.save(unidadeAluno);
			session.getTransaction().commit();
		}else{
			Transaction t = this.session.beginTransaction();
		    this.session.save(unidadeAluno);
		    t.commit();
		}
	}

	public UnidadeAluno getUnidadeAluno(UnidadeAluno unidadeAluno) {
		session.beginTransaction();
        return (UnidadeAluno)session.load(UnidadeAluno.class, unidadeAluno.getIdUnidadeAluno());
	}

	public List<UnidadeAluno> list() {
		Transaction t = this.session.beginTransaction();
		List lista = this.session.createQuery("from UnidadeAluno").list();
		t.commit();
		return lista;
	}
	
	public List<UnidadeAluno> list(Unidade unidade) {
		Transaction t = this.session.beginTransaction();
		Criteria criteria = session.createCriteria(UnidadeAluno.class);
		criteria.add(Restrictions.eq("unidade.nome", unidade.getNome()));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List lista = criteria.list();
		t.commit();
		return lista;
	}
	
	public List<UnidadeAluno> listAtrasados(Unidade unidade) {
		Transaction t = this.session.beginTransaction();
		Criteria criteria = session.createCriteria(UnidadeAluno.class);
		criteria.add(Restrictions.eq("unidade.nome", unidade.getNome()));
		criteria.add(Restrictions.eq("situacaoPagamento", "Atrasado"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List lista = criteria.list();
		t.commit();
		return lista;
	}
	
	public List<UnidadeAluno> listPagos(Unidade unidade) {
		Transaction t = this.session.beginTransaction();
		Criteria criteria = session.createCriteria(UnidadeAluno.class);
		criteria.add(Restrictions.eq("unidade.nome", unidade.getNome()));
		criteria.add(Restrictions.eq("situacaoPagamento", "Pago"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List lista = criteria.list();
		t.commit();
		return lista;
	}
	
	public List<UnidadeAluno> list(Unidade unidade, Date inicio, Date fim) {
		Transaction t = this.session.beginTransaction();
		Criteria criteria = session.createCriteria(UnidadeAluno.class);
		criteria.add(Restrictions.eq("unidade.nome", unidade.getNome()));
		criteria.add(Restrictions.ge("dataMatricula", inicio));
		criteria.add(Restrictions.le("dataMatricula", fim));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List lista = criteria.list();
		t.commit();
		return lista;
	}
	
	public  List<UnidadeAluno> listFezAvaliacao(Unidade unidade){
		Transaction t = this.session.beginTransaction();
		Criteria criteria = session.createCriteria(UnidadeAluno.class);
		criteria.add(Restrictions.eq("unidade.nome", unidade.getNome()));
		criteria.add(Restrictions.isNotNull("scanAvaliacaoFisica"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List lista = criteria.list();
		t.commit();
		return lista;
	}
	
	public  List<UnidadeAluno> listNaoFezAvaliacao(Unidade unidade){
		Transaction t = this.session.beginTransaction();
		Criteria criteria = session.createCriteria(UnidadeAluno.class);
		criteria.add(Restrictions.eq("unidade.nome", unidade.getNome()));
		criteria.add(Restrictions.isNull("scanAvaliacaoFisica"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List lista = criteria.list();
		t.commit();
		return lista;
	}

	public void remove(UnidadeAluno unidadeAluno) {
	    Transaction t = this.session.beginTransaction();
	    this.session.delete(unidadeAluno);
	    t.commit();
	}

	public void update(UnidadeAluno unidadeAluno) {
		Transaction t = this.session.beginTransaction();
	    this.session.update(unidadeAluno);
	    t.commit();
	}
	
	public List<UnidadeAluno> list(Unidade unidade, String nomePesquisado) {
		Transaction t = session.beginTransaction();
		String queryS = "from UnidadeAluno where unidade.nome = '"+unidade.getNome() +"' and aluno.nome like '%" + nomePesquisado+ "%'";
		Query query = session.createQuery(queryS);
        List lista = query.list();
        t.commit();
        return lista;
	}

}
