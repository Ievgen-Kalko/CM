package com.cm.service;

import com.cm.domain.model.Coin;
import com.cm.domain.model.Email;
import com.cm.helpers.EmailHelper;
import com.cm.persistence.jpa.EmailRepositoryJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service("com.cm.service.emailService")
@Transactional
public class EmailService {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    private final static String EMAIL_START = "Dear subscriber, we've just received new coin, that can be interesting " +
            "for you.\n";
    private final static String EMAIL_END = "\nRegards,\nCoins Management System,\nPhone: +38(050)3567836\n";
    private final static String SYSTEM_EMAIL_ADDRESS = "com.cm.automailer@gmail.com";
    private final static String EMAIL_SUBJECT = "Coin service";
    private final static String EMAIL_START_ADMIN = "Failed to estimate coin's price for coin: \n";

    public EmailService() {
    }

    @Autowired
    private EmailRepositoryJPA emailRepository;

    @Autowired
    private EmailHelper emailHelper;

    public Email composeEmailForClient(Coin coin, String emailAddress) {
        Assert.notNull(coin, "method was invoked with null arg");
        Assert.notNull(emailAddress, "method was invoked with null arg");
        Assert.isTrue(!emailAddress.isEmpty(), "method was invoked with empty string");

        String body = createBodyForClient(coin);

        Email email = new Email();
        email.setBody(body);
        email.setEmail(emailAddress);

        return email;
    }

    public Email composeEmailForAdmin(Coin coin, String emailAddress) {
        Assert.notNull(coin, "method was invoked with null arg");
        Assert.notNull(emailAddress, "method was invoked with null arg");
        Assert.isTrue(!emailAddress.isEmpty(), "method was invoked with empty string");

        String body = createBodyForAdmin(coin);

        Email email = new Email();
        email.setBody(body);
        email.setEmail(emailAddress);

        return email;
    }

    public void sendEmail(Email email) {
        Assert.notNull(email, "method was invoked with null arg");

        try {
            emailHelper.sendMail(SYSTEM_EMAIL_ADDRESS, email.getEmail(), EMAIL_SUBJECT, email.getBody());
            email.setSentStatus(Email.SentStatus.SUCCESSFULLY_SENT);
        } catch (Exception e) {
            LOGGER.info("Cannot sent email to " + email.getEmail() +
                    " It will be persisted to DB with status 'DELIVERY_FAILED'");
            email.setSentStatus(Email.SentStatus.DELIVERY_FAILED);
        }
    }

    private String createBodyForClient(Coin coin) {
        StringBuilder emailBody = new StringBuilder();

        emailBody.append(EMAIL_START);
        emailBody.append("\n");
        emailBody.append(coin.toString());
        emailBody.append("\n");
        emailBody.append(EMAIL_END);

        return emailBody.toString();
    }

    public void saveEmail(Email email) {
        Assert.notNull(email, "method was invoked with null arg");

        emailRepository.create(email);
    }

    private String createBodyForAdmin(Coin coin) {
        StringBuilder emailBody = new StringBuilder();

        emailBody.append(EMAIL_START_ADMIN);
        emailBody.append("\n");
        emailBody.append(coin.toString());
        emailBody.append("\n");

        return emailBody.toString();
    }
}
