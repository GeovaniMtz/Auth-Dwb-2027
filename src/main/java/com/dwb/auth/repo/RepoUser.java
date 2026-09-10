package com.dwb.auth.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dwb.auth.entity.User;

@Repository
public interface RepoUser extends JpaRepository<User, Long> {

    //Buscar por username — lo usamos la próxima semana para el login
    Optional<User> findByUsername(String username);
}