package br.com.mfs.casamento.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="localidade")
public class Localidade implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_localidade")
	private Integer idLocalidade;
	
	@Column(name="nome_local")
	private String nomeLocalidade;
	
	@OneToMany(mappedBy="localidade",fetch=FetchType.EAGER)
	private Collection<Convidados> convidados;
	
	
	@javax.persistence.Transient
	private String oauth;
	
	public Localidade() {
		// TODO Auto-generated constructor stub
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}


	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	
	
	
	
	public Collection<Convidados> getConvidados() {
		return convidados;
	}

	public void setConvidados(Collection<Convidados> convidados) {
		this.convidados = convidados;
	}

	public String getOauth() {
		return oauth;
	}

	public void setOauth(String oauth) {
		this.oauth = oauth;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idLocalidade == null) ? 0 : idLocalidade.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Localidade other = (Localidade) obj;
		if (idLocalidade == null) {
			if (other.idLocalidade != null)
				return false;
		} else if (!idLocalidade.equals(other.idLocalidade))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Localidade [idLocalidade=" + idLocalidade + ", nomeLocalidade="
				+ nomeLocalidade + ", oauth=" + oauth + "]";
	}


	
	
	
	
	
}
