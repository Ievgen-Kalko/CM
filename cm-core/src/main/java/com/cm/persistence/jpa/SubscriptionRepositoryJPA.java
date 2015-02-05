package com.cm.persistence.jpa;

import com.cm.domain.model.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("com.cm.persistence.jpa.SubscriptionRepositoryJPA")
@Transactional
public class SubscriptionRepositoryJPA extends EntityRepositoryJPA<Subscription, Long> {

    private final static Logger LOGGER = LoggerFactory.getLogger(SubscriptionRepositoryJPA.class);

    @Override
    protected Class getActualClass() {
        return Subscription.class;
    }
}
