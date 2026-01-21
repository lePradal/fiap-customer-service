package br.com.fiap.postech.soat.techchallenge.application.service;

import br.com.fiap.postech.soat.techchallenge.application.exceptions.CustomerAlreadyExistsException;
import br.com.fiap.postech.soat.techchallenge.application.exceptions.NotFoundException;
import br.com.fiap.postech.soat.techchallenge.domain.mapper.CustomerMapper;
import br.com.fiap.postech.soat.techchallenge.application.dto.response.CustomerResponse;
import br.com.fiap.postech.soat.techchallenge.infraestructure.persistence.CustomerDocument;
import br.com.fiap.postech.soat.techchallenge.infraestructure.persistence.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomerService {

    @Autowired
    private CustomerRepository repository;
    @Autowired
    private CustomerMapper mapper;

    public Optional<CustomerResponse> getCustomerById(UUID customerId) {
        log.info("Fetching customer with ID: {}", customerId);
        Optional<CustomerDocument> entity = repository.findById(customerId);

        entity.orElseThrow(() -> new NotFoundException("Customer not found."));

        return Optional.ofNullable(mapper.toResponse(entity));
    }

    public CustomerResponse getCustomerByCpf(String cpf) {
        log.info("Fetching customer with CPF: {}", cpf);
        Optional<CustomerDocument> byCpf = repository.findByCpf(cpf);
        byCpf.orElseThrow(() -> new NotFoundException("Customer not found."));
        return mapper.toResponse(byCpf);
    }

    public CustomerResponse createCustomer(String name, String cpf, String email, String phone) {
        log.info("Creating customer with CPF: {}", cpf);

        Optional<CustomerDocument> existingCustomer = repository.findByCpf(cpf);
        if (existingCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer with CPF " + cpf + " already exists.");
        }

        CustomerDocument customer = new CustomerDocument(String.valueOf(UUID.randomUUID()), name, cpf, email, phone);
        repository.save(customer);
        return mapper.toResponse(Optional.of(customer));
    }

    public void deleteCustomer(String cpf){
        log.info("Deleting customer with CPF: {}", cpf);

        repository.deleteByCpf(cpf);
    }
}
