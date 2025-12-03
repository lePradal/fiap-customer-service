package br.com.fiap.postech.soat.techchallenge.application.handler;

import java.util.List;

public record ValidationErrorResponse(int status, List<String> errors) {
}

