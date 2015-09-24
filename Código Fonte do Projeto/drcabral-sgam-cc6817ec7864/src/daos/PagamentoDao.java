package daos;

import java.util.List;

import models.Pagamento;
import models.UnidadeAluno;

public interface PagamentoDao {
	public void save(Pagamento pagamento);
	public Pagamento getPagamento(Pagamento pagamento);
	public List<Pagamento> list();
	public List<Pagamento> list(UnidadeAluno unidadeAluno);
	public void remove(Pagamento pagamento);
	public void update(Pagamento pagamento);

}
