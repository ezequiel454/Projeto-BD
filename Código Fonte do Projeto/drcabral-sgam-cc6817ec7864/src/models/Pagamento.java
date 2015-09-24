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
public class Pagamento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPagamento;
	
	@ManyToOne
	@JoinColumn(name="idUnidadeAluno")
	private UnidadeAluno unidadeAluno;
	
	private Date dataPagamentoRealizado;
	private String mesPago;
	private String anoPago;
	private double desconto;
	private double valorPago;
	
	public int getIdPagamento() {
		return idPagamento;
	}
	public void setIdPagamento(int idPagamento) {
		this.idPagamento = idPagamento;
	}
	public UnidadeAluno getUnidadeAluno() {
		return unidadeAluno;
	}
	public void setUnidadeAluno(UnidadeAluno unidadeAluno) {
		this.unidadeAluno = unidadeAluno;
	}
	public Date getDataPagamentoRealizado() {
		return dataPagamentoRealizado;
	}
	public void setDataPagamentoRealizado(Date dataPagamentoRealizado) {
		this.dataPagamentoRealizado = dataPagamentoRealizado;
	}
	public String getMesPago() {
		return mesPago;
	}
	public void setMesPago(String mesPago) {
		this.mesPago = mesPago;
	}
	public String getAnoPago() {
		return anoPago;
	}
	public void setAnoPago(String anoPago) {
		this.anoPago = anoPago;
	}
	public double getDesconto() {
		return desconto;
	}
	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
	public double getValorPago() {
		return valorPago;
	}
	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}
	

}
