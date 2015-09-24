package controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import business.EnderecoPessoaBusiness;
import models.EnderecoPessoa;

@ManagedBean(name="EnderecoPessoa")
@SessionScoped
public class EnderecoPessoaBean {
	
    private EnderecoPessoa ep;

	
	 public EnderecoPessoaBean()
	    {
	        ep = null;
	    }

	    @PostConstruct
	    public void init()
	    {
	        if(ep == null)
	            ep = new EnderecoPessoa();
	    }

	    public EnderecoPessoa getEnderecoPessoa()
	    {
	        return ep;
	    }

	    public void setEnderecoPessoa(EnderecoPessoa ep)
	    {
	        this.ep = ep;
	    }

	    public void cadastrarEnderecoPessoa()
	    {
	        EnderecoPessoaBusiness enderecoPessoaBusiness = new EnderecoPessoaBusiness();
	        enderecoPessoaBusiness.salvar(ep);
	    }

	    public String editarEnderecoPessoa()
	    {
	        return "editarEnderecoPessoa";
	    }


}
