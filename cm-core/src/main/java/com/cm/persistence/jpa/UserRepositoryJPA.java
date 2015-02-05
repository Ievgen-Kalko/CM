package com.cm.persistence.jpa;

import com.cm.domain.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("com.cm.persistence.jpa.UserRepositoryJPA")
@Transactional
public class UserRepositoryJPA extends EntityRepositoryJPA<User, Long> {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserRepositoryJPA.class);

    @Override
    protected Class getActualClass() {
        return User.class;
    }
}
