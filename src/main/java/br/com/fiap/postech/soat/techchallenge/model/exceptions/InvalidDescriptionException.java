package br.com.fiap.postech.soat.techchallenge.model.exceptions;

public class InvalidDescriptionException extends RuntimeException {
    public InvalidDescriptionException(String message) {
        super(message);
    }

    public InvalidDescriptionException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDescriptionException(Throwable cause) {
        super(cause);
    }
}
