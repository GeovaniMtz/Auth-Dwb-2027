package com.dwb.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dwb.auth.entity.User;
import com.dwb.auth.service.SvcUser;

@RestController
@RequestMapping("/user")
public class CtrlUser {
    
    @Autowired
    private SvcUser svcUser;

    @PostMapping
    public User create(@RequestBody User user){
        return svcUser.createUser(user);
    }

    @GetMapping
    public List<User> getUsers(){
        return svcUser.getUsers();
    }
}
