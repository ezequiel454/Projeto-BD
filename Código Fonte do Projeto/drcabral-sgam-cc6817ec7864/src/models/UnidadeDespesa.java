package models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UnidadeDespesa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUnidadeDespesa;
	
	@ManyToOne
	@JoinColumn(name="nome")
	private Unidade unidade;
	
	@ManyToOne
	@JoinColumn(name="idDespesa")
	private Despesa despesa;
	
	private double valor;
	private Date data;
	
	public int getIdUnidadeDespesa() {
		return idUnidadeDespesa;
	}
	public void setIdUnidadeDespesa(int idUnidadeDespesa) {
		this.idUnidadeDespesa = idUnidadeDespesa;
	}
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	public Despesa getDespesa() {
		return despesa;
	}
	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}

}
