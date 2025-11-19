package br.com.fiap.postech.soat.techchallenge.model.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CustomerIdRequest(
        @NotNull(message = "{customer.id.notnull}")
        UUID id
) {}
