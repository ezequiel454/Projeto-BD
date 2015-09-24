package daos;

import java.util.Date;
import java.util.List;

import models.Unidade;
import models.UnidadeAluno;

public interface UnidadeAlunoDao {
	public  void save(UnidadeAluno unidadeAluno);	  
	public  UnidadeAluno getUnidadeAluno(UnidadeAluno unidadeAluno);
	public  List<UnidadeAluno> list();
	public  List<UnidadeAluno> list(Unidade unidade);
	public List<UnidadeAluno> listAtrasados(Unidade unidade);
	public List<UnidadeAluno> listPagos(Unidade unidade);
	public List<UnidadeAluno> list(Unidade unidade, String nomePesquisado);
	public  List<UnidadeAluno> list(Unidade unidade, Date inicio, Date fim);
	public  List<UnidadeAluno> listFezAvaliacao(Unidade unidade);
	public  List<UnidadeAluno> listNaoFezAvaliacao(Unidade unidade);
	public  void remove(UnidadeAluno unidadeAluno);
	public  void update(UnidadeAluno unidadeAluno);

}
