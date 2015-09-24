package business;

import java.util.List;

import models.Unidade;
import models.UnidadeUsuario;
import models.Usuario;
import daos.DAOFactory;
import daos.UnidadeUsuarioDao;

public class UnidadeUsuarioBusiness {
	
private UnidadeUsuarioDao unidadeUsuarioDao;      
    
    public UnidadeUsuarioBusiness() {
    	setUnidadeUsuarioDao(DAOFactory.createUnidadeUsuario());
    }
     
    public void setUnidadeUsuarioDao(UnidadeUsuarioDao unidadeUsuarioDao) {
        this.unidadeUsuarioDao = unidadeUsuarioDao;
    }     
    
    public void salvar(UnidadeUsuario unidadeUsuario) {
    	this.unidadeUsuarioDao.save(unidadeUsuario);
    }
    
    public void update(UnidadeUsuario unidadeUsuario) {
    	this.unidadeUsuarioDao.update(unidadeUsuario);
    }
    
    public void deletar(UnidadeUsuario unidadeUsuario) {
    	this.unidadeUsuarioDao.remove(unidadeUsuario);
    }
    
    public UnidadeUsuario procurar(UnidadeUsuario unidadeUsuario) {
    	return this.unidadeUsuarioDao.getUnidadeUsuario(unidadeUsuario);
    }
    
    public List<UnidadeUsuario> listar() {
    	return this.unidadeUsuarioDao.list();
    }
    
    public List<UnidadeUsuario> listar(Unidade unidade) {
    	return this.unidadeUsuarioDao.list(unidade);
    }
    
    public List<UnidadeUsuario> procurarUnidades(Usuario usuario){
    	return this.unidadeUsuarioDao.procurarUnidades(usuario);
    }
}
