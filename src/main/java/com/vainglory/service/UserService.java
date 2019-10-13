package com.vainglory.service;

import com.vainglory.domain.User;

import java.util.List;

public interface UserService {
    List<User> getUserList();

    User findByusername(String username);

    User queryUserByUsername(String username);

    void register(User user);
}
