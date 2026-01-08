package br.com.fiap.postech.soat.techchallenge.application.handler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleError {

    private String message;

    public SimpleError(String message) {
        this.message = message;
    }
}
