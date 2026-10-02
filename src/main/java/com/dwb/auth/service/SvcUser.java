package com.dwb.auth.service;

import java.util.List;

import com.dwb.auth.dto.in.UserRequest;
import com.dwb.auth.dto.out.UserResponse;

public interface SvcUser {

    // Crear un usuario
    String createUser(UserRequest userRequest);

    // Obtener todos los usuarios
    List<UserResponse> getUsers();

    // Eliminar un usuario
    String deleteUser(Long id);
}
