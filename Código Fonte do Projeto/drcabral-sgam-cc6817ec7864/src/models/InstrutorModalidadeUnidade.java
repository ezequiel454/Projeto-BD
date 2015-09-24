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
public class InstrutorModalidadeUnidade implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idInstrutorModalidadeUnidade;
	
	@ManyToOne
	@JoinColumn(name="idInstrutorModalidade")
	private InstrutorModalidade instrutorModalidade;
	
	@ManyToOne
	@JoinColumn(name="nome")
	private Unidade unidade;
	
	private double salarioInstrutor;
	private String cargoInstrutor;
	private Date dataContratacao;
	private Date dataFimContrato;
	
	public int getIdInstrutorModalidadeUnidade() {
		return idInstrutorModalidadeUnidade;
	}
	public void setIdInstrutorModalidadeUnidade(int idInstrutorModalidadeUnidade) {
		this.idInstrutorModalidadeUnidade = idInstrutorModalidadeUnidade;
	}
	public InstrutorModalidade getInstrutorModalidade() {
		return instrutorModalidade;
	}
	public void setInstrutorModalidade(InstrutorModalidade instrutorModalidade) {
		this.instrutorModalidade = instrutorModalidade;
	}
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	public double getSalarioInstrutor() {
		return salarioInstrutor;
	}
	public void setSalarioInstrutor(double salarioInstrutor) {
		this.salarioInstrutor = salarioInstrutor;
	}
	public Date getDataContratacao() {
		return dataContratacao;
	}
	public void setDataContratacao(Date dataContratacao) {
		this.dataContratacao = dataContratacao;
	}
	public Date getDataFimContrato() {
		return dataFimContrato;
	}
	public void setDataFimContrato(Date dataFimContrato) {
		this.dataFimContrato = dataFimContrato;
	}
	public String getCargoInstrutor() {
		return cargoInstrutor;
	}
	public void setCargoInstrutor(String cargoInstrutor) {
		this.cargoInstrutor = cargoInstrutor;
	}
	

}
