package models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Despesa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private int idDespesa;
	
	@OneToMany(mappedBy="despesa", targetEntity=UnidadeDespesa.class, fetch=FetchType.EAGER) //mudei para eager pq com lazy ele não carregava as coisas que eu precisava na lista, lazy só faz por demanda
	private List<UnidadeDespesa> unidadeDespesa;
	
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<UnidadeDespesa> getUnidadeDespesa() {
		return unidadeDespesa;
	}

	public void setUnidadeDespesa(List<UnidadeDespesa> unidadeDespesa) {
		this.unidadeDespesa = unidadeDespesa;
	}

	public int getIdDespesa() {
		return idDespesa;
	}

	public void setIdDespesa(int idDespesa) {
		this.idDespesa = idDespesa;
	}

}
