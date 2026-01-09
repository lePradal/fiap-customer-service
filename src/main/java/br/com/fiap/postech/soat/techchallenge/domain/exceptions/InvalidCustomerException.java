package br.com.fiap.postech.soat.techchallenge.domain.exceptions;

public class InvalidCustomerException extends RuntimeException {
    public InvalidCustomerException(String message) {
        super(message);
    }

    public InvalidCustomerException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCustomerException(Throwable cause) {
        super(cause);
    }
}
