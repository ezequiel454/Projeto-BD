package controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import models.UnidadeDespesa;
import business.UnidadeDespesaBusiness;

@ManagedBean(name = "unidadeDespesa")
@SessionScoped
public class UnidadeDespesaBean {
	
private UnidadeDespesa unidadeDespesa = null;
	
	@PostConstruct
	public void init() {
		if (unidadeDespesa == null) {
			unidadeDespesa = new UnidadeDespesa();
		}
	}
	
	public UnidadeDespesa getUnidadeDespesa() {
		return unidadeDespesa;
	}

	public void setUnidadeDespesa(UnidadeDespesa unidadeDespesa) {
		this.unidadeDespesa = unidadeDespesa;
	}
	
	public void cadastrarUnidadeDespesa(){
		UnidadeDespesaBusiness unidadeDespesaBusiness = new UnidadeDespesaBusiness();
		unidadeDespesaBusiness.salvar(unidadeDespesa);
	}
	
	public String editar(){
		return "editarUnidadeDespesa";
	}


}
