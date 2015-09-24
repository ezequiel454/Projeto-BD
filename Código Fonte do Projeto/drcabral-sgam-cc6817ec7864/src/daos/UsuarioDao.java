package daos;

import java.util.List;
import models.Usuario;

public interface UsuarioDao {
	public void save(Usuario usuario);
	public Usuario getUsuario(Usuario usuario);
	public List<Usuario> list();
	public void remove(Usuario usuario);
	public void update(Usuario usuario);

}
