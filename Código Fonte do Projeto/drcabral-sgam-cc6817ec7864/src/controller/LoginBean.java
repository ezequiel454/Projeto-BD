package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import models.Acesso;
import models.LogAcesso;
import models.Pagamento;
import models.Unidade;
import models.UnidadeAluno;
import models.UnidadeUsuario;
import models.Usuario;
import business.AcessoBusiness;
import business.LogAcessoBusiness;
import business.PagamentoBusiness;
import business.UnidadeAlunoBusiness;
import business.UnidadeBusiness;
import business.UnidadeUsuarioBusiness;
import business.UsuarioBusiness;

@ManagedBean(name = "login")
@SessionScoped
public class LoginBean {
	
	private Usuario usuario = null;
	private String nomeUnidade = null;
	
	@PostConstruct
	public void init() {
		if (usuario == null) {
			usuario = new Usuario();
			nomeUnidade = "";
		}
	}
	
	public void fazerLogin(){
		boolean existeUsuario = validarUsuario();
		boolean tudoOk = false;
		
		if(existeUsuario){
			UnidadeUsuarioBusiness unidadeUsuarioBusiness = new UnidadeUsuarioBusiness();
			ArrayList<UnidadeUsuario> unidades = (ArrayList<UnidadeUsuario>) unidadeUsuarioBusiness.procurarUnidades(usuario);  // PROCURA TODAS OS NOMES DE UNIDADES DO USUARIO
			ArrayList<String> nomesUnidades = new ArrayList<String>();
			
			for (int i = 0; i < unidades.size(); i++) {
				nomesUnidades.add(unidades.get(i).getUnidade().getNome());
			}
			
			for (int i = 0; i < nomesUnidades.size(); i++) {
				if(nomesUnidades.get(i).equals(nomeUnidade)){
					tudoOk = true;
					break;
				}
			}
			
			if(tudoOk){
				
				//inserindo na tabela de acesso
				AcessoBusiness aB = new AcessoBusiness();
				Acesso acesso = new Acesso();
				acesso.setDataAcesso(new Date(System.currentTimeMillis()));
				acesso.setTipo("Login");
				aB.salvar(acesso);
				
				LogAcessoBusiness laB = new LogAcessoBusiness();
				LogAcesso la = new LogAcesso();
				la.setAcesso(acesso);
				la.setUsuario(usuario);
				laB.salvar(la);
				
				//verifica todos os alunos da unidade que vai ser logada e vê a situação de pagamento deles
				verificarSitPagamento();
				
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
			}else{
				//return "error";
			}
		}else{
			//return "error";
		}
	}
	
	public boolean validarUsuario(){
		UsuarioBusiness usuarioBusiness = new UsuarioBusiness();
		Usuario usuAux = usuarioBusiness.procurar(usuario);
		
		if(usuAux.getLogin() != null){
			if(usuAux.getSenha().equals(usuario.getSenha())){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
		
	}

	public List<Unidade> getTodasUnidades(){
		UnidadeBusiness unidadeBusiness = new UnidadeBusiness();
		return unidadeBusiness.listar();
	}
	

	public void verificarSitPagamento(){
		UnidadeAlunoBusiness uaB = new UnidadeAlunoBusiness();
		Unidade unidade = new Unidade();
		unidade.setNome(nomeUnidade);
		ArrayList<UnidadeAluno> alunos = (ArrayList<UnidadeAluno>) uaB.listar(unidade);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String dataAtual = dateFormat.format(date);
		int anoAtual = Integer.parseInt(dataAtual.substring(0,4));
		int mesAtual = Integer.parseInt(dataAtual.substring(5,7));
		int diaAtual = Integer.parseInt(dataAtual.substring(8,10));
		
		//verifica se o ultimo mês  e ano pago bate com o mês e ano atual, se não, ele está atrasado
		for (int i = 0; i < alunos.size(); i++) {
			
			int diaPagamento = Integer.parseInt(alunos.get(i).getDiaPagamento());
			alunos.get(i).setSituacaoPagamento("Atrasado"); //todos começam como atrasado até acabar a verificação
			
			PagamentoBusiness pB = new PagamentoBusiness();
			ArrayList<Pagamento> pagamentosAluno = (ArrayList<Pagamento>) pB.listar(alunos.get(i));
			
			for (int j = 0; j < pagamentosAluno.size(); j++) {
				int mesPago = Integer.parseInt(pagamentosAluno.get(j).getMesPago());
				int anoPago = Integer.parseInt(pagamentosAluno.get(j).getAnoPago());
				
				if(diaPagamento < diaAtual){ //ja passou o dia de pagamento
					//verifica mes corrente
					if(mesPago == mesAtual && anoPago == anoAtual){
						//aluno está em dia
						alunos.get(i).setSituacaoPagamento("Pago");
					}
					
				}else{ //não passou o dia de pagamento
					int mesPassado = 0;
					int anoPassado = 0;
					if(mesAtual == 1){ //se n passou o dia e o mes for janeiro, analisa dezembro do mes passado
						mesPassado = 12;
						anoPassado = anoAtual-1;
						
						if(mesPago == mesPassado && anoPago == anoPassado){
							//aluno está em dia
							alunos.get(i).setSituacaoPagamento("Pago");
						}
						
					}else{
						mesPassado = mesAtual-1;
						
						if(mesPago == mesPassado && anoPago == anoAtual){
							//aluno está em dia
							alunos.get(i).setSituacaoPagamento("Pago");
						}
					}
				}
				UnidadeAlunoBusiness uaB2 = new UnidadeAlunoBusiness();
				uaB2.update(alunos.get(i));
			}
		}
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNomeUnidade() {
		return nomeUnidade;
	}

	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
	}
	
}
