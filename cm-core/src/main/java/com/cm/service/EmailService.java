package com.cm.service;

import com.cm.domain.model.Coin;
import com.cm.domain.model.Email;

public interface EmailService {

    Email composeEmailForClient(Coin coin, String emailAddress);

    Email composeEmailForAdmin(Coin coin, String emailAddress);

    void sendEmail(Email email);

    void saveEmail(Email email);
}
