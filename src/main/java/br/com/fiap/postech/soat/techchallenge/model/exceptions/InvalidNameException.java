package br.com.fiap.postech.soat.techchallenge.model.exceptions;

public class InvalidNameException extends RuntimeException {
    public InvalidNameException(String message) {
        super(message);
    }
}
