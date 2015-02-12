package br.com.mfs.casamento.business;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.mfs.casamento.dao.LocalidadeDAO;
import br.com.mfs.casamento.exception.NegocioException;
import br.com.mfs.casamento.model.Localidade;

@RequestScoped
public class LocalidadeRegra {
	
	@Inject
	private LocalidadeDAO localidadeDAO;
	
	public List<Localidade> trazerTodos(){
		return localidadeDAO.trazerTodos();
	}
	
	
	public void salvarLocalidade(Localidade localidade) throws NegocioException{
		
		if(localidade.getNomeLocalidade().trim().equals("") 
				||localidade.getNomeLocalidade() == null) {
			
			throw new NegocioException("Campo do nome localidade nulo.");				
		} else {
			
			localidadeDAO.save(localidade);
		}

	}
	
}
