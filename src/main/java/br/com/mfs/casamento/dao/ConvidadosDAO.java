package br.com.mfs.casamento.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.mfs.casamento.model.Convidados;

public class ConvidadosDAO extends GenericDAO<Convidados, Integer>{


	public List<Convidados> buscarTodosDoUsuario(String login) {
		return super.em.createNamedQuery(Convidados.TRAZER_TODOS_DO_USUARIO, Convidados.class)
				       .setParameter("login", login)
				       .getResultList();
	}

	
	
	public List<Convidados> buscarTodosConvidadosDoCasal(String login) {
				
		String sql = "select * from convidados c inner join usuario u where (u.login=? and c.id_usuario = u.id_usuario)  or (u.login=? and c.id_usuario = u.casal)";

		Query query = em.createNativeQuery(sql, Convidados.class)
				        .setParameter(1, login)
				        .setParameter(2, login);
		return query.getResultList();

	}


	

}
