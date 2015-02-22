package br.com.mfs.casamento.dao;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.text.StyledEditorKit.BoldAction;

import br.com.mfs.casamento.model.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario, Integer> {
	
	
	public boolean checandoLoginSenha(String nomeUsuario, String senha){
		
		try {
			
			TypedQuery<String> query = em.createNamedQuery(Usuario.VERIFICAR_LOGIN_SENHA, String.class);
			query.setParameter("login", nomeUsuario);
			query.setParameter("senha", senha);
			
			String aouth = query.getSingleResult();
			
			if(aouth.equals("") || aouth == null){
				return false;
			} else {
				return true;
			}
		
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	public Usuario buscandoUsuarioPorOauth(String oauth) {
		return super.em.createNamedQuery(Usuario.VERIFICAR_OAUTH, Usuario.class)
						.setParameter("oauth", oauth)
						.getSingleResult();
	}

	
	public Usuario buscandoUsuarioPorLogin(String login) {
		return super.em.createNamedQuery(Usuario.VERIFICAR_LOGIN, Usuario.class).setParameter("login", login).getSingleResult();
	}

	
	public Usuario buscandoCasalDoUsuario(String login) {
		return super.em.createNamedQuery(Usuario.TRAZER_CASAL, Usuario.class).setParameter("login", login).getSingleResult();
	}
	
	
	
	
}
