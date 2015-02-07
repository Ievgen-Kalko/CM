package com.cm.service;

import com.cm.domain.model.Coin;
import com.cm.domain.model.Email;

public interface EmailService {

    /**
     * Creates email for client
     * @param coin
     * @param emailAddress
     * @return newly created email
     */
    Email composeEmailForClient(Coin coin, String emailAddress);

    /**
     * Creates email for admin
     * @param coin
     * @param emailAddress
     * @return newly created email
     */
    Email composeEmailForAdmin(Coin coin, String emailAddress);

    /**
     * Sends email
     * @param email
     */
    void sendEmail(Email email);

    /**
     * Saves email to the database
     * @param email
     */
    void saveEmail(Email email);
}
