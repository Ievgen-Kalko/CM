package com.cm.persistence.jpa.impl;

import com.cm.domain.model.User;
import com.cm.persistence.jpa.EntityRepositoryJPA;
import com.cm.persistence.jpa.UserRepositoryJPA;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Repository("com.cm.persistence.jpa.UserRepositoryJPA")
@Transactional
public class UserRepositoryJPAImpl extends EntityRepositoryJPA<User, Long> implements UserRepositoryJPA {

    public UserRepositoryJPAImpl() {
    }

    public List<User> findByType(User.UserTypes type) {
        Assert.notNull(type, "method was invoked with null arg");

        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("type", type));

        return criteria.list();
    }

    @Override
    protected Class getActualClass() {
        return User.class;
    }
}
