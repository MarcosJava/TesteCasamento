package br.com.mfs.casamento.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.mfs.casamento.dao.ConvidadosDAO;
import br.com.mfs.casamento.exception.NegocioException;
import br.com.mfs.casamento.interceptadores.BancoDadosMysql;
import br.com.mfs.casamento.interceptadores.Transactional;
import br.com.mfs.casamento.model.Convidados;

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
	public void exclui(Convidados convidados) throws NegocioException{
		try {
			convidadosDAO.delete(convidados.getIdConvidado(), Convidados.class);
		} catch (Exception e) {
			throw new NegocioException("Não pode excluir convidado.");
		}
	}
	
	public List<Convidados> buscarTudo(){
		return convidadosDAO.trazerTodos();
	}
	
	public List<Convidados> buscarTodosDoCasal(String login){
		return convidadosDAO.buscarTodosConvidadosDoCasal(login);
	}
	
	public List<Convidados> buscarTodosDoUsuario(String login) throws NegocioException{
		return convidadosDAO.buscarTodosDoUsuario(login);
	}
	
	
}
