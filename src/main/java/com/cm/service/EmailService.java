package com.cm.service;

import com.cm.domain.model.Coin;
import com.cm.domain.model.Email;
import com.cm.domain.model.User;

import java.util.List;

public interface EmailService {

    /**
     * Creates email for appropriate user type
     * @param coin
     * @param emailAddresses
     * @param userType
     * @return newly created email
     */
    Email composeEmail(Coin coin, List<String> emailAddresses, User.UserTypes userType);

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
