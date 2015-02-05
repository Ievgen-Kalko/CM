package com.cm.persistence.jpa;

import com.cm.domain.JPAEntity;
import com.cm.persistence.EntityRepository;
import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;

public abstract class EntityRepositoryJPA<Type extends JPAEntity<PK>, PK extends Serializable> implements EntityRepository<Type, PK> {

    private final static Logger LOGGER = LoggerFactory.getLogger(EntityRepositoryJPA.class);
    //TODO - For what it exists here
//    private static final long serialVersionUID = 1287435634234657L;

    //TODO - Implement logging

    @PersistenceContext
    protected EntityManager entityManager;

    private Class<Type> clazz;

    public EntityRepositoryJPA() {
        this.clazz = (Class<Type>) getActualClass();
    }

    @Override
    public Type find(PK id) {
        return entityManager.find(clazz, id);
    }

    @Override
    public Type find(Criteria criteria) {
        Assert.notNull(criteria, "criteria");

        TypedQuery<Type> query = getTypedQuery(criteria);

        query.setMaxResults(1);

        Type entity;
        try {
            entity = query.getSingleResult();
        } catch (NoResultException e) {
            entity = null;
            LOGGER.info(e.getMessage());
        }
        return entity;
    }

    @Override
    public void create(Type entity) {
        entityManager.persist(entity);
    }

    @Override
    public Type createOrUpdate(Type entity) {
        throw new NotImplementedException();
    }

    @Override
    public Type update(Type entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void delete(Type entity) {
        entityManager.remove(entity);
    }

    @Override
    public void deleteById(PK entityId) {
        Type entity = find(entityId);
        delete(entity);
    }

    protected abstract Class getActualClass();

    private TypedQuery<Type> getTypedQuery(Criteria criteria) {
        // TODO - Decide sth
        return null;
    }
}
