package br.com.mfs.casamento.managed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.mfs.casamento.business.LocalidadeRegra;
import br.com.mfs.casamento.exception.NegocioException;
import br.com.mfs.casamento.model.Localidade;

@Named
@RequestScoped
public class LocalidadeMB implements Serializable{

	@Inject
	private LocalidadeRegra localidadeRegra;
	
	@Inject
	private Localidade localidade;
	
	private List<Localidade> localidades = new ArrayList<Localidade>();
	
	
	public void cadastrar(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		
		try {			
			
			System.out.println(localidade.getNomeLocalidade());
			localidadeRegra.salvarLocalidade(localidade);

			//localidade = new Localidade();
			
			context.addMessage(null, new FacesMessage("Localidade salvo com sucesso!"));
		} catch (NegocioException e) {
			FacesMessage mensagemErro = new FacesMessage(e.getMessage());
			mensagemErro.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagemErro);
		}
	}
	
	public void excluir(Localidade localidade){
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			
			
			this.localidadeRegra.deletarLocalidade(localidade);
			localidade = new Localidade();
			context.addMessage(null, new FacesMessage("Localidade excluida com sucesso"));
			
		} catch (NegocioException e) {
			FacesMessage mensagemErro = new FacesMessage(e.getMessage());
			mensagemErro.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagemErro);
		}
	}
	
	public List<Localidade> getLocalidades() {		
		localidades = localidadeRegra.trazerTodos();		
		return localidades;
	}
	
	
	

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	
	
	
}
