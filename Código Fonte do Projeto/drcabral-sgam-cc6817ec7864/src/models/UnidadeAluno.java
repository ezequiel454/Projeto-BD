package models;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class UnidadeAluno implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUnidadeAluno;
	
	@ManyToOne
	@JoinColumn(name="nome")
	private Unidade unidade;
	
	@ManyToOne
	@JoinColumn(name="cpf")
	private Aluno aluno;
	
	private int numMatricula;
	private Date dataMatricula;
	private String diaPagamento;
	private String situacaoPagamento;
	private String scanQuestionario;
	private String scanAutorizacaoResponsavel;
	private String scanAvaliacaoFisica;
	
	@OneToMany(mappedBy="unidadeAluno", targetEntity=Pagamento.class, fetch=FetchType.LAZY)
	private List<Pagamento> pagamentos;

	public int getIdUnidadeAluno() {
		return idUnidadeAluno;
	}

	public void setIdUnidadeAluno(int idUnidadeAluno) {
		this.idUnidadeAluno = idUnidadeAluno;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public int getNumMatricula() {
		return numMatricula;
	}

	public void setNumMatricula(int numMatricula) {
		this.numMatricula = numMatricula;
	}

	public Date getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(Date dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public String getDiaPagamento() {
		return diaPagamento;
	}

	public void setDiaPagamento(String diaPagamento) {
		this.diaPagamento = diaPagamento;
	}

	public String getSituacaoPagamento() {
		return situacaoPagamento;
	}

	public void setSituacaoPagamento(String situacaoPagamento) {
		this.situacaoPagamento = situacaoPagamento;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}

	public String getScanQuestionario() {
		return scanQuestionario;
	}

	public void setScanQuestionario(String scanQuestionario) {
		this.scanQuestionario = scanQuestionario;
	}

	public String getScanAutorizacaoResponsavel() {
		return scanAutorizacaoResponsavel;
	}

	public void setScanAutorizacaoResponsavel(String scanAutorizacaoResponsavel) {
		this.scanAutorizacaoResponsavel = scanAutorizacaoResponsavel;
	}

	public String getScanAvaliacaoFisica() {
		return scanAvaliacaoFisica;
	}

	public void setScanAvaliacaoFisica(String scanAvaliacaoFisica) {
		this.scanAvaliacaoFisica = scanAvaliacaoFisica;
	}

}
