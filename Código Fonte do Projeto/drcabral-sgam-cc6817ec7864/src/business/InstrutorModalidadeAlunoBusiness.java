package business;

import java.util.List;

import models.InstrutorModalidadeAluno;
import models.Modalidade;
import models.Unidade;
import daos.DAOFactory;
import daos.InstrutorModalidadeAlunoDao;

public class InstrutorModalidadeAlunoBusiness {
	
	private InstrutorModalidadeAlunoDao instrutorModalidadeAlunoDao;      
    
    public InstrutorModalidadeAlunoBusiness() {
    	setInstrutorModalidadeAlunoDao(DAOFactory.createInstrutorModalidadeAluno());
    }
     
    public void setInstrutorModalidadeAlunoDao(InstrutorModalidadeAlunoDao instrutorModalidadeAlunoDao) {
        this.instrutorModalidadeAlunoDao = instrutorModalidadeAlunoDao;
    }     
    
    public void salvar(InstrutorModalidadeAluno instrutorModalidadeAluno) {
    	this.instrutorModalidadeAlunoDao.save(instrutorModalidadeAluno);
    }
    
    public void update(InstrutorModalidadeAluno instrutorModalidadeAluno) {
    	this.instrutorModalidadeAlunoDao.update(instrutorModalidadeAluno);
    }
    
    public void deletar(InstrutorModalidadeAluno instrutorModalidadeAluno) {
    	this.instrutorModalidadeAlunoDao.remove(instrutorModalidadeAluno);
    }
    
    public InstrutorModalidadeAluno procurar(InstrutorModalidadeAluno instrutorModalidadeAluno) {
    	return this.instrutorModalidadeAlunoDao.getInstrutorModalidadeAluno(instrutorModalidadeAluno);
    }
    
    public List<InstrutorModalidadeAluno> listar() {
    	return this.instrutorModalidadeAlunoDao.list();
    }

    public List<InstrutorModalidadeAluno> listar(Modalidade modalidade, Unidade unidade) {
    	return this.instrutorModalidadeAlunoDao.list(modalidade, unidade);
    }
}
