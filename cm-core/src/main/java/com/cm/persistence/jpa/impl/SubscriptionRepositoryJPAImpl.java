package com.cm.persistence.jpa.impl;

import com.cm.domain.model.Subscription;
import com.cm.persistence.jpa.EntityRepositoryJPA;
import com.cm.persistence.jpa.SubscriptionRepositoryJPA;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Set;

@Repository("com.cm.persistence.jpa.SubscriptionRepositoryJPA")
@Transactional
public class SubscriptionRepositoryJPAImpl extends EntityRepositoryJPA<Subscription, Long> implements SubscriptionRepositoryJPA {

    private final static Logger LOGGER = LoggerFactory.getLogger(SubscriptionRepositoryJPAImpl.class);

    public Set<Subscription> getSubscriptions(String country) {
        Assert.notNull(country, "method was invoked with null arg");
        Assert.isTrue(!country.isEmpty(), "method was invoked with empty string");

        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(Subscription.class);
        criteria.add(Restrictions.eq("country", country));

        return new HashSet<Subscription>(criteria.list());
    }

    @Override
    protected Class getActualClass() {
        return Subscription.class;
    }
}
