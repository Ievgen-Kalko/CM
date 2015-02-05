package com.cm.persistence.jpa;

import com.cm.domain.model.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("com.cm.persistence.jpa.EmailRepositoryJPA")
@Transactional
public class EmailRepositoryJPA extends EntityRepositoryJPA<Email, Long> {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailRepositoryJPA.class);

    @Override
    protected Class getActualClass() {
        return Email.class;
    }
}
