package business;

import java.util.Date;
import java.util.List;

import models.Aluno;
import models.Unidade;
import models.UnidadeAluno;
import daos.DAOFactory;
import daos.UnidadeAlunoDao;

public class UnidadeAlunoBusiness {
	private UnidadeAlunoDao unidadeAlunoDao;
	
	 public UnidadeAlunoBusiness() {
	    	setUnidadeAlunoDao(DAOFactory.createUnidadeAluno());
	 }
	
    public void setUnidadeAlunoDao(UnidadeAlunoDao unidadeAlunoDao)
    {
        this.unidadeAlunoDao = unidadeAlunoDao;
    }

    public void salvar(UnidadeAluno unidadeAluno)
    {
    	this.unidadeAlunoDao.save(unidadeAluno);
    }

    public void update(UnidadeAluno unidadeAluno)
    {
    	this.unidadeAlunoDao.update(unidadeAluno);
    }

    public void deletar(UnidadeAluno unidadeAluno)
    {
        this.unidadeAlunoDao.remove(unidadeAluno);
    }

    public UnidadeAluno procurar(UnidadeAluno unidadeAluno)
    {
        return this.unidadeAlunoDao.getUnidadeAluno(unidadeAluno);
    }

    public List<UnidadeAluno> listar()
    {
        return unidadeAlunoDao.list();
    }
    
    public List<UnidadeAluno> listar(Unidade unidade)
    {
        return unidadeAlunoDao.list(unidade);
    }
    
    public List<UnidadeAluno> listarAtrasados(Unidade unidade){
    	return this.unidadeAlunoDao.listAtrasados(unidade);
    }
    
    public List<UnidadeAluno> listarPagos(Unidade unidade){
    	return this.unidadeAlunoDao.listPagos(unidade);
    }
    
    public List<UnidadeAluno> listar(Unidade unidade, String nomePesquisado){
    	return this.unidadeAlunoDao.list(unidade, nomePesquisado);
    }
    
    public List<UnidadeAluno> listar(Unidade unidade, Date inicio, Date fim)
    {
        return unidadeAlunoDao.list(unidade, inicio, fim);
    }
    
    public List<UnidadeAluno> listarNaoFezAvaliacao(Unidade unidade)
    {
        return unidadeAlunoDao.listNaoFezAvaliacao(unidade);
    }
    
    public List<UnidadeAluno> listarFezAvaliacao(Unidade unidade)
    {
        return unidadeAlunoDao.listFezAvaliacao(unidade);
    }
}
