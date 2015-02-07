package com.cm.persistence;

import java.io.Serializable;

public interface EntityRepository<Type, PK extends Serializable> {

    Type find(PK id);

    void create(Type entity);

    Type update(Type entity);

    void delete(Type entity);

    Type createOrUpdate(Type entity);

    void deleteById(PK entityId);
}