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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
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

    /**
     * Do all operations required when new coin is received,
     * including: Verification, Price calculation, Persisting
     * @param coin
     * @throws CmGenericException
     */
    public void processNewCoin(Coin coin) throws CmGenericException {
        Assert.notNull(coin, "method was invoked with null arg");

        try {
            coinService.checkCoinParameters(coin);
            boolean priceCalculated = coinService.tryToCalculatePrice(coin);
            coinService.saveCoin(coin);

            if (priceCalculated) {
                processEmailForClient(coin);
            } else {
                processEmailForAdmin(coin);
            }
        } catch (CmGenericException e) {
            LOGGER.error("Cannot process new coin", e);
            throw e;
        }

    }

    private void processEmailForClient(Coin coin) {
        Set<Subscription> subscriptions = null;
        List<String> emailAddresses = null;
        Email email = null;

        subscriptions = subscriptionService.getSubscriptions(coin.getCountry());
        emailAddresses = new ArrayList<>(subscriptions.size());

        if (subscriptions.size() > 0) {
            for (Subscription subscription : subscriptions) {
                emailAddresses.add(subscription.getUserId().getEmail());
            }

            email = emailService.composeEmail(coin, emailAddresses, User.UserTypes.CLIENT);
            emailService.sendEmail(email);
            emailService.saveEmail(email);
        } else {
            LOGGER.debug("Cannot find subscribers for country: " + coin.getCountry());
        }
    }

    private void processEmailForAdmin(Coin coin) {
        List<User> users = null;
        List<String> emailAddresses = null;
        Email email = null;

        users = userService.getUsersByType(User.UserTypes.ADMIN);
        emailAddresses = new ArrayList<>(users.size());

        if (users.size() > 0) {
            for (User user : users) {
                emailAddresses.add(user.getEmail());
            }

            email = emailService.composeEmail(coin, emailAddresses, User.UserTypes.ADMIN);
            emailService.sendEmail(email);
            emailService.saveEmail(email);
        } else {
            LOGGER.debug("Cannot find any admin in the database");
        }
    }
}
