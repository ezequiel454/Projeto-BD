package business;

import daos.DAOFactory;
import daos.EnderecoPessoaDao;
import java.util.List;
import models.EnderecoPessoa;

public class EnderecoPessoaBusiness {
    public EnderecoPessoaBusiness()
    {
        setEnderecoPessoaDao(DAOFactory.createEnderecoPessoa());
    }

    public void setEnderecoPessoaDao(EnderecoPessoaDao epDao)
    {
        this.epDao = epDao;
    }

    public void salvar(EnderecoPessoa ep)
    {
        epDao.save(ep);
    }

    public void update(EnderecoPessoa ep)
    {
        epDao.update(ep);
    }

    public void deletar(EnderecoPessoa ep)
    {
        epDao.remove(ep);
    }

    public EnderecoPessoa procurar(EnderecoPessoa ep)
    {
        return epDao.getEnderecoPessoa(ep);
    }

    public List<EnderecoPessoa> listar()
    {
        return epDao.list();
    }

    private EnderecoPessoaDao epDao;
}
