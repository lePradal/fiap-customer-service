package br.com.fiap.postech.soat.techchallenge.application.dto.response;

import java.util.UUID;

public record CustomerResponse(
        String id,
        String name,
        String cpf,
        String email,
        String phone
) {
}
