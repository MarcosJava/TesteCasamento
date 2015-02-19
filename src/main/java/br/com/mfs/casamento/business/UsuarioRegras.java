package br.com.mfs.casamento.business;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.mfs.casamento.dao.UsuarioDAO;
import br.com.mfs.casamento.interceptadores.BancoDadosMysql;
import br.com.mfs.casamento.interceptadores.Transactional;
import br.com.mfs.casamento.model.Usuario;

public class UsuarioRegras implements Serializable{
	
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
	
	
	public Usuario buscarUsuarioPorOauth(String oauth){
		return usuarioDAO.buscandoUsuarioPorOauth(oauth);
	}
	
	public Usuario buscarUsuarioPorLogin(String login){
		return usuarioDAO.buscandoUsuarioPorLogin(login);
	}
	
	@Transactional @BancoDadosMysql
	public void salvarUsuario(Usuario usuario) throws Exception{
		usuarioDAO.save(usuario);
	}
	
	public Usuario buscarCasalDoUsuario(Usuario u){
		return usuarioDAO.buscandoCasalDoUsuario(u.getLogin());
	}
	
	
}
