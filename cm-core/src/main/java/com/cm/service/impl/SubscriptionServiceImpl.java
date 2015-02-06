package com.cm.service.impl;

import com.cm.domain.model.Subscription;
import com.cm.persistence.jpa.SubscriptionRepositoryJPA;
import com.cm.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Set;

@Service("com.cm.service.impl.SubscriptionService")
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {

    private final static Logger LOGGER = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

    @Autowired
    private SubscriptionRepositoryJPA subscriptionRepositoryJPA;

    public SubscriptionServiceImpl() {
    }

    @Override
    public Set<Subscription> getSubscriptions(String country) {
        Assert.notNull(country, "method was invoked with null arg");
        Assert.isTrue(!country.isEmpty(), "method was invoked with empty string");
        
        return subscriptionRepositoryJPA.getSubscriptions(country);
    }
}
