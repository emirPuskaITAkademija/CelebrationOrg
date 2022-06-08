package ba.organizuj.celebrationorg.ejb.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;

import java.util.List;

public abstract class AbstractService<E>{

    private Class<E> entityClass;

    public AbstractService(Class<E> entityClass){
        this.entityClass = entityClass;
    }

    public abstract EntityManager getEntityManager();

    //Create
    public void create(E entity){
        getEntityManager().persist(entity);
    }

    //Retrieve > by id -> all
    public E find(Object id){
        return getEntityManager().find(entityClass, id);
    }

    public List<E> findAll(){
        //ne named query -> CriteriaQuery
        CriteriaQuery<E> criteriaQuery = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        criteriaQuery.select(criteriaQuery.from(entityClass));
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    //Update
    public void edit(E entity){
        getEntityManager().merge(entity);
    }

    //Delete
    public void remove(E entity){
        getEntityManager().remove(entity);
    }
}
