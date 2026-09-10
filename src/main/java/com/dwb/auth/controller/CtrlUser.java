package com.dwb.auth.controller;

import com.dwb.auth.entity.User;
import com.dwb.auth.service.SvcUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
