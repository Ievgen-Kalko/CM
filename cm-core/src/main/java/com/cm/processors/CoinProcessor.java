package com.cm.processors;

import com.cm.domain.model.Coin;
import com.cm.domain.model.Email;
import com.cm.domain.model.Subscription;
import com.cm.domain.model.User;
import com.cm.service.CoinService;
import com.cm.service.EmailService;
import com.cm.service.SubscriptionService;
import com.cm.service.UserService;
import com.cm.service.impl.CoinServiceImpl;
import com.cm.service.impl.EmailServiceImpl;
import com.cm.service.impl.SubscriptionServiceImpl;
import com.cm.service.impl.UserServiceImpl;
import com.cm.util.CmGenericException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Set;

@Component("com.cm.processors.CoinProcessor")
@Transactional
public class CoinProcessor {

    private final static Logger LOGGER = LoggerFactory.getLogger(CoinProcessor.class);

    @Autowired
    private CoinService coinService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private UserService userService;

    public CoinProcessor() {
    }

    public void processNewCoin(Coin coin) {
        Assert.notNull(coin, "method was invoked with null arg");

        try {
            coinService.checkCoinParameters(coin);
            boolean priceCalculated = coinService.calculatePrice(coin);
            coinService.saveCoin(coin);

            if (priceCalculated) {
                processEmailForClient(coin);
            } else {
                processEmailForAdmin(coin);
            }
        } catch (CmGenericException e) {
            LOGGER.error("Cannot process new coin", e);
        }

    }

    private void processEmailForClient(Coin coin) {
        Set<Subscription> subscriptions = null;

        subscriptions = subscriptionService.getSubscriptions(coin.getCountry());
        Email email = null;

        if (subscriptions.size() > 0) {
            for (Subscription subscription : subscriptions) {
                email = emailService.composeEmailForClient(coin, subscription.getUserId().getEmail());
                emailService.sendEmail(email);
                emailService.saveEmail(email);
            }
        } else {
            LOGGER.info("Cannot find subscribers for country: " + coin.getCountry());
        }
    }

    private void processEmailForAdmin(Coin coin) {
        List<User> users = null;

        users = userService.getUsersByType(User.UserTypes.ADMIN);
        Email email = null;

        if (users.size() > 0) {
            for (User user : users) {
                email = emailService.composeEmailForAdmin(coin, user.getEmail());
                emailService.sendEmail(email);
                emailService.saveEmail(email);
            }
        }
    }
}
