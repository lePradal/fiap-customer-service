package br.com.fiap.poastech.soat.techchallenge.application.handler;

import br.com.fiap.postech.soat.techchallenge.application.handler.GlobalExceptionHandler;
import br.com.fiap.postech.soat.techchallenge.application.handler.SimpleError;
import br.com.fiap.postech.soat.techchallenge.application.exceptions.CustomerAlreadyExistsException;
import br.com.fiap.postech.soat.techchallenge.application.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void handleConflict() {
        CustomerAlreadyExistsException ex = new CustomerAlreadyExistsException("exists");
        ResponseEntity<SimpleError> resp = handler.handleConflict(ex);

        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(resp.getBody()).isNotNull();
        assertThat(resp.getBody().getMessage()).isEqualTo("exists");
    }

    @Test
    void handleNotFound() {
        NotFoundException ex = new NotFoundException("notfound");
        ResponseEntity<SimpleError> resp = handler.handleNotFound(ex);

        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(resp.getBody()).isNotNull();
        assertThat(resp.getBody().getMessage()).isEqualTo("notfound");
    }

    @Test
    void handleValidation_withFieldErrors() {
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        BindingResult binding = mock(BindingResult.class);
        FieldError fieldError = new FieldError("obj", "field", "msg");

        when(ex.getBindingResult()).thenReturn(binding);
        when(binding.getFieldErrors()).thenReturn(List.of(fieldError));

        ResponseEntity<SimpleError> resp = handler.handleValidation(ex);

        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(resp.getBody()).isNotNull();
        assertThat(resp.getBody().getMessage()).contains("field: msg");
    }

    @Test
    void handleValidation_withoutFieldErrors() {
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        BindingResult binding = mock(BindingResult.class);

        when(ex.getBindingResult()).thenReturn(binding);
        when(binding.getFieldErrors()).thenReturn(List.of());

        ResponseEntity<SimpleError> resp = handler.handleValidation(ex);

        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(resp.getBody()).isNotNull();
        assertThat(resp.getBody().getMessage()).isEqualTo("Dados inválidos");
    }

    @Test
    void handleMethodNotAllowed() {
        HttpRequestMethodNotSupportedException ex = new HttpRequestMethodNotSupportedException("GET");
        ResponseEntity<SimpleError> resp = handler.handleMethodNotAllowed(ex);

        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.METHOD_NOT_ALLOWED);
        assertThat(resp.getBody()).isNotNull();
        assertThat(resp.getBody().getMessage()).contains("Método HTTP não permitido");
    }
}

