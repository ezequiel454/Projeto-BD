package business;

import daos.DAOFactory;
import daos.PessoaDao;
import java.util.List;
import models.Pessoa;

public class PessoaBusiness {
	public PessoaBusiness() {
		setPessoaDao(DAOFactory.createPessoa());
	}

	public void setPessoaDao(PessoaDao pessoaDao) {
		this.pessoaDao = pessoaDao;
	}

	public void salvar(Pessoa pessoa) {
		pessoaDao.save(pessoa);
	}

	public void update(Pessoa pessoa) {
		pessoaDao.update(pessoa);
	}

	public void deletar(Pessoa pessoa) {
		pessoaDao.remove(pessoa);
	}

	public Pessoa procurar(Pessoa pessoa) {
		return pessoaDao.getPessoa(pessoa);
	}

	public List<Pessoa> listar() {
		return pessoaDao.list();
	}

	private PessoaDao pessoaDao;

}
