package daos;

import java.util.List;

import models.Unidade;
import models.UnidadeUsuario;
import models.Usuario;

public interface UnidadeUsuarioDao {
	
	 public  void save(UnidadeUsuario unidadeUsuario);

	    public  UnidadeUsuario getUnidadeUsuario(UnidadeUsuario unidadeUsuario);
	    
	    public List<UnidadeUsuario> procurarUnidades(Usuario usuario);

	    public  List<UnidadeUsuario> list();
	    
	    public  List<UnidadeUsuario> list(Unidade usuario);

	    public  void remove(UnidadeUsuario unidadeUsuario);

	    public  void update(UnidadeUsuario unidadeUsuario);
}
