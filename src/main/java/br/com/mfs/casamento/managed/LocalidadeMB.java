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
	
	private Localidade localidade;
	private List<Localidade> localidades = new ArrayList<Localidade>();
	
	
	
	public LocalidadeMB() {
		super();
		localidade = new Localidade();
	}
	
	public void cadastrar(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		
		try {			
			localidade.setIdLocalidade(6);
			System.out.println(localidade.getNomeLocalidade());
			localidadeRegra.salvarLocalidade(localidade);

			localidade = new Localidade();
			
			context.addMessage(null, new FacesMessage("Lançamento salvo com sucesso!"));
		} catch (NegocioException e) {
			FacesMessage mensagemErro = new FacesMessage(e.getMessage());
			mensagemErro.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagemErro);
		}
	}
	
	public void excluir(Localidade localidade){
		try {
		
			System.out.println(localidade.getNomeLocalidade());
			localidade = new Localidade();
			
		} catch (Exception e) {
			e.printStackTrace();
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
