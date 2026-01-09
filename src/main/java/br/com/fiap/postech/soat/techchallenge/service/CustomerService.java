package br.com.fiap.postech.soat.techchallenge.service;

import br.com.fiap.postech.soat.techchallenge.application.exceptions.CustomerAlreadyExistsException;
import br.com.fiap.postech.soat.techchallenge.application.exceptions.NotFoundException;
import br.com.fiap.postech.soat.techchallenge.model.mapper.CustomerMapper;
import br.com.fiap.postech.soat.techchallenge.model.domain.Customer;
import br.com.fiap.postech.soat.techchallenge.model.dto.response.CustomerResponse;
import br.com.fiap.postech.soat.techchallenge.persistence.CustomerEntity;
import br.com.fiap.postech.soat.techchallenge.persistence.JpaManageCustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class ManageCustomerService{

    private JpaManageCustomerRepository repository;
    private CustomerMapper mapper;

    public Optional<CustomerResponse> getCustomerById(UUID customerId) {
        log.info("Fetching customer with ID: {}", customerId);
        Optional<CustomerEntity> entity = repository.findById(customerId);

        entity.orElseThrow(() -> new NotFoundException("Customer not found."));

        return Optional.ofNullable(mapper.toResponse(entity));
    }

    public CustomerResponse getCustomerByCpf(String cpf) {
        log.info("Fetching customer with CPF: {}", cpf);
        Optional<CustomerEntity> byCpf = repository.findByCpf(cpf);
        byCpf.orElseThrow(() -> new NotFoundException("Customer not found."));
        return mapper.toResponse(byCpf);
    }

    public CustomerResponse createCustomer(String name, String cpf, String email, String phone) {
        log.info("Creating customer with CPF: {}", cpf);

        Optional<CustomerEntity> existingCustomer = repository.findByCpf(cpf);
        if (existingCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer with CPF " + cpf + " already exists.");
        }

        CustomerEntity customer = new CustomerEntity(UUID.randomUUID(), name, cpf, email, phone);
        repository.save(customer);
        return mapper.toResponse(Optional.of(customer));
    }

    public void deleteCustomer(String cpf){
        log.info("Deleting customer with CPF: {}", cpf);

        repository.deleteByCpf(cpf);
    }
}
