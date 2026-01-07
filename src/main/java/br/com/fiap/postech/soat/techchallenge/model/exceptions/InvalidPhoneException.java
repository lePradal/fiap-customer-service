package br.com.fiap.postech.soat.techchallenge.model.exceptions;

public class InvalidPhoneException extends RuntimeException {
    public InvalidPhoneException(String message) {
        super(message);
    }
}
