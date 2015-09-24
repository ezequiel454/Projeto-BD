package business;

import java.util.List;

import models.Usuario;
import daos.DAOFactory;
import daos.UsuarioDao;

public class UsuarioBusiness {
	private UsuarioDao usuarioDao;
	
	public UsuarioBusiness() {
    	setUsuarioDao(DAOFactory.createUsuario());
    }
     
    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }     
    
    public void salvar(Usuario usuario) {
    	this.usuarioDao.save(usuario);
    }
    
    public void update(Usuario usuario) {
    	this.usuarioDao.update(usuario);
    }
    
    public void deletar(Usuario usuario) {
    	this.usuarioDao.remove(usuario);
    }
    
    public Usuario procurar(Usuario usuario) {
    	return this.usuarioDao.getUsuario(usuario);
    }
    
    public List<Usuario> listar() {
    	return this.usuarioDao.list();
    }
}
