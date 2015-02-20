package br.com.mfs.casamento.managed;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.mfs.casamento.business.ConvidadosRegra;
import br.com.mfs.casamento.business.UsuarioRegras;
import br.com.mfs.casamento.model.Convidados;

@Named("buscarConvidadosCasalBean")
@RequestScoped
public class BuscarConvidadosCasalBean {

	@Inject
	private ConvidadosRegra convidadosRegra;
	
	private List<Convidados> lstConvidados;
	
	@Inject
	private UsuarioSessionBean usuarioSessao;
	
	@Inject
	private UsuarioRegras usuarioRegras;
	
	public List<Convidados> getLstConvidados() {		
		lstConvidados = convidadosRegra.buscarTodosDoCasal(usuarioSessao.getLogin());
		System.out.println(lstConvidados.size());
		lstConvidados.addAll(convidadosRegra.buscarTodosDoCasal(usuarioRegras.buscarCasalDoUsuario(usuarioSessao.getUsuario()).getLogin()));
		System.out.println(lstConvidados.size());
		return lstConvidados;
	}
	
	public Integer getQtdConvidados(){
		return getLstConvidados().size();
	}
}
