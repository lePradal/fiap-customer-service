package br.com.fiap.postech.soat.techchallenge.application.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<SimpleError>> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex) {

        List<SimpleError> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new SimpleError(
                        error.getField(),
                        Objects.requireNonNull(error.getDefaultMessage())
                ))
                .toList();

        return ResponseEntity.badRequest().body(errors);
    }
}
