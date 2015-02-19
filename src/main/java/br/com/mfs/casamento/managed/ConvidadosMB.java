package br.com.mfs.casamento.managed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.mfs.casamento.business.ConvidadosRegra;
import br.com.mfs.casamento.business.LocalidadeRegra;
import br.com.mfs.casamento.business.UsuarioRegras;
import br.com.mfs.casamento.exception.NegocioException;
import br.com.mfs.casamento.model.Convidados;
import br.com.mfs.casamento.model.Localidade;
import br.com.mfs.casamento.model.Usuario;

@Named("convidadosMB")
@RequestScoped
public class ConvidadosMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ConvidadosRegra convidadosBusiness;
	
	@Inject
	private LocalidadeRegra localidadeBusiness;
	
	@Inject
	private UsuarioRegras usuarioBusiness;
	
	@Inject
	private Usuario usuario;
	
	private Convidados convidados;
	private List<Localidade> localidades;
	private List<Convidados> lstMeusConvidados;
	private List<Convidados> lstConvidados;
	private String localidadeSelecionada;
	
	
	
	private Integer qtdConvidados;
	private Integer qtdMeusConvidados;
	
	@Inject
	private UsuarioSessionBean usuarioSessionMB;
	
	
	@PostConstruct
	public void init(){
		this.usuario = this.usuarioSessionMB.getUsuario();
		convidados = new Convidados();
		localidades = new ArrayList<Localidade>();
		lstConvidados = new ArrayList<Convidados>();
	}
	
	
	public void salvar(){
		
		FacesContext fc = FacesContext.getCurrentInstance();
		
		try {
			
			
			Localidade localidade = localidadeBusiness.trazerPeloId(new Integer(localidadeSelecionada));
			
			convidados.setLocalidade(localidade);
			convidados.setUsuario(usuario);
			
			convidados.setNome(convidados.getNome().trim().toUpperCase());
			
			convidadosBusiness.salvar(convidados);
			
			convidados = new Convidados();
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Convidados adicionado com sucesso", "Convidados adicionado com sucesso"));
		} catch (NegocioException e) {
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
		}
	}
	
	public void deletar(Convidados convidados){
				
		try {
			System.out.println(convidados.getNome());
			convidadosBusiness.exclui(convidados);
			
		} catch (Exception e) {
			
		}
	}
	
	

	public List<Convidados> getLstConvidados() {
		
		lstConvidados = convidadosBusiness.buscarTodosDoCasal(usuario.getLogin());
		lstConvidados.addAll(convidadosBusiness.buscarTodosDoCasal(usuarioBusiness.buscarCasalDoUsuario(usuario).getLogin()));
		
		return lstConvidados;
	}
	
	public List<Localidade> getLocalidades() {
		
		localidades = localidadeBusiness.trazerTodos();
		return localidades;
	}
	public Integer getQtdConvidados() {
		qtdConvidados = getLstConvidados().size();
		return qtdConvidados;
	}
	
	
	
	
	public List<Convidados> getLstMeusConvidados() {
				lstMeusConvidados = convidadosBusiness.buscarTodosDoCasal(usuario.getLogin());
		return lstMeusConvidados;
	}


	public Integer getQtdMeusConvidados() {
		return qtdMeusConvidados = getLstMeusConvidados().size();
	}


	/*********
	 * 
	 * GETTER e SETTER
	 */

	public Convidados getConvidados() {
		return convidados;
	}

	public void setConvidados(Convidados convidados) {
		this.convidados = convidados;
	}

	


	public String getLocalidadeSelecionada() {
		return localidadeSelecionada;
	}


	public void setLocalidadeSelecionada(String localidadeSelecionada) {
		this.localidadeSelecionada = localidadeSelecionada;
	}
	
	
}
