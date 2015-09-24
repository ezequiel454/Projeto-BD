package daos;

import util.HibernateUtil;
 
public class DAOFactory {
    
    public static UnidadeDao createUnidade(){
    	UnidadeDaoImp unidadeDaoImpl = new UnidadeDaoImp();
        unidadeDaoImpl.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
        return unidadeDaoImpl;
    }
    
    public static EnderecoUnidadeDao createEnderecoUnidade(){
    	EnderecoUnidadeDaoImp enderecoUnidadeDaoImpl = new EnderecoUnidadeDaoImp();
        enderecoUnidadeDaoImpl.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
        return enderecoUnidadeDaoImpl;
    }
    
    public static TelefoneUnidadeDao createTelefoneUnidade(){
    	TelefoneUnidadeDaoImp telefoneUnidadeDaoImpl = new TelefoneUnidadeDaoImp();
        telefoneUnidadeDaoImpl.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
        return telefoneUnidadeDaoImpl;
  }
    
    public static EnderecoPessoaDao createEnderecoPessoa() {
		EnderecoPessoaDaoImp enderecoPessoaDaoImpl = new EnderecoPessoaDaoImp();
		enderecoPessoaDaoImpl.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return enderecoPessoaDaoImpl;
	}

	public static PessoaDao createPessoa() {
		PessoaDaoImp pessoaDaoImpl = new PessoaDaoImp();
		pessoaDaoImpl.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return pessoaDaoImpl;
	}

	public static TelefonePessoaDao createTelefonePessoa() {
		TelefonePessoaDaoImp telefonePessoaDaoImpl = new TelefonePessoaDaoImp();
		telefonePessoaDaoImpl.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return telefonePessoaDaoImpl;
	}

	public static UnidadeModalidadeDao createUnidadeModalidade() {
		UnidadeModalidadeDaoImp unidadeModalidadeDaoImpl = new UnidadeModalidadeDaoImp();
		unidadeModalidadeDaoImpl.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return unidadeModalidadeDaoImpl;
	}
	
	public static DespesaDao createDespesa() {
		DespesaDaoImp despesaDaoImpl = new DespesaDaoImp();
		despesaDaoImpl.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return despesaDaoImpl;
	}
	
	public static InstrutorModalidadeUnidadeDao createInstrutorModalidadeUnidade() {
		InstrutorModalidadeUnidadeDaoImp instrutorModalidadeUnidadeDaoImp = new InstrutorModalidadeUnidadeDaoImp();
		instrutorModalidadeUnidadeDaoImp.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return instrutorModalidadeUnidadeDaoImp;
	}
	
	public static ModalidadeDao createModalidade() {
		ModalidadeDaoImp modalidadeDaoImpl = new ModalidadeDaoImp();
		modalidadeDaoImpl.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return modalidadeDaoImpl;
	}
	
	public static UnidadeDespesaDao createUnidadeDespesa() {
		UnidadeDespesaDaoImp unidadeDespesaDaoImpl = new UnidadeDespesaDaoImp();
		unidadeDespesaDaoImpl.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return unidadeDespesaDaoImpl;
	}
	
    public static InstrutorDao createInstrutor() {
    	InstrutorDaoImp instrutorDaoImpl = new InstrutorDaoImp();
    	instrutorDaoImpl.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
    	return instrutorDaoImpl;
    }
    
    public static InstrutorModalidadeDao createInstrutorModalidade() {
    	InstrutorModalidadeDaoImp instrutorModalidadeDaoImpl = new InstrutorModalidadeDaoImp();
    	instrutorModalidadeDaoImpl.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
    	return instrutorModalidadeDaoImpl;
    }
    
    public static InstrutorModalidadeAlunoDao createInstrutorModalidadeAluno() {
    	InstrutorModalidadeAlunoDaoImp instrutorModalidadeAlunoDaoImpl = new InstrutorModalidadeAlunoDaoImp();
    	instrutorModalidadeAlunoDaoImpl.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
    	return instrutorModalidadeAlunoDaoImpl;
    }
    
    public static AlunoDao createAluno(){
        AlunoDaoImp alunoDaoImpl = new AlunoDaoImp();
        alunoDaoImpl.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
        return alunoDaoImpl;
    }
    
    public static PagamentoDao createPagamento() {
		PagamentoDaoImp pagamentoDaoImpl = new PagamentoDaoImp();
		pagamentoDaoImpl.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return pagamentoDaoImpl;
	}
    
    public static AcessoDao createAcesso() {
		AcessoDaoImp acessoDaoImpl = new AcessoDaoImp();
		acessoDaoImpl.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return acessoDaoImpl;
	}
    
    public static LogAcessoDao createLogAcesso() {
		LogAcessoDaoImp logAcessoDaoImpl = new LogAcessoDaoImp();
		logAcessoDaoImpl.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return logAcessoDaoImpl;
	}
    
    public static UnidadeAlunoDao createUnidadeAluno() {
		UnidadeAlunoDaoImp unidadeAlunoDaoImpl = new UnidadeAlunoDaoImp();
		unidadeAlunoDaoImpl.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return unidadeAlunoDaoImpl;
	}
    
    public static UsuarioDao createUsuario() {
    	UsuarioDaoImp usuarioDaoImpl = new UsuarioDaoImp();
    	usuarioDaoImpl.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return usuarioDaoImpl;
	}
    
    public static UnidadeUsuarioDao createUnidadeUsuario() {
    	UnidadeUsuarioDaoImp unidadeUsuarioDaoImpl = new UnidadeUsuarioDaoImp();
    	unidadeUsuarioDaoImpl.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return unidadeUsuarioDaoImpl;
	}
}