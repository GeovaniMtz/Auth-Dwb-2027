package com.dwb.auth.service;

import java.util.List;

import com.dwb.auth.entity.User;

public interface SvcUser {

    User createUser(User user);

    List<User> getUsers();

}