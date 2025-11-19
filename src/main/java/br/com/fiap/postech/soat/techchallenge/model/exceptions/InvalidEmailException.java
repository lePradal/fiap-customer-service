package br.com.fiap.postech.soat.techchallenge.model.exceptions;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String message) {
        super(message);
    }

    public InvalidEmailException(String message, Throwable cause) {
      super(message, cause);
    }

    public InvalidEmailException(Throwable cause) {
    super(cause);
  }
}
