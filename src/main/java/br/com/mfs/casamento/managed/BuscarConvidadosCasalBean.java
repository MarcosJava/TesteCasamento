package br.com.mfs.casamento.managed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.mfs.casamento.business.ConvidadosRegra;
import br.com.mfs.casamento.business.UsuarioRegras;
import br.com.mfs.casamento.exception.NegocioException;
import br.com.mfs.casamento.model.Convidados;

@Named("buscarConvidadosCasalBean")
@ViewScoped
public class BuscarConvidadosCasalBean implements Serializable {

	@Inject
	private ConvidadosRegra convidadosRegra;
	
	private List<Convidados> lstConvidados;
	
	@Inject
	private UsuarioSessionBean usuarioSessao;
	
	@Inject
	private UsuarioRegras usuarioRegras;
	
	@PostConstruct
	public void init(){
		
		verificandoCasal();
		
	}
	
	public void verificandoCasal(){

		String login = usuarioSessao.getLogin();
		
		
		if(usuarioSessao.getUsuario().getCasal() == null){
			lstConvidados = new ArrayList<Convidados>();
		} else {
			lstConvidados = convidadosRegra.buscarTodosDoCasal(login);
		}
		
	}
	
	public List<Convidados> getLstConvidados() {				
		return lstConvidados;
	}
	
	public Integer getQtdConvidados(){
		return getLstConvidados().size();
	}
}
