package com.dwb.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dwb.auth.dto.in.UserRequest;
import com.dwb.auth.dto.out.UserResponse;
import com.dwb.auth.service.SvcUser;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@Tag(name = "Usuarios", description = "Registro y cosulta de usuarios")
public class CtrlUser {

    @Autowired
    private SvcUser svcUser;

    @Operation(summary = "Registrar usuario", description = "Público. Asigna el rol de User")
    @PostMapping
    public String create(@Valid @RequestBody UserRequest request) {
        return svcUser.createUser(request);
    }

    @Operation(summary = "Lista de usuarios", description = "Requiere token con rol Administrator")
    @GetMapping
    public List<UserResponse> getUsers() {
        return svcUser.getUsers();
    }
}
