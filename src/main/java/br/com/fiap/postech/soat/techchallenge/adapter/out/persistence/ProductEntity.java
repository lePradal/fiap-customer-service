package br.com.fiap.postech.soat.techchallenge.adapter.out.persistence;

import br.com.fiap.postech.soat.techchallenge.domain.models.ProductCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Id
    private UUID id;

    private String name;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    private String description;
    private boolean active;
}