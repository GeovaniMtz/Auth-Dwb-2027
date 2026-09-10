package com.dwb.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dwb.auth.entity.User;
import com.dwb.auth.repo.RepoUser;

@Service
public class SvcUserImp implements SvcUser {

    @Autowired
    private RepoUser repoUser;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return repoUser.save(user);

    }


    @Override
    public List<User> getUsers(){
        return repoUser.findAll();
    }

    
}
