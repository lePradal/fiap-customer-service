package br.com.fiap.postech.soat.techchallenge.domain.exceptions;

public class InvalidPhoneException extends RuntimeException {
    public InvalidPhoneException(String message) {
        super(message);
    }

    public InvalidPhoneException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPhoneException(Throwable cause) {
        super(cause);
    }
}
