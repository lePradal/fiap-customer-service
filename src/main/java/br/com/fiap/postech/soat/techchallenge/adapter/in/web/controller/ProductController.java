package br.com.fiap.postech.soat.techchallenge.adapter.in.web.controller;

import br.com.fiap.postech.soat.techchallenge.adapter.in.web.api.ProductAPI;
import br.com.fiap.postech.soat.techchallenge.adapter.in.web.dto.request.CreateProductRequest;
import br.com.fiap.postech.soat.techchallenge.adapter.in.web.dto.request.UpdateProductRequest;
import br.com.fiap.postech.soat.techchallenge.adapter.in.web.dto.response.ProductResponse;
import br.com.fiap.postech.soat.techchallenge.adapter.in.web.mapper.ProductResponseMapper;
import br.com.fiap.postech.soat.techchallenge.domain.models.Product;
import br.com.fiap.postech.soat.techchallenge.domain.models.ProductCategory;
import br.com.fiap.postech.soat.techchallenge.domain.ports.in.ManageProductUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController implements ProductAPI {

    private final ManageProductUseCase useCase;
    private final ProductResponseMapper mapper;

    @Override
    public ResponseEntity<ProductResponse> getProductById(UUID id) {
        Product product = useCase.getProductById(id);
        ProductResponse response = mapper.toResponse(product);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> createProduct(@Valid @RequestBody CreateProductRequest request) {
        useCase.createProduct(request.name(), request.price(), request.category(), request.description());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> updateProduct(UUID id, @Valid @RequestBody UpdateProductRequest request) {
        useCase.updateProduct(id, request.name(), request.price(), request.category(), request.description());
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<ProductResponse>> getProducts(
            @RequestParam(name = "category", required = false) ProductCategory category,
            @RequestParam(name = "active", required = false) Boolean active) {

        List<Product> products = useCase.getProducts(category, active);

        List<ProductResponse> response = products
                .stream().map(mapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        useCase.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> activateProduct(@PathVariable UUID id) {
        useCase.activateProduct(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deactivateProduct(@PathVariable UUID id) {
        useCase.deactivateProduct(id);
        return ResponseEntity.noContent().build();
    }
}
