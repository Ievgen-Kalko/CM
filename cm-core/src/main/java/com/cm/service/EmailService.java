package com.cm.service;

import com.cm.domain.model.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("com.cm.service.emailService")
@Transactional
public class EmailService {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
}
