package com.cm.service;

import com.cm.domain.model.Coin;
import com.cm.domain.model.Email;
import com.cm.persistence.jpa.SubscriptionRepositoryJPA;
import com.cm.service.impl.SubscriptionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

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
    public void whenGetSubscriptionsInvokedWithNullArg_thenExceptionIsThrown() {
        subscriptionService.getSubscriptions(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetSubscriptionsInvokedWithEmptyArg_thenExceptionIsThrown() {
        subscriptionService.getSubscriptions("");
    }

    @Test
    public void whenGetSubscriptionsInvoked_thenSubscriptionRepositoryShouldInvoked() {
        subscriptionService.getSubscriptions("sth");

        verify(subscriptionRepositoryJPA, times(1)).getSubscriptions(any(String.class));
    }

}
