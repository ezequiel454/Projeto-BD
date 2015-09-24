package business;

import java.util.List;

import models.Unidade;
import models.UnidadeModalidade;
import daos.DAOFactory;
import daos.UnidadeModalidadeDao;

public class UnidadeModalidadeBusiness {
	 public UnidadeModalidadeBusiness()
	    {
	        setUnidadeModalidadeDao(DAOFactory.createUnidadeModalidade());
	    }

	    public void setUnidadeModalidadeDao(UnidadeModalidadeDao umDao)
	    {
	        this.umDao = umDao;
	    }

	    public void salvar(UnidadeModalidade um)
	    {
	        umDao.save(um);
	    }

	    public void update(UnidadeModalidade um)
	    {
	        umDao.update(um);
	    }

	    public void deletar(UnidadeModalidade um)
	    {
	        umDao.remove(um);
	    }

	    public UnidadeModalidade procurar(UnidadeModalidade um)
	    {
	        return umDao.getUnidadeModalidade(um);
	    }

	    public List<UnidadeModalidade> listar()
	    {
	        return umDao.list();
	    }
	    
	    public List<UnidadeModalidade> listar(Unidade unidade)
	    {
	        return umDao.list(unidade);
	    }
	    
	    public List<UnidadeModalidade> listarPorMensalidade(Unidade unidade)
	    {
	        return umDao.listPorMensalidade(unidade);
	    }

	    private UnidadeModalidadeDao umDao;
}
