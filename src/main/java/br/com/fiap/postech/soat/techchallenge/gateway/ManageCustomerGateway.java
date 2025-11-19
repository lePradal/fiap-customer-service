package br.com.fiap.postech.soat.techchallenge.gateway;

import br.com.fiap.postech.soat.techchallenge.model.domain.Customer;

import java.util.Optional;
import java.util.UUID;

public interface ManageCustomerGateway {
    void save(Customer customer);
    Optional<Customer> findById(UUID id);
    Optional<Customer> findByCpf(String cpf);
}
