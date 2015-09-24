package daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import models.Usuario;

public class UsuarioDaoImp implements UsuarioDao {
	private Session session;
	
	public void setSession(Session session) {
        this.session = session;
    }

	public void save(Usuario usuario) {
		Transaction t = session.beginTransaction();
        session.save(usuario);
        t.commit();
	}

	public Usuario getUsuario(Usuario usuario) {
		session.beginTransaction();
        return (Usuario)session.get(Usuario.class, usuario.getLogin());
	}

	public List<Usuario> list() {
		Transaction t = session.beginTransaction();
        List lista = session.createQuery("from Usuario").list();
        t.commit();
        return lista;
	}

	public void remove(Usuario usuario) {
		Transaction t = session.beginTransaction();
        session.delete(usuario);
        t.commit();
	}

	public void update(Usuario usuario) {
		Transaction t = session.beginTransaction();
        session.update(usuario);
        t.commit();	
	}

}
