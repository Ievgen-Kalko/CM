package com.cm.service;

import com.cm.persistence.jpa.SubscriptionRepositoryJPA;
import com.cm.service.impl.SubscriptionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class SubscriptionServiceImplTest {

    @InjectMocks
    private SubscriptionServiceImpl subscriptionService;

    @Mock
    private SubscriptionRepositoryJPA subscriptionRepositoryJPA;

    @Test(expected = IllegalArgumentException.class)
    public void whenGetSubscriptionsInvokedWithNullArgThenExceptionIsThrown() {
        subscriptionService.getSubscriptions(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetSubscriptionsInvokedWithEmptyArgThenExceptionIsThrown() {
        subscriptionService.getSubscriptions("");
    }

    @Test
    public void whenGetSubscriptionsInvokedThenSubscriptionRepositoryShouldInvoked() {
        subscriptionService.getSubscriptions("sth");

        verify(subscriptionRepositoryJPA, times(1)).getSubscriptions(any(String.class));
    }

}
