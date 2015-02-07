package com.cm.persistence.jpa;

import com.cm.domain.model.User;
import com.cm.persistence.EntityRepository;

import java.util.List;

public interface UserRepositoryJPA extends EntityRepository<User, Long> {

    List<User> findByType(User.UserTypes type);
}
