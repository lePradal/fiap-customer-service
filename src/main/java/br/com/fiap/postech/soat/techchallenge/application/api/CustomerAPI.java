package br.com.fiap.postech.soat.techchallenge.application.api;

import br.com.fiap.postech.soat.techchallenge.model.dto.request.CreateCustomerRequest;
import br.com.fiap.postech.soat.techchallenge.model.dto.response.CustomerResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RequestMapping("/customers")
public interface CustomerAPI {

    @PostMapping
    ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CreateCustomerRequest customerRequest);

    @GetMapping("/{id}")
    ResponseEntity<Optional<CustomerResponse>> getById(@PathVariable UUID id);

    @GetMapping("/cpf/{cpf}")
    ResponseEntity<CustomerResponse> getByCpf(@PathVariable String cpf);

    @DeleteMapping("{cpf}")
    ResponseEntity<Void> deleteCustomer(@PathVariable String cpf);
}
