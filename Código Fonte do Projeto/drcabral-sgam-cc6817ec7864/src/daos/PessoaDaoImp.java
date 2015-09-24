package daos;

import java.util.List;
import models.Pessoa;
import org.hibernate.*;

public class PessoaDaoImp implements PessoaDao{
	public PessoaDaoImp()
    {
    }

    public void setSession(Session session)
    {
        this.session = session;
    }

    public void save(Pessoa pessoa)
    {
        Transaction t = session.beginTransaction();
        session.save(pessoa);
        t.commit();
    }

    public List<Pessoa> list()
    {
        Transaction t = session.beginTransaction();
        List lista = session.createQuery("from Pessoa").list();
        t.commit();
        return lista;
    }

    public void remove(Pessoa pessoa)
    {
        Transaction t = session.beginTransaction();
        session.delete(pessoa);
        t.commit();
    }

    public void update(Pessoa pessoa)
    {
        Transaction t = session.beginTransaction();
        session.update(pessoa);
        t.commit();
    }

    public Pessoa getPessoa(Pessoa pessoa)
    {
        return (Pessoa)session.load(Pessoa.class, pessoa.getCpf());
    }

    private Session session;

}
