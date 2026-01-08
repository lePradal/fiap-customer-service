package br.com.fiap.postech.soat.techchallenge.application.controller;

import br.com.fiap.postech.soat.techchallenge.application.api.CustomerAPI;
import br.com.fiap.postech.soat.techchallenge.model.dto.request.CreateCustomerRequest;
import br.com.fiap.postech.soat.techchallenge.model.dto.response.CustomerResponse;
import br.com.fiap.postech.soat.techchallenge.model.mapper.CustomerResponseMapper;
import br.com.fiap.postech.soat.techchallenge.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController implements CustomerAPI {

    private final CustomerService service;
    private final CustomerResponseMapper responseMapper;

    @Operation(summary = "Customer registration")
    @Override
    public ResponseEntity<CustomerResponse> createCustomer(CreateCustomerRequest customerRequest) {
        CustomerResponse customer = service.createCustomer(customerRequest.name(), customerRequest.cpf(), customerRequest.email(), customerRequest.phone());
        return ResponseEntity.ok(customer);
    }

    @Operation(summary = "Search customer by ID")
    @Override
    public ResponseEntity<Optional<CustomerResponse>> getById(UUID id) {
        Optional<CustomerResponse> customer = service.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @Operation(summary = "Search customer by CPF")
    @Override
    public ResponseEntity<CustomerResponse> getByCpf(String cpf) {
        CustomerResponse customer = service.getCustomerByCpf(cpf);
        return ResponseEntity.ok(customer);

        
    }

    @Operation(summary = "Delete customer by CPF")
    @Override
    public ResponseEntity<Void> deleteCustomer(String cpf){
        service.deleteCustomer(cpf);

        return ResponseEntity.noContent().build();
    }
}
