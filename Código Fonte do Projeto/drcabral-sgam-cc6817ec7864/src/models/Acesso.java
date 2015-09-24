package models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Acesso implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private int idAcesso;
	
	private Date dataAcesso;
	private String tipo;
	private String nomeTabelaAfetada;
	
	@OneToMany(mappedBy="acesso", targetEntity=LogAcesso.class, fetch=FetchType.LAZY)
	private List<LogAcesso> logAcesso;

	public List<LogAcesso> getLogAcesso() {
		return logAcesso;
	}

	public void setLogAcesso(List<LogAcesso> logAcesso) {
		this.logAcesso = logAcesso;
	}

	public int getIdAcesso() {
		return idAcesso;
	}

	public void setIdAcesso(int idAcesso) {
		this.idAcesso = idAcesso;
	}

	public Date getDataAcesso() {
		return dataAcesso;
	}

	public void setDataAcesso(Date dataAcesso) {
		this.dataAcesso = dataAcesso;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNomeTabelaAfetada() {
		return nomeTabelaAfetada;
	}

	public void setNomeTabelaAfetada(String nomeTabelaAfetada) {
		this.nomeTabelaAfetada = nomeTabelaAfetada;
	}

}
