package com.cm.service;

import com.cm.domain.model.Subscription;
import com.cm.persistence.jpa.SubscriptionRepositoryJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Set;

@Service("com.cm.service.SubscriptionService")
@Transactional
public class SubscriptionService {

    private final static Logger LOGGER = LoggerFactory.getLogger(SubscriptionService.class);

    @Autowired
    private SubscriptionRepositoryJPA subscriptionRepositoryJPA;

    public SubscriptionService() {
    }

    public Set<Subscription> getSubscriptions(String country) {
        Assert.notNull(country, "method was invoked with null arg");
        Assert.isTrue(!country.isEmpty(), "method was invoked with empty string");
        
        return subscriptionRepositoryJPA.getSubscriptions(country);
//        return null;
    }
}
