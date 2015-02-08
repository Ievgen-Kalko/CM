package com.cm.persistence;

import java.io.Serializable;

public interface EntityRepository<Type, PK extends Serializable> {

    /**
     * Finds by id
     * @param id
     * @return
     */
    Type find(PK id);

    /**
     * Persists (@link entity) to the database
     * @param entity
     */
    void create(Type entity);

    /**
     * Updates (@link entity) in the database
     * @param entity
     * @return
     */
    Type update(Type entity);

    /**
     * Removes (@link entity) from the database
     * @param entity
     */
    void delete(Type entity);

    /**
     * Persists or updates entity in the database
     * @param entity
     * @return
     */
    Type createOrUpdate(Type entity);

    /**
     * Removes entity with id (@link entityId) from the database
     * @param entityId
     */
    void deleteById(PK entityId);
}