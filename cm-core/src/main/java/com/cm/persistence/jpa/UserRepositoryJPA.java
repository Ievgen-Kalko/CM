package com.cm.persistence.jpa;

import com.cm.domain.model.User;
import com.cm.persistence.EntityRepository;

import java.util.List;

public interface UserRepositoryJPA extends EntityRepository<User, Long> {

    /**
     * Finds users by (@link type)
     * @param type
     * @return lists of users with the following (@link type)
     */
    List<User> findByType(User.UserTypes type);
}
