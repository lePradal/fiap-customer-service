package br.com.fiap.postech.soat.techchallenge.domain.exceptions;

public class InvalidCpfException extends RuntimeException {
    public InvalidCpfException(String message) {
        super(message);
    }

    public InvalidCpfException(String message, Throwable cause) {
      super(message, cause);
    }

    public InvalidCpfException(Throwable cause) {
      super(cause);
    }
}
