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
public class UnidadeModalidade implements Serializable{

	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUnidadeModalidade;
	
	@ManyToOne
	@JoinColumn(name="idModalidade")
	private Modalidade modalidade;
	
	@ManyToOne
	@JoinColumn(name="nome")
	private Unidade unidade;
	
	private double valorMensalidade;
	private Date dataInicio;
	private Date datafim;

	public int getIdUnidadeModalidade() {
		return idUnidadeModalidade;
	}

	public void setIdUnidadeModalidade(int idUnidadeModalidade) {
		this.idUnidadeModalidade = idUnidadeModalidade;
	}

	public Modalidade getModalidade() {
		return modalidade;
	}

	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public double getValorMensalidade() {
		return valorMensalidade;
	}

	public void setValorMensalidade(double valorMensalidade) {
		this.valorMensalidade = valorMensalidade;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDatafim() {
		return datafim;
	}

	public void setDatafim(Date datafim) {
		this.datafim = datafim;
	}

}
