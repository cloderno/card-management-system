package com.cloderno.card_management_system.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     *
     * HashMap не гарантирует порядок ключей → при сериализации в JSON порядок может быть случайным.
     * LinkedHashMap сохраняет порядок вставки ключей → JSON будет читабельным, например:
     *
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", String.valueOf(LocalDateTime.now()));
        body.put("status", String.valueOf(400));
        body.put("error", "Bad Request");
        body.put("path", request.getRequestURI());

        List<Map<String, String>> errors = exception.getBindingResult().getFieldErrors().stream()
                        .map(error -> {
                            assert error.getDefaultMessage() != null;
                            return Map.of(
                                    "field", error.getField(),
                                    "message", error.getDefaultMessage()
                            );
                        })
                        .toList();

        body.put("errors", errors);

        return ResponseEntity.badRequest().body(body);
    }
}
