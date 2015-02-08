package com.cm.service;

import com.cm.domain.model.User;

import java.util.List;

public interface UserService {

    /**
     * Returns all users for the (@link type)
     * @param type
     * @return
     */
    List<User> getUsersByType(User.UserTypes type);
}
