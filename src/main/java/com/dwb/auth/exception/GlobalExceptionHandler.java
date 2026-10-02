package com.dwb.auth.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // @Valid falló: junta todos los mensajes de UserRequest
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(
            MethodArgumentNotValidException ex) {

        String mensaje = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(" | "));

        return ResponseEntity.badRequest()
                .body(Map.of("error", mensaje));
    }

    // El body no se pudo leer como JSON (coma de más, comillas faltantes...)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleBadJson(
            HttpMessageNotReadableException ex) {

        return ResponseEntity.badRequest()
                .body(Map.of("error", "El cuerpo de la petición no es un JSON válido"));
    }

    // Lanzada por el servicio cuando existsBy encuentra el dato
    @ExceptionHandler(DuplicateFieldException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateField(
            DuplicateFieldException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("error", ex.getMessage()));
    }

    // Red de seguridad: el UNIQUE de la BD rechazó el insert (caso de carrera)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrity(
            DataIntegrityViolationException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("error", "Ya existe un usuario con ese username, email o teléfono"));
    }

    // Login incorrecto (usuario inexistente o password equivocado)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleBadCredentials(
            BadCredentialsException ex) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Username o contraseña incorrectos"));
    }

    // Último recurso
    // FIX 1: los errores propios de Spring (405, 415, 404...) conservan su status real
    // FIX 2: el 500 ya no expone ex.getMessage() al cliente; va al log
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneral(Exception ex) {

        if (ex instanceof ErrorResponse springError) {
            String detalle = Objects.requireNonNullElse(
                    springError.getBody().getDetail(), "Petición inválida");
            return ResponseEntity.status(springError.getStatusCode())
                    .body(Map.of("error", detalle));
        }

        log.error("Error no controlado", ex);
        return ResponseEntity.internalServerError()
                .body(Map.of("error", "Ocurrió un error interno. Intenta más tarde."));
    }
}
