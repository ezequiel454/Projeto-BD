package daos;

import java.util.List;
import java.io.Serializable;

import models.EnderecoPessoa;

public interface EnderecoPessoaDao {
	public  void save(EnderecoPessoa enderecopessoa);

    public  EnderecoPessoa getEnderecoPessoa(EnderecoPessoa enderecopessoa);

    public  List<EnderecoPessoa> list();

    public  void remove(EnderecoPessoa enderecopessoa);

    public  void update(EnderecoPessoa enderecopessoa);

}
