package br.com.mfs.casamento.managed;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.mfs.casamento.business.UsuarioRegras;
import br.com.mfs.casamento.model.Usuario;

@Named
@SessionScoped
public class UsuarioSessionBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String login;
	private Date dataLogin;

	@Inject
	private UsuarioRegras usuarioRegras;
	
	public boolean isLogado() {
		return login != null;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Date getDataLogin() {
		return dataLogin;
	}

	public void setDataLogin(Date dataLogin) {
		this.dataLogin = dataLogin;
	}
	
	public Usuario getUsuario(){
		return this.usuarioRegras.buscarUsuarioPorLogin(this.login);
	}
	
	
}