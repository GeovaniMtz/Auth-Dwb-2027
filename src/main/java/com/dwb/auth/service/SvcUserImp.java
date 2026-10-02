package com.dwb.auth.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dwb.auth.dto.in.UserRequest;
import com.dwb.auth.dto.out.UserResponse;
import com.dwb.auth.entity.User;
import com.dwb.auth.exception.DuplicateFieldException;
import com.dwb.auth.repo.RepoUser;

import jakarta.transaction.Transactional;

@Service
public class SvcUserImp implements SvcUser {

    @Autowired
    private RepoUser repoUser;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String createUser(UserRequest request) {

        // 1. Reglas de negocio: ¿ya existe?
        if (repoUser.existsByUsername(request.getUsername())) {
            throw new DuplicateFieldException("El username ya está registrado");
        }
        if (repoUser.existsByEmail(request.getEmail())) {
            throw new DuplicateFieldException("El email ya está registrado");
        }
        if (repoUser.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new DuplicateFieldException("El teléfono ya está registrado");
        }

        // 2. Armar la entidad campo por campo
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());

        // 3. Encriptamos DESPUÉS de pasar las validaciones del DTO
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // 4. El rol lo decide el servidor, nunca el cliente
        user.setRoles(Set.of("User"));

        repoUser.save(user);
        return "Usuario registrado exitosamente";
    }

    @Override
    public List<UserResponse> getUsers() {
        return repoUser.findAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }
}
