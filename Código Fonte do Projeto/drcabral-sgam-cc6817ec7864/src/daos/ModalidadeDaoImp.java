package daos;

import java.util.List;

import models.Modalidade;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ModalidadeDaoImp implements ModalidadeDao{

	private Session session;

    public void setSession(Session session)
    {
        this.session = session;
    }

    public void save(Modalidade modalidade)
    {
        Transaction t = session.beginTransaction();
        session.save(modalidade);
        t.commit();
    }

    public List<Modalidade> list()
    {
        Transaction t = session.beginTransaction();
        List lista = session.createQuery("from Despesa").list();
        t.commit();
        return lista;
    }

    public void remove(Modalidade modalidade)
    {
        Transaction t = session.beginTransaction();
        session.delete(modalidade);
        t.commit();
    }

    public void update(Modalidade modalidade)
    {
        Transaction t = session.beginTransaction();
        session.update(modalidade);
        t.commit();
    }

    public Modalidade getModalidade(Modalidade modalidade)
    {
    	Transaction t = session.beginTransaction();
        return (Modalidade)session.load(Modalidade.class, modalidade.getIdModalidade());
    }
}
