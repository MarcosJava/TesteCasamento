package br.com.mfs.casamento.managed;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.mfs.casamento.business.ConvidadosRegra;
import br.com.mfs.casamento.exception.NegocioException;
import br.com.mfs.casamento.model.Convidados;

@Named
@RequestScoped
public class BuscarConvidadosMeusBean {

	@Inject
	private ConvidadosRegra convidadosRegra;
	
	private List<Convidados> lstConvidados;
	
	@Inject
	private UsuarioSessionBean usuarioSessao;
	
	
	public List<Convidados> getLstConvidados() {		
		lstConvidados = convidadosRegra.buscarTodosDoCasal(usuarioSessao.getLogin());
		System.out.println(lstConvidados.size());
		return lstConvidados;
	}
	

	public void deletar(Convidados convidados){
		
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			System.out.println(convidados.getNome());
			convidadosRegra.exclui(convidados);
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Convidados excluido com sucesso", "Convidados excluido com sucesso"));
		} catch (NegocioException e) {
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
		}
	}
	
	public Integer getQtdConvidados(){
		return getLstConvidados().size();
	}
}
