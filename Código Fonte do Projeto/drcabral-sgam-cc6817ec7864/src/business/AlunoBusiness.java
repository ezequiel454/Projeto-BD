package business;

import java.util.List;

import models.Aluno;
import models.TelefonePessoa;
import models.UnidadeAluno;
import daos.AlunoDao;
import daos.DAOFactory;

public class AlunoBusiness {
	
	private AlunoDao alunoDao;      
    
    public AlunoBusiness() {
    	setAlunoDao(DAOFactory.createAluno());
    }
     
    public void setAlunoDao(AlunoDao alunoDao) {
        this.alunoDao = alunoDao;
    }     
    
    public void salvar(Aluno aluno) {
    	this.alunoDao.save(aluno);
    }
    
    public void update(Aluno aluno) {
    	this.alunoDao.update(aluno);
    }
    
    public void deletar(Aluno aluno) {
    	this.alunoDao.remove(aluno);
    }
    
    public Aluno procurar(Aluno aluno) {
    	return this.alunoDao.getAluno(aluno);
    }
    
    public List<Aluno> listar() {
    	return this.alunoDao.list();
    }
    
    public List<Aluno> listar(UnidadeAluno unidadeAluno) {
    	return this.alunoDao.list(unidadeAluno);
    }

}
