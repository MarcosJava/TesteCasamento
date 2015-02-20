package br.com.mfs.casamento.managed;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.mfs.casamento.business.ConvidadosRegra;
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
	
	public Integer getQtdConvidados(){
		return getLstConvidados().size();
	}
}
