package br.com.mfs.casamento.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.HibernateException;

import br.com.mfs.casamento.dao.ConvidadosDAO;
import br.com.mfs.casamento.exception.NegocioException;
import br.com.mfs.casamento.interceptadores.BancoDadosMysql;
import br.com.mfs.casamento.interceptadores.Transactional;
import br.com.mfs.casamento.model.Convidados;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class ConvidadosRegra implements Serializable {
	
	@Inject
	private ConvidadosDAO convidadosDAO;
	
	
	public void checkConvidados(Convidados convidado) throws NegocioException{
		
		if(convidado.getNome() == null || convidado.getNome().trim().equals("")){
			throw new NegocioException("Não contém nome");
			
		}  else if (convidado.getLocalidade().getIdLocalidade() == 0 || convidado.getLocalidade().getIdLocalidade() == null){
			throw new NegocioException("Não foi selecionado uma localidade.");
		}
	}
	
	
	@Transactional @BancoDadosMysql
	public void salvar(Convidados convidado) throws NegocioException{
		checkConvidados(convidado);
		try {
			this.convidadosDAO.save(convidado);
		} catch (Exception e) {
			throw new NegocioException("O nome do convidado já existe.");
		}
		
	}
	
	@Transactional @BancoDadosMysql
	public void exclui(Convidados convidados){
		convidadosDAO.delete(convidados.getIdConvidado(), Convidados.class);
	}
	
	public List<Convidados> buscarTudo(){
		return convidadosDAO.trazerTodos();
	}
	
	public List<Convidados> buscarTodosDoCasal(String login){
		return convidadosDAO.buscarTodosDoUsuario(login);
	}
	
	public List<String> buscarTodosConvidadosDoCasal(String login){
		return convidadosDAO.buscarTodosConvidadosDoCasal(login);
	}
	
}
