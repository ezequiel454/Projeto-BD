package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="cpf")  //dizendo a qual chave primaria o extends se relaciona na especialização
public class Instrutor extends Pessoa{

	private static final long serialVersionUID = 1L;

	private String cref;
	
	@OneToMany(mappedBy="instrutor", targetEntity=InstrutorModalidade.class, fetch=FetchType.LAZY)
	private List<InstrutorModalidade> instrutorModalidades;

	public String getCref() {
		return cref;
	}

	public void setCref(String cref) {
		this.cref = cref;
	}

	public List<InstrutorModalidade> getInstrutorModalidades() {
		return instrutorModalidades;
	}

	public void setInstrutorModalidades(
			List<InstrutorModalidade> instrutorModalidades) {
		this.instrutorModalidades = instrutorModalidades;
	}
}
