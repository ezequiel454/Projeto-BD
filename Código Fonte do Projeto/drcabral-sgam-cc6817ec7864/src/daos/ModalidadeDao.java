package daos;

import java.util.List;

import models.Modalidade;

public interface ModalidadeDao {
	
	public void save(Modalidade modalidade);
	public Modalidade getModalidade(Modalidade modalidade);
	public List<Modalidade> list();
	public void remove(Modalidade modalidade);
	public void update(Modalidade modalidade);
}
