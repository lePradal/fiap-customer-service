package br.com.fiap.postech.soat.techchallenge.adapter.in.web.api;

import br.com.fiap.postech.soat.techchallenge.adapter.in.web.dto.request.*;
import br.com.fiap.postech.soat.techchallenge.adapter.in.web.dto.response.ProductResponse;
import br.com.fiap.postech.soat.techchallenge.domain.models.ProductCategory;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/products")
public interface ProductAPI {

    @PostMapping
    ResponseEntity<Void> createProduct(@Valid @RequestBody CreateProductRequest request);

    @PutMapping("/{id}")
    ResponseEntity<Void> updateProduct(@Valid @PathVariable UUID id, @RequestBody UpdateProductRequest request);

    @GetMapping("/{id}")
    ResponseEntity<ProductResponse> getProductById(@PathVariable UUID id);

    @GetMapping
    ResponseEntity<List<ProductResponse>> getProducts(@RequestParam ProductCategory category, @RequestParam Boolean active);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteProduct(@PathVariable UUID id);

    @PatchMapping("/{id}/activate")
    ResponseEntity<Void> activateProduct(@PathVariable UUID id);

    @PatchMapping("/{id}/deactivate")
    ResponseEntity<Void> deactivateProduct(@PathVariable UUID id);
}

