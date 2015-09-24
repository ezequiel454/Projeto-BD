package daos;

import java.util.List;

import models.Pessoa;

public interface PessoaDao {
    public  void save(Pessoa pessoa);

    public  Pessoa getPessoa(Pessoa pessoa);

    public  List<Pessoa> list();

    public  void remove(Pessoa pessoa);

    public  void update(Pessoa pessoa);
}
