package br.com.fiap.postech.soat.techchallenge.domain.exceptions;

public class InvalidImageUrlException extends RuntimeException {
    public InvalidImageUrlException(String message) {
        super(message);
    }

    public InvalidImageUrlException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidImageUrlException(Throwable cause) {
        super(cause);
    }
}
