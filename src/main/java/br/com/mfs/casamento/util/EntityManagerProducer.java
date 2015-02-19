package br.com.mfs.casamento.util;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.mfs.casamento.interceptadores.BancoDadosMysql;

@ApplicationScoped
public class EntityManagerProducer {
	
	private EntityManagerFactory factory;
	
	@PostConstruct  
    public void init() {  
		this.factory = Persistence.createEntityManagerFactory("CasamentoPU");  
          
    } 
	
	@Produces
	@RequestScoped
	@BancoDadosMysql
	public EntityManager createEntityManager() {
		return factory.createEntityManager();
	}

	public void closeEntityManager(@Disposes @BancoDadosMysql EntityManager manager) {
		manager.close();
	}
}