package br.com.mfs.casamento.managed;


import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.mfs.casamento.business.UsuarioRegras;


@Named
@RequestScoped
public class LoginBean {
	
	@Inject
	private UsuarioSessionBean usuarioSession;
	
	@Inject
	private UsuarioRegras usuarioRegras;
	
	private String nomeUsuario;
	private String senha;
	
	
	
	public String login() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		boolean resultado = usuarioRegras.verificarUsuario(this.nomeUsuario, this.senha);
		
		if(resultado){			
			this.usuarioSession.setNome(this.nomeUsuario);
			this.usuarioSession.setDataLogin(new Date());
			
			return "protegido/usuario/system?faces-redirect=true";
		
		} else {
			
			FacesMessage mensagem = new FacesMessage("Usuário/Senha invalido");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
			
			return null;
		}
		
		
	}
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login?faces-redirect=true";
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	public UsuarioSessionBean getUsuarioSession() {
		return usuarioSession;
	}
	public void setUsuarioSession(UsuarioSessionBean usuarioSession) {
		this.usuarioSession = usuarioSession;
	}
	
	
}
