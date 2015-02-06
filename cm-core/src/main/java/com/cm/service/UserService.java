package com.cm.service;

import com.cm.domain.model.User;
import com.cm.persistence.jpa.UserRepositoryJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service("com.cm.service.userService")
@Transactional
public class UserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepositoryJPA userRepository;

    public UserService() {
    }

    public List<User> getUsersByType(User.UserTypes type) {
        Assert.notNull(type, "method was invoked with null arg");

        return userRepository.findByType(type);
    }
}
