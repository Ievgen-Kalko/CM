package com.cm.service;

import com.cm.domain.model.Subscription;

import java.util.Set;

public interface SubscriptionService {

    /**
     * Returns all existing subscriptions for the (@link country)
     * @param country
     * @return list of subscriptions
     */
    Set<Subscription> getSubscriptions(String country);
}
