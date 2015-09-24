package daos;

import java.io.Serializable;
import java.util.List;

import models.EnderecoUnidade;

public interface EnderecoUnidadeDao {
	public void save(EnderecoUnidade enderecoUnidade);
	public EnderecoUnidade getEnderecoUnidade(EnderecoUnidade enderecoUnidade);
	public List<EnderecoUnidade> list();
	public void remove(EnderecoUnidade enderecoUnidade);
	public void update(EnderecoUnidade enderecoUnidade);
}
