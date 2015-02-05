package com.cm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("com.cm.service.subscriptionService")
@Transactional
public class SubscriptionService {

    private final static Logger LOGGER = LoggerFactory.getLogger(SubscriptionService.class);

}
