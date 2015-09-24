package business;

import java.util.List;

import models.Aluno;
import models.Instrutor;
import models.TelefonePessoa;
import daos.DAOFactory;
import daos.TelefonePessoaDao;

public class TelefonePessoaBusiness {
	 public TelefonePessoaBusiness()
	    {
	        setTelefonePessoaDao(DAOFactory.createTelefonePessoa());
	    }

	    public void setTelefonePessoaDao(TelefonePessoaDao tpDao)
	    {
	        this.tpDao = tpDao;
	    }

	    public void salvar(TelefonePessoa tp)
	    {
	        tpDao.save(tp);
	    }

	    public void update(TelefonePessoa tp)
	    {
	        tpDao.update(tp);
	    }

	    public void deletar(TelefonePessoa tp)
	    {
	        tpDao.remove(tp);
	    }

	    public TelefonePessoa procurar(TelefonePessoa tp)
	    {
	        return tpDao.getTelefonePessoa(tp);
	    }

	    public List<TelefonePessoa> listar()
	    {
	        return tpDao.list();
	    }
	    
	    public List<TelefonePessoa> listar(Aluno aluno)
	    {
	        return tpDao.list(aluno);
	    }

	    public List<TelefonePessoa> listar(Instrutor instrutor)
	    {
	        return tpDao.list(instrutor);
	    }
	    
	    private TelefonePessoaDao tpDao;
}
