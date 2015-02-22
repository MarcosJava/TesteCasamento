package br.com.mfs.casamento.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import br.com.mfs.casamento.interceptadores.BancoDadosMysql;

public class GenericDAO<T, ID extends Serializable> {

	@Inject @BancoDadosMysql
	protected EntityManager em;
	
	private Class<T> entityClass;
	
	public GenericDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public GenericDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	
	public void save(T entity) {
		em.persist(entity);
	}
	
	public void delete(Object id, Class<T> classe) throws Exception {
		T entityToBeRemoved = em.getReference(classe, id);
		em.remove(entityToBeRemoved);
	}
	
	
	public T update(T entity) {
		entity = em.merge(entity);
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
