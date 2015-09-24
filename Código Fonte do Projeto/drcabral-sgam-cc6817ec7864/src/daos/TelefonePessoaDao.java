package daos;

import java.util.List;

import models.Aluno;
import models.Instrutor;
import models.TelefonePessoa;

public interface TelefonePessoaDao {
	  public  void save(TelefonePessoa paramTelefonePessoa);
	  
	  public  TelefonePessoa getTelefonePessoa(TelefonePessoa paramTelefonePessoa);
	  
	  public  List<TelefonePessoa> list();
	  
	  public  List<TelefonePessoa> list(Aluno aluno);
	  
	  public List<TelefonePessoa> list(Instrutor instrutor);
	  
	  public  void remove(TelefonePessoa paramTelefonePessoa);
	  
	  public  void update(TelefonePessoa paramTelefonePessoa);
}
