package br.com.fiap.postech.soat.techchallenge.domain.models;

import br.com.fiap.postech.soat.techchallenge.domain.exceptions.InvalidIdException;
import br.com.fiap.postech.soat.techchallenge.domain.exceptions.ListEmptyException;

import java.util.UUID;

public class OrderItem {
    private UUID id;
    private Integer quantity = 0;
    private Product product;

    public OrderItem(UUID id, Integer quantity, Product product) {
        this.setId(id);
        this.setQuantity(quantity);
        this.setProduct(product);
    }

    public UUID getId() {
        return id;
    }

    private void setId(UUID id) {
        if (id == null) {
            throw new InvalidIdException("Item ID cannot be null.");
        }
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    private void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    private void setQuantity(Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new ListEmptyException("Quantity must be greater than zero.");
        }

        this.quantity = quantity;
    }
}
