package util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import entities.Alumno;

public class Conexion<T> {
	private Class<T> c;
	private static EntityManager em = null;
	
	public Conexion() {
		em = this.getEm();
	}
	
	public Conexion(Class<T> c) {
		em = this.getEm();
		this.c = c;
	}
	
	public void setC(Class<T> c){
		this.c = c;
	}
	
	public static EntityManager getEm(){
		if ( em == null ) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("mensajemodel");
            em = emf.createEntityManager();
        }
		return em;
	}
	
	public <E> T find(E id){
		T object = (T) em.find(c, id);
		return object;
	}
	
	public List<T> list(){
		TypedQuery<T> consulta= em.createNamedQuery(c.getSimpleName()+".findAll", c);
		List<T> lista = (List<T>) consulta.getResultList();
		return lista;
	}
	
	
	public void insert(T o){
		try {
			em.getTransaction().begin();
			em.persist(o);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//em.close();
		}
		
	}
	
	public void update(T o){
		try {
			em.getTransaction().begin();
			em.merge(o);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//em.close();
		}
		
	}
	
	public void delete(T o){
		try {
			em.getTransaction().begin();
			em.remove(o);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//em.close();
		}
		
	}
	public <E> T findByFieldObject(Class<T> c, String fieldName, E fieldValue) {
		Method metodoGet = null;
		try {

			metodoGet = c
					.getDeclaredMethod("get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1));

		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

		Class tipo = metodoGet.getReturnType();

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(c);
		Root<T> root = criteriaQuery.from(c);

		criteriaQuery.select(root);

		ParameterExpression<Object> params = criteriaBuilder.parameter(tipo);
		criteriaQuery.where(criteriaBuilder.equal(root.get(fieldName), params));
		TypedQuery<T> query = (TypedQuery<T>) em.createQuery(criteriaQuery);
		Constructor<?> ct;
		Object retobj = null;
		try {

			retobj = fieldValue;

		} catch (RuntimeException e) {
			e.printStackTrace();
		}

		query.setParameter(params, retobj);
		List<T> queryResult = query.getResultList();
		if(queryResult!=null)
		return queryResult.get(0);
		else
			return null;


	}
}
