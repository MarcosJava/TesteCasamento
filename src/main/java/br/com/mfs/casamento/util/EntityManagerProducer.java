package br.com.mfs.casamento.util;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer {
	
	private EntityManagerFactory factory;
	
	@PostConstruct  
    public void init() {  
		this.factory = Persistence.createEntityManagerFactory("CasamentoPU");  
          
    } 
	
	@Produces
	@RequestScoped
	@Default
	public EntityManager createEntityManager() {
		return factory.createEntityManager();
	}

	public void closeEntityManager(@Disposes @Default EntityManager manager) {
		manager.close();
	}
}