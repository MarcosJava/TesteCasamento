package br.com.mfs.casamento.business;

import javax.inject.Inject;

import br.com.mfs.casamento.dao.UsuarioDAO;

public class UsuarioRegras {
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	public boolean verificarUsuario(String login, String senha){
		
		if(login.trim().equals("")){
			return false;
		} else if (senha.trim().equals("")) {
			return false;
		} else {
			return usuarioDAO.checandoLoginSenha(login, senha);
		}
		
		
	}

}
