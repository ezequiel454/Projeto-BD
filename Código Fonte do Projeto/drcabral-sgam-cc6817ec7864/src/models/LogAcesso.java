package models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class LogAcesso implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idLogAcesso;
	
	@ManyToOne
	@JoinColumn(name="login")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="idAcesso")
	private Acesso acesso;

	public int getIdLogAcesso() {
		return idLogAcesso;
	}

	public void setIdLogAcesso(int idLogAcesso) {
		this.idLogAcesso = idLogAcesso;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Acesso getAcesso() {
		return acesso;
	}

	public void setAcesso(Acesso acesso) {
		this.acesso = acesso;
	}

}
