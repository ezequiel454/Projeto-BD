package controller;

import java.util.ArrayList;
import java.util.Collections;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import models.LogAcesso;
import models.Unidade;
import models.UnidadeUsuario;
import business.LogAcessoBusiness;
import business.UnidadeUsuarioBusiness;

@ManagedBean(name = "logAcesso")
@SessionScoped

public class LogAcessoBean {
	
	@ManagedProperty(value="#{login}")
	private LoginBean loginBean;
	
	private LogAcesso logAcesso = null;
	private DataModel<LogAcesso> model;
	
	@PostConstruct
	public void init() {
		if (logAcesso == null) {
			logAcesso = new LogAcesso();
		}
	}
	
	public DataModel<LogAcesso> getTodos() throws Exception {
		LogAcessoBusiness laB = new LogAcessoBusiness();
		Unidade unidade= new Unidade();
		unidade.setNome(loginBean.getNomeUnidade());
		ArrayList<LogAcesso> logAcessos = new ArrayList<LogAcesso>();
		logAcessos = (ArrayList<LogAcesso>) laB.listar(); //lista todos os acessos de todas as unidades
		
		UnidadeUsuarioBusiness uuB = new UnidadeUsuarioBusiness();
		ArrayList<UnidadeUsuario> unidadesUsuario = (ArrayList<UnidadeUsuario>) uuB.listar(unidade);
		
		boolean deveRemover = false;
		for (int i = 0; i < logAcessos.size(); i++) {
			for (int j = 0; j < unidadesUsuario.size(); j++) {
				if(!logAcessos.get(i).getUsuario().getLogin().equals(unidadesUsuario.get(j).getUsuario().getLogin())){
					deveRemover = true;
				}else{
					deveRemover = false;
					break;
				}
			}
			
			if(deveRemover){
				logAcessos.remove(i);
				i--;
				deveRemover = false;
			}
		}
		Collections.reverse(logAcessos);
		model = new ListDataModel<LogAcesso>(logAcessos);
		return model;
	}
	
	public LogAcesso getLogAcesso() {
		return logAcesso;
	}

	public void setLogAcesso(LogAcesso logAcesso) {
		this.logAcesso = logAcesso;
	}
	
	public void cadastrarLogAcesso(){
		LogAcessoBusiness logAcessoBusiness = new LogAcessoBusiness();
		logAcessoBusiness.salvar(logAcesso);
	}
	
	public String editar(){
		return "editarLogAcesso";
	}

	public DataModel<LogAcesso> getModel() {
		return model;
	}

	public void setModel(DataModel<LogAcesso> model) {
		this.model = model;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}


}
