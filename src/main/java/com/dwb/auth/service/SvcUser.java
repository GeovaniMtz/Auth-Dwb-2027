package com.dwb.auth.service;

import java.util.List;

import com.dwb.auth.dto.out.UserResponse;
import com.dwb.auth.entity.User;

public interface SvcUser {
    // Crear un usuario
    UserResponse createUser(User user);

    // Obtener todos los usuarios
    List<UserResponse> getUsers();
}