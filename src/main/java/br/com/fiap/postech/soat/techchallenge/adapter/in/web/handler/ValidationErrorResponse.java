package br.com.fiap.postech.soat.techchallenge.adapter.in.web.handler;

import java.util.List;

public record ValidationErrorResponse(int status, List<String> errors) {
}

