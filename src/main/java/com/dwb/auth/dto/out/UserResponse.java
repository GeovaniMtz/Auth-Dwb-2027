package com.dwb.auth.dto.out;

import java.util.Set;

import com.dwb.auth.entity.User;

import lombok.Data;

@Data
public class UserResponse {

    private String username;
    private String email;
    private Set<String> roles;

    
    public UserResponse(User user){
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.roles = user.getRoles();
    
    }
}
