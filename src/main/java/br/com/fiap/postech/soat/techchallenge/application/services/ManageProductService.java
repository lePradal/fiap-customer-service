package br.com.fiap.postech.soat.techchallenge.application.services;

import br.com.fiap.postech.soat.techchallenge.application.services.exceptions.NotFoundException;
import br.com.fiap.postech.soat.techchallenge.domain.ports.in.ManageProductUseCase;
import br.com.fiap.postech.soat.techchallenge.domain.ports.out.ProductRepository;
import br.com.fiap.postech.soat.techchallenge.domain.models.Product;
import br.com.fiap.postech.soat.techchallenge.domain.models.ProductCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ManageProductService implements ManageProductUseCase {
    private final ProductRepository productRepository;

    @Override
    public Product getProductById(UUID productId) {
        return productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found."));
    }

    @Override
    public List<Product> getProducts(ProductCategory category, Boolean active) {
        return productRepository.findByFilters(category, active);
    }

    @Override
    public void createProduct(String name, BigDecimal price, ProductCategory category, String description) {
        Product product = new Product(UUID.randomUUID(), name, price, category, description, true);

        productRepository.save(product);
    }

    @Override
    public void updateProduct(UUID productId, String name, BigDecimal price, ProductCategory category, String description) {
        Product product = getProductById(productId);
        product.update(name, price, category, description);
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(UUID productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public void activateProduct(UUID productId) {
        Product product = getProductById(productId);
        product.activate();
        productRepository.save(product);
    }

    @Override
    public void deactivateProduct(UUID productId) {
        Product product = getProductById(productId);
        product.deactivate();
        productRepository.save(product);
    }
}
