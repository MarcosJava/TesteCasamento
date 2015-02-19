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

	
	public List<String> buscarTodosConvidadosDoCasal(String login) {
		Query query = super.em.createNativeQuery("select * from vw_convidados_casal where login=:login order by convidados");
		query.setParameter("login", login);
		return query.getResultList();
	}


	

}
