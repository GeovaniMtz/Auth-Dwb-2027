package com.dwb.auth.service;

import com.dwb.auth.entity.User;
import com.dwb.auth.repo.RepoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SvcUserImp implements SvcUser {

    @Autowired
    private RepoUser repoUser;

    @Override
    public User createUser(User user) {
        // Por ahora guardamos directo — el jueves 17 encriptamos aquí
        return repoUser.save(user);
    }

    @Override
    public List<User> getUsers() {
        return repoUser.findAll();
    }

}