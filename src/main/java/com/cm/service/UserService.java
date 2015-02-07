package com.cm.service;

import com.cm.domain.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsersByType(User.UserTypes type);
}
