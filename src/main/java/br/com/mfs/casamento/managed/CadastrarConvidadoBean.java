package br.com.mfs.casamento.managed;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.mfs.casamento.business.ConvidadosRegra;
import br.com.mfs.casamento.business.LocalidadeRegra;
import br.com.mfs.casamento.exception.NegocioException;
import br.com.mfs.casamento.model.Convidados;
import br.com.mfs.casamento.model.Localidade;
import br.com.mfs.casamento.model.Usuario;

@Named("cadastrarConvidadoBean")
@RequestScoped
public class CadastrarConvidadoBean {

	@Inject
	private UsuarioSessionBean usuarioSessionMB;
	
	@Inject
	private LocalidadeRegra localidadeRegra;
	
	@Inject
	private ConvidadosRegra convidadosRegra;
	
	@Inject
	private Usuario usuario;
	
	@Inject
	private Convidados convidados;
	
	private String localidadeSelecionada;
	
	
	@PostConstruct
	public void init(){
		this.usuario = this.usuarioSessionMB.getUsuario();
		convidados = new Convidados();
	}
	
	public void salvar(){
		
		FacesContext fc = FacesContext.getCurrentInstance();
		
		try {
			
			Localidade localidade = localidadeRegra.trazerPeloId(new Integer(localidadeSelecionada));
			convidados.setLocalidade(localidade);
			convidados.setUsuario(usuario);
			convidados.setNome(convidados.getNome().toUpperCase());
			
			convidadosRegra.salvar(convidados);
			
			fc.addMessage("msgResposta", new FacesMessage(FacesMessage.SEVERITY_INFO, "Convidados adicionado com sucesso", "Convidados adicionado com sucesso"));
			
		} catch (NegocioException e) {
			fc.addMessage("msgResposta", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
		}
		
	}

	

	public String getLocalidadeSelecionada() {
		return localidadeSelecionada;
	}


	public void setLocalidadeSelecionada(String localidadeSelecionada) {
		this.localidadeSelecionada = localidadeSelecionada;
	}
	

	public Convidados getConvidados() {
		return convidados;
	}

	public void setConvidados(Convidados convidados) {
		this.convidados = convidados;
	}
	
	
	
}
