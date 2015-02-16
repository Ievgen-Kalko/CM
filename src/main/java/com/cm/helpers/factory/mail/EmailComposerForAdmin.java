package com.cm.helpers.factory.mail;

import com.cm.domain.model.Coin;
import com.cm.domain.model.Email;
import com.cm.helpers.EmailHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

@Component("com.cm.helpers.factory.mail.EmailComposerForAdmin")
public class EmailComposerForAdmin implements EmailComposer {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailComposerForClient.class);

    private final static String EMAIL_SUBJECT = "Failed to estimate coin's price";
    private final static String EMAIL_START = "Failed to estimate price for coin: ";
    private final static String EMAIL_END = "\nRegards,\nCoins Management System for Admin,\n";

    @Autowired
    private EmailHelper emailHelper;

    @Override
    public Email composeEmail(Coin coin, List<String> emailAddresses) {
        Assert.notNull(coin, "method was invoked with null arg");
        Assert.notNull(emailAddresses, "method was invoked with null arg");

        Email email = new Email();

        LOGGER.debug("Starting creation of new Email...");

        email.setFrom(SYSTEM_EMAIL_ADDRESS);
        email.setTo(emailHelper.convertAddressesToString(emailAddresses));
        email.setSubject(EMAIL_SUBJECT);
        email.setBody(createBody(coin));

        LOGGER.debug("Email has been successfully created");

        return email;
    }

    private String createBody(Coin coin) {
        StringBuilder emailBody = new StringBuilder();

        emailBody.append(EMAIL_START);
        emailBody.append("\n");
        emailBody.append(coin.toString());
        emailBody.append("\n");
        emailBody.append(EMAIL_END);

        return emailBody.toString();
    }

}
