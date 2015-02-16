package com.cm.service.impl;

import com.cm.domain.model.Coin;
import com.cm.domain.model.Email;
import com.cm.domain.model.User;
import com.cm.helpers.EmailHelper;
import com.cm.helpers.factory.mail.EmailComposerFactory;
import com.cm.persistence.jpa.EmailRepositoryJPA;
import com.cm.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service("com.cm.service.emailService")
@Transactional
public class EmailServiceImpl implements EmailService {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    public EmailServiceImpl() {
    }

    @Autowired
    private EmailRepositoryJPA emailRepository;

    @Autowired
    private EmailHelper emailHelper;

    @Autowired
    private EmailComposerFactory composerFactory;

    @Override
    public Email composeEmail(Coin coin, List<String> emailAddresses, User.UserTypes userType) {
        Assert.notNull(coin, "method was invoked with null arg");
        Assert.notNull(emailAddresses, "method was invoked with null arg");
        Assert.notNull(userType, "method was invoked with null arg");

        return composerFactory.getForType(userType.toString()).composeEmail(coin, emailAddresses);
    }

    @Override
    public void sendEmail(Email email) {
        Assert.notNull(email, "method was invoked with null arg");

        String[] emailAddresses = email.getTo().split(";");

        try {
            emailHelper.sendMail(email.getFrom(), emailAddresses, email.getSubject(), email.getBody());
            email.setSentStatus(Email.SentStatus.SUCCESSFULLY_SENT);
        } catch (Exception e) {
            email.setSentStatus(Email.SentStatus.DELIVERY_FAILED);
            LOGGER.warn("Cannot send e-mail", e);
        }
    }

    @Override
    public void saveEmail(Email email) {
        Assert.notNull(email, "method was invoked with null arg");

        emailRepository.create(email);
    }
}
