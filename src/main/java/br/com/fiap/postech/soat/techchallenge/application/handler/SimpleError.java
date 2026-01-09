package br.com.fiap.postech.soat.techchallenge.application.handler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleError {

    private String field;
    private String message;

    public SimpleError(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
