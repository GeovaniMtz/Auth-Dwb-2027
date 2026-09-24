package com.dwb.auth.service;

import com.dwb.auth.dto.out.UserResponse;
import com.dwb.auth.entity.User;
import com.dwb.auth.repo.RepoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SvcUserImp implements SvcUser {

    @Autowired private RepoUser       repoUser;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public UserResponse createUser(User user) {
        // El rol siempre lo asigna el servidor — el cliente no decide
        user.setRoles(Set.of("User"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User saved = repoUser.save(user);
        return new UserResponse(saved);
    }

    @Override
    public List<UserResponse> getUsers() {
        return repoUser.findAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }
}