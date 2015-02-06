package com.cm.service;

import com.cm.domain.model.Subscription;

import java.util.Set;

public interface SubscriptionService {

    Set<Subscription> getSubscriptions(String country);
}
