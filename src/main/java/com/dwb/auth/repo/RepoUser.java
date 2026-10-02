package com.dwb.auth.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dwb.auth.entity.User;

public interface RepoUser extends JpaRepository<User, Long> {

    // Buscar por username — lo usamos la próxima semana para el login
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);
}
