package models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String login;
	
	private String email;
	private String senha;
	
	@OneToMany(mappedBy="usuario", targetEntity=LogAcesso.class, fetch=FetchType.LAZY)
	private List<LogAcesso> logAcesso;
	
	@OneToMany(mappedBy="usuario", targetEntity=UnidadeUsuario.class, fetch=FetchType.LAZY)
	private List<UnidadeUsuario> unidadeUsuario;
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public List<LogAcesso> getLogAcesso() {
		return logAcesso;
	}
	public void setLogAcesso(List<LogAcesso> logAcesso) {
		this.logAcesso = logAcesso;
	}

	public List<UnidadeUsuario> getUnidadeUsuario() {
		return unidadeUsuario;
	}
	public void setUnidadeUsuario(List<UnidadeUsuario> unidadeUsuario) {
		this.unidadeUsuario = unidadeUsuario;
	}

}
