package com.dwb.auth.service;

import com.dwb.auth.entity.User;
import java.util.List;

public interface SvcUser{
    
    //Crear un usuario
    User createUser(User user);

    //Obtener todos los usuarios
    List<User> getUsers();
}
