package daos;

import java.util.List;

import models.Instrutor;

public interface InstrutorDao {
	public void save(Instrutor instrutor);
	public Instrutor getInstrutor(Instrutor instrutor);
	public List<Instrutor> list();
	public void remove(Instrutor instrutor);
	public void update(Instrutor instrutor);
}
