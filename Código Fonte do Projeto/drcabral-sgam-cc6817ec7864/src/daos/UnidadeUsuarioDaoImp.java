package daos;

import java.util.List;

import models.Unidade;
import models.UnidadeUsuario;
import models.Usuario;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class UnidadeUsuarioDaoImp implements UnidadeUsuarioDao{

	private Session session;

    public void setSession(Session session)
    {
        this.session = session;
    }

    public void save(UnidadeUsuario unidadeUsuario)
    {
        Transaction t = session.beginTransaction();
        session.save(unidadeUsuario);
        t.commit();
    }

    public List<UnidadeUsuario> list()
    {
        Transaction t = session.beginTransaction();
        List lista = session.createQuery("from UnidadeUsuario").list();
        t.commit();
        return lista;
    }
    
    public List<UnidadeUsuario> list(Unidade unidade)
    {
        Transaction t = session.beginTransaction();
        Criteria criteria = session.createCriteria(UnidadeUsuario.class);
        criteria.add(Restrictions.eq("unidade.nome", unidade.getNome()));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List lista = criteria.list();
        t.commit();
        return lista;
    }

    public void remove(UnidadeUsuario unidadeUsuario)
    {
        Transaction t = session.beginTransaction();
        session.delete(unidadeUsuario);
        t.commit();
    }

    public void update(UnidadeUsuario unidadeUsuario)
    {
        Transaction t = session.beginTransaction();
        session.update(unidadeUsuario);
        t.commit();
    }

    public UnidadeUsuario getUnidadeUsuario(UnidadeUsuario unidadeUsuario)
    {
    	Transaction t = session.beginTransaction();
        return (UnidadeUsuario)session.load(UnidadeUsuario.class, unidadeUsuario.getIdUnidadeUsuario());
    }
    
    public List<UnidadeUsuario> procurarUnidades(Usuario usuario){
        Query query = session.createQuery("from UnidadeUsuario where login = :loginParam");
        query.setParameter("loginParam", usuario.getLogin());
        List lista = query.list();
        return lista;
    }
}
