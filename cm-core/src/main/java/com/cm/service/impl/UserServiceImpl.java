package com.cm.service.impl;

import com.cm.domain.model.User;
import com.cm.persistence.jpa.UserRepositoryJPA;
import com.cm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service("com.cm.service.UserServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepositoryJPA userRepository;

    public UserServiceImpl() {
    }

    @Override
    public List<User> getUsersByType(User.UserTypes type) {
        Assert.notNull(type, "method was invoked with null arg");

        return userRepository.findByType(type);
    }
}
