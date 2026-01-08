package br.com.fiap.postech.soat.techchallenge.application.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
