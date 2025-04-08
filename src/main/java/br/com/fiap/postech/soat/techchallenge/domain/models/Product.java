package br.com.fiap.postech.soat.techchallenge.domain.models;

import br.com.fiap.postech.soat.techchallenge.domain.exceptions.InvalidDescriptionException;
import br.com.fiap.postech.soat.techchallenge.domain.exceptions.InvalidIdException;
import br.com.fiap.postech.soat.techchallenge.domain.exceptions.InvalidNameException;
import br.com.fiap.postech.soat.techchallenge.domain.exceptions.InvalidPriceException;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class Product {
    private UUID id;
    private String name;
    private BigDecimal price;
    private ProductCategory category;
    private String description;
    private boolean active;

    public Product(UUID id, String name, BigDecimal price, ProductCategory category, String description, boolean active) {
        this.setId(id);
        this.setName(name);
        this.setPrice(price);
        this.setCategory(category);
        this.setDescription(description);
        this.setActive(active);
    }

    private void setId(UUID id) {
        if (id == null) {
            throw new InvalidIdException("Product ID cannot be null.");
        }
        this.id = id;
    }

    private void setName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new InvalidNameException("Product name cannot be empty.");
        }

        this.name = name.trim();
    }

    private void setPrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidPriceException("Price must be greater than zero.");
        }
        this.price = price;
    }

    private void setCategory(ProductCategory category) {
        this.category = category;
    }

    private void setDescription(String description) {
        if (StringUtils.isBlank(description)) {
            throw new InvalidDescriptionException("Product description cannot be empty.");
        }

        this.description = description.trim();
    }

    private void setActive(boolean active) {
        this.active = active;
    }

    public Product update(String name, BigDecimal price, ProductCategory category, String description) {
        this.setName(name);
        this.setPrice(price);
        this.setCategory(category);
        this.setDescription(description);

        return this;
    }

    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }
}
