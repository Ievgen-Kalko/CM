package com.cm.persistence.jpa;

import com.cm.domain.JPAEntity;
import com.cm.persistence.EntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

public abstract class EntityRepositoryJPA<Type extends JPAEntity<PK>, PK extends Serializable> implements EntityRepository<Type, PK> {

    private final static Logger LOGGER = LoggerFactory.getLogger(EntityRepositoryJPA.class);

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
}
