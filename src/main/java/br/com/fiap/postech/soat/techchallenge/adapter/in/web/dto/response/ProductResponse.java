package br.com.fiap.postech.soat.techchallenge.adapter.in.web.dto.response;

import br.com.fiap.postech.soat.techchallenge.domain.models.ProductCategory;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(
        UUID id,
        String name,
        BigDecimal price,
        ProductCategory category,
        String description
) {
}
