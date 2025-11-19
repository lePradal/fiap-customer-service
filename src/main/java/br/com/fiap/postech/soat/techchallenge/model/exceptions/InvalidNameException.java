package br.com.fiap.postech.soat.techchallenge.model.exceptions;

public class InvalidNameException extends RuntimeException {
    public InvalidNameException(String message) {
        super(message);
    }

    public InvalidNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidNameException(Throwable cause) {
        super(cause);
    }
}
