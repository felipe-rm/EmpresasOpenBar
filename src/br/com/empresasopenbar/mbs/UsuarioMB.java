package br.com.empresasopenbar.mbs;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.empresasopenbar.entidades.Usuario;
import br.com.empresasopenbar.utils.Mensagem;

@ManagedBean(name = "usuarioMB")
@ViewScoped
public class UsuarioMB  {
	private Usuario usuario;
		
	@PostConstruct
	public void inicializarManagedBean() {
		limpar();
	}
		
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void limpar() {
		usuario = new Usuario();
	}
	
	private boolean validarPreenchimentoDosCampos(){
		boolean camposValidos = true;
		
		if (usuario.getUsuario().trim().length() <= 0) {
			camposValidos = false;
			Mensagem.exibirMensagemErro("Informe o usuário.");
		}
				
		if (usuario.getSenha().trim().length() <= 0) {
			camposValidos = false;
			Mensagem.exibirMensagemErro("Informe a senha.");
		}
					
		return camposValidos;
	}
	
	public String efetuarLogin() {
		String destino = "";
		
		if (validarPreenchimentoDosCampos())
			destino = retornarDestinoDeValidacaoDeLogin();
		
		return destino;
	}
	
	private String retornarDestinoDeValidacaoDeLogin()
	{
		String destino = "/login.xhtml";
		
		if (usuario.getUsuario().equals("admin") && usuario.getSenha().equals("frm007")) {
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession sessao = (HttpSession) fc.getExternalContext().getSession(false);
			
			sessao.setAttribute("usuarioLogado", usuario);
			destino = "/index.xhtml";
			
			try {
				ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
				
				ec.redirect(ec.getRequestContextPath() + destino);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
			Mensagem.exibirMensagemNoTitulo("Login e/ou senha inválido(s)!");
		
		return destino;
	}
	
	public Usuario obterUsuarioLogado() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession sessao = (HttpSession) fc.getExternalContext().getSession(false);
		Usuario usuarioLogado = (Usuario)sessao.getAttribute("usuarioLogado");
		
		if (usuarioLogado == null)
			usuarioLogado = new Usuario();
		
		return usuarioLogado;
	}
	
	/*public br.com.openbar.entidades.Usuario obterUsuarioLogado() {
		br.com.openbar.utils.Usuario utilsUsuario = new br.com.openbar.utils.Usuario();
		return utilsUsuario.obterUsuarioLogado();
	}*/
	
	/*public boolean validarUsuarioParaParaAlteracaoOuExclusao(Usuario usuario) throws NoSuchAlgorithmException {
		br.com.openbar.utils.Usuario utilsUsuario = new br.com.openbar.utils.Usuario();
			 		
		Usuario usuarioLogado = utilsUsuario.obterUsuarioLogado();
		
		return ((usuarioLogado.isUsuarioAdministrador()) || usuarioLogado.getCodigo() == usuario.getCodigo());
		
	}*/
	
	/*public void efetuarLogout() throws IOException {
		br.com.openbar.utils.Usuario utilsUsuario = new br.com.openbar.utils.Usuario();
		utilsUsuario.efetuarLogout();
	}*/
	
	public void efetuarLogout() throws IOException {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession sessao = (HttpSession) fc.getExternalContext().getSession(false);

		if (sessao != null)
		{
			String destino = "/login.xhtml";
			sessao.removeAttribute("usuarioLogado");
						
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			ec.redirect(ec.getRequestContextPath() + destino);
		}
	}

}
