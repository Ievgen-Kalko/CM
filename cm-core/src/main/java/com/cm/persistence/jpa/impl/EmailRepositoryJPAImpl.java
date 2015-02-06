package com.cm.persistence.jpa.impl;

import com.cm.domain.model.Email;
import com.cm.persistence.jpa.EmailRepositoryJPA;
import com.cm.persistence.jpa.EntityRepositoryJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("com.cm.persistence.jpa.EmailRepositoryJPA")
@Transactional
public class EmailRepositoryJPAImpl extends EntityRepositoryJPA<Email, Long> implements EmailRepositoryJPA {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailRepositoryJPAImpl.class);

    public EmailRepositoryJPAImpl() {
    }

    @Override
    protected Class getActualClass() {
        return Email.class;
    }
}
