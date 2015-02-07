package com.cm.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component("com.cm.helpers.MailProcessor")
public class EmailHelper {

    @Autowired
    private MailSender mailSender;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Tries to send (@link email)
     * @param from
     * @param to
     * @param subject
     * @param msg
     */
    public void sendMail(String from, String to, String subject, String msg) {
        Assert.notNull(from, "method was invoked with null arg");
        Assert.notNull(to, "method was invoked with null arg");
        Assert.notNull(subject, "method was invoked with null arg");
        Assert.notNull(msg, "method was invoked with null arg");

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(msg);
        mailSender.send(message);
    }
}
