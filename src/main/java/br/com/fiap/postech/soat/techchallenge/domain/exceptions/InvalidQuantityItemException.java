package br.com.fiap.postech.soat.techchallenge.domain.exceptions;

public class InvalidQuantityItemException extends RuntimeException {
    public InvalidQuantityItemException(String message) {
        super(message);
    }
}
