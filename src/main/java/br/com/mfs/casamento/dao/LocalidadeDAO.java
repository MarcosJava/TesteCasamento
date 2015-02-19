package br.com.mfs.casamento.dao;

import java.io.Serializable;

import br.com.mfs.casamento.model.Localidade;


public class LocalidadeDAO extends GenericDAO<Localidade, Integer> implements Serializable{

	public LocalidadeDAO() {
		super(Localidade.class);
	}


}
