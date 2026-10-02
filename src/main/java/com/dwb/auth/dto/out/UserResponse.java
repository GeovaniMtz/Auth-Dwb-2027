package com.dwb.auth.dto.out;

import java.util.Set;

import com.dwb.auth.entity.User;

import lombok.Data;

@Data
public class UserResponse {

    private String username;
    private String email;
    private String name;
    private String lastName;
    private String phoneNumber;
    private Set<String> roles;

    public UserResponse(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.phoneNumber = user.getPhoneNumber();
        this.roles = user.getRoles();
    }
}
