package daos;

import java.util.List;

import models.Unidade;
import models.UnidadeModalidade;

public interface UnidadeModalidadeDao {
	  public  void save(UnidadeModalidade um);
	  public  UnidadeModalidade getUnidadeModalidade(UnidadeModalidade um);
	  public  List<UnidadeModalidade> list();
	  public  List<UnidadeModalidade> list(Unidade unidade);
	  public  List<UnidadeModalidade> listPorMensalidade(Unidade unidade);	  
	  public  void remove(UnidadeModalidade um);
	  public  void update(UnidadeModalidade um);
}
