package models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UnidadeUsuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUnidadeUsuario;
	
	@ManyToOne
	@JoinColumn(name="nome")
	private Unidade unidade;
	
	@ManyToOne
	@JoinColumn(name="login")
	private Usuario usuario;

	public int getIdUnidadeUsuario() {
		return idUnidadeUsuario;
	}

	public void setIdUnidadeUsuario(int idUnidadeUsuario) {
		this.idUnidadeUsuario = idUnidadeUsuario;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
