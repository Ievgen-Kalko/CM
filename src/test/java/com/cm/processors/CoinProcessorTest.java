package com.cm.processors;

import com.cm.domain.model.Coin;
import com.cm.domain.model.Email;
import com.cm.domain.model.Subscription;
import com.cm.domain.model.User;
import com.cm.service.CoinService;
import com.cm.service.EmailService;
import com.cm.service.SubscriptionService;
import com.cm.service.UserService;
import com.cm.util.CmGenericException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CoinProcessorTest {

    @InjectMocks
    private CoinProcessor coinProcessor;

    @Mock
    private CoinService coinService;

    @Mock
    private EmailService emailService;

    @Mock
    private SubscriptionService subscriptionService;

    @Mock
    private UserService userService;

    private Coin coin;
    private List<User> admins;
    private Set<Subscription> subscriptions;

    @Before
    public void setUp() {
        Coin coin = new Coin();
        coin.setCountry("UK");
        coin.setComposition(Coin.CompositionType.GOLD);
        coin.setDescription("Test desc");
        coin.setCirculation(1200);
        coin.setGrade(Coin.GradeType.EXTRA_FINE);
        coin.setYear(1789);

        this.coin = coin;

        User admin = new User();
        admin.setEmail("qqq@qqq.qqq");
        admin.setType(User.UserTypes.ADMIN);

        this.admins = new ArrayList<>(2);
        this.admins.add(admin);

        Subscription subscription = new Subscription();
        subscription.setCountry("UK");
        subscription.setUserId(this.admins.get(0));
        this.subscriptions = new HashSet<Subscription>(1);
        this.subscriptions.add(subscription);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNullArgumentPassedToProcessNewCoin_thenExceptionIsThrown() throws CmGenericException {
        coinProcessor.processNewCoin(null);
    }

    @Test
    public void ifPriceNotExistsInCoin_thenMessageForAdminShouldBeSent() throws CmGenericException {
        when(coinService.tryToCalculatePrice(this.coin)).thenReturn(false);
        when(userService.getUsersByType(User.UserTypes.ADMIN)).thenReturn(this.admins);

        coinProcessor.processNewCoin(this.coin);

        verify(coinService, times(1)).checkCoinParameters(this.coin);
        verify(coinService, times(1)).saveCoin(this.coin);
        verify(emailService, times(1)).sendEmail(any(Email.class));
        verify(emailService, times(1)).saveEmail(any(Email.class));
    }

    @Test
    public void ifPriceExistsInCoin_thenMessageForUserShouldBeSent() throws CmGenericException {
        when(coinService.tryToCalculatePrice(this.coin)).thenReturn(true);
        when(subscriptionService.getSubscriptions(any(String.class))).thenReturn(this.subscriptions);

        coinProcessor.processNewCoin(this.coin);

        verify(coinService, times(1)).checkCoinParameters(this.coin);
        verify(coinService, times(1)).saveCoin(this.coin);
        verify(emailService, times(1)).sendEmail(any(Email.class));
        verify(emailService, times(1)).saveEmail(any(Email.class));
    }

//    @Test
//    public void ifTwoUsersFound_thenEmailIsSendTwoTimes() throws CmGenericException {
//        List<User> users = this.admins;
//        users.add(new User());
//
//        when(coinService.tryToCalculatePrice(this.coin)).thenReturn(false);
//        when(userService.getUsersByType(User.UserTypes.ADMIN)).thenReturn(users);
//
//        coinProcessor.processNewCoin(this.coin);
//
//        verify(coinService, times(1)).checkCoinParameters(this.coin);
//        verify(coinService, times(1)).saveCoin(this.coin);
//        verify(emailService, times(2)).sendEmail(any(Email.class));
//        verify(emailService, times(2)).saveEmail(any(Email.class));
//    }
//
//    @Test
//    public void ifTwoSubscribersFound_thenEmailIsSendTwoTimes() throws CmGenericException {
//        Set<Subscription> subscriptions = this.subscriptions;
//        Subscription subscription = new Subscription();
//        subscription.setCountry("GH");
//        subscription.setUserId(new User());
//        subscriptions.add(subscription);
//
//        when(coinService.tryToCalculatePrice(this.coin)).thenReturn(true);
//        when(subscriptionService.getSubscriptions(any(String.class))).thenReturn(subscriptions);
//
//        coinProcessor.processNewCoin(this.coin);
//
//        verify(coinService, times(1)).checkCoinParameters(this.coin);
//        verify(coinService, times(1)).saveCoin(this.coin);
//        verify(emailService, times(2)).sendEmail(any(Email.class));
//        verify(emailService, times(2)).saveEmail(any(Email.class));
//    }
}
