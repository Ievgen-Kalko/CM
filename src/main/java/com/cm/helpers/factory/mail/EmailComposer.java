package com.cm.helpers.factory.mail;

import com.cm.domain.model.Coin;
import com.cm.domain.model.Email;

import java.util.List;

/**
 * Composes email.
 */
public interface EmailComposer {

    final static String SYSTEM_EMAIL_ADDRESS = "com.cm.automailer@gmail.com";

    /**
     * Composes email with the type, predefined in a factory method
     * @param coin
     * @param emailAddress
     * @return newly created Email
     */
    Email composeEmail(Coin coin, List<String> emailAddress);

}
