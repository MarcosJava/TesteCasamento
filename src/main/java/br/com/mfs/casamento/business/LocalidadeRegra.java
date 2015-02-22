package br.com.mfs.casamento.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.mfs.casamento.dao.LocalidadeDAO;
import br.com.mfs.casamento.exception.NegocioException;
import br.com.mfs.casamento.interceptadores.BancoDadosMysql;
import br.com.mfs.casamento.interceptadores.Transactional;
import br.com.mfs.casamento.model.Localidade;

public class LocalidadeRegra implements Serializable{
	
	@Inject
	private LocalidadeDAO localidadeDAO;
	
	public List<Localidade> trazerTodos(){
		return localidadeDAO.trazerTodos();
	}
	
	public Localidade trazerPeloId(Integer id) throws NegocioException{
		
		if(id == 0 ||
				id == null){
			
			throw new NegocioException("Localidade não escolhida");
		}
		
		
		return localidadeDAO.procurarPorId(id);
	}
	
	
	@Transactional @BancoDadosMysql
	public void salvarLocalidade(Localidade localidade) throws NegocioException{
		
		if(localidade.getNomeLocalidade().trim().equals("") 
				||localidade.getNomeLocalidade() == null) {
			
			throw new NegocioException("Campo do nome localidade nulo.");				
		} else {
			System.out.println(localidade.getIdLocalidade());
			System.out.println(localidade.getNomeLocalidade());
			localidadeDAO.save(localidade);
			System.out.println("foi aleluia");
		}

	}
	
	@Transactional @BancoDadosMysql
	public void deletarLocalidade(Localidade localidade) throws NegocioException {		
		try {
			this.localidadeDAO.delete(localidade.getIdLocalidade(), Localidade.class);
		} catch (Exception e) {
			throw new NegocioException("Existe convidados na localidade , não pode ser excluida");
		}
		
		
	}
	
}
