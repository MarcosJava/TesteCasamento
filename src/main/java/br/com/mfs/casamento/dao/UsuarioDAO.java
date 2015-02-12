package br.com.mfs.casamento.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.mfs.casamento.model.Usuario;

public class UsuarioDAO {
	
	@Inject
	private EntityManager em;
	
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
}
