package br.com.fiap.postech.soat.techchallenge.domain.models;

import br.com.fiap.postech.soat.techchallenge.domain.exceptions.*;
import org.apache.commons.lang3.StringUtils;
import java.math.BigDecimal;
import java.util.UUID;

public class Product {

    private UUID id;
    private String name;
    private BigDecimal price;
    private ProductCategory category;
    private String description;
    private boolean active;
    private String imageUrl;

    public Product(UUID id, String name, BigDecimal price, ProductCategory category, String description, boolean active, String imageUrl) {
        this.setId(id);
        this.setName(name);
        this.setPrice(price);
        this.setCategory(category);
        this.setDescription(description);
        this.setActive(active);
        this.setImageUrl(imageUrl);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }

    public String getImageUrl() {
        return imageUrl;
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
        this.description = description != null ? description.trim() : null;
    }

    private void setActive(boolean active) {
        this.active = active;
    }

    private void setImageUrl(String imageUrl) {
        if (StringUtils.isBlank(imageUrl)) {
            throw new InvalidImageUrlException("Image URL cannot be empty.");
        }
        this.imageUrl = imageUrl;
    }

    public void update(String name, BigDecimal price, ProductCategory category, String description, String imageUrl) {
        this.setName(name);
        this.setPrice(price);
        this.setCategory(category);
        this.setDescription(description);
        this.setImageUrl(imageUrl);
    }

    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }
}
