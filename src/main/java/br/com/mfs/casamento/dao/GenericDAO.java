package br.com.mfs.casamento.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public class GenericDAO<T, ID extends Serializable> {

	@Inject
	protected EntityManager em;
	
	private Class<T> entityClass;
	
	public GenericDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public GenericDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	
	public void save(T entity) {
		System.out.println("Salvando");
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
	}
	
	public void delete(Object id, Class<T> classe) {
		T entityToBeRemoved = em.getReference(classe, id);
		em.remove(entityToBeRemoved);
	}
	
	
	public T update(T entity) {
		em.getTransaction().begin();
		entity = em.merge(entity);
		em.getTransaction().commit();
		return entity;
	}
	
	
	public T procurarPorId(ID id){
		return em.find(entityClass, id);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> trazerTodos() {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return em.createQuery(cq).getResultList();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> trazerTodosPaginacao(long begin, long end) {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return em.createQuery(cq).setFirstResult((int) begin).setMaxResults((int) end).getResultList();
	}
	
	
	
}
