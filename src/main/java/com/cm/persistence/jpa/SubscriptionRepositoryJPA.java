package com.cm.persistence.jpa;

import com.cm.domain.model.Subscription;
import com.cm.persistence.EntityRepository;

import java.util.Set;

public interface SubscriptionRepositoryJPA extends EntityRepository<Subscription, Long> {

    Set<Subscription> getSubscriptions(String country);

}
