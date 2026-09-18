package com.groupeisi.HelloSpring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntrepriseNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleEntrepriseNotFound(
            EntrepriseNotFoundException exception) {

        Map<String, Object> erreur = new LinkedHashMap<>();

        erreur.put("date", LocalDateTime.now());
        erreur.put("status", HttpStatus.NOT_FOUND.value());
        erreur.put("error", "Not Found");
        erreur.put("message", exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(erreur);
    }


    @ExceptionHandler(StageNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleStageNotFound(
            StageNotFoundException exception) {

        Map<String, Object> erreur = new LinkedHashMap<>();

        erreur.put("date", LocalDateTime.now());
        erreur.put("status", HttpStatus.NOT_FOUND.value());
        erreur.put("error", "Not Found");
        erreur.put("message", exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(erreur);
    }
}
