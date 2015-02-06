package com.cm.persistence;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

public interface EntityRepository<Type, PK extends Serializable> {

    Type find(PK id);

    void create(Type entity);

    Type update(Type entity);

    void delete(Type entity);

    Type createOrUpdate(Type entity);

    void deleteById(PK entityId);
}