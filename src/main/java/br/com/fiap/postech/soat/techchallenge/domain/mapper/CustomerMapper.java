package br.com.fiap.postech.soat.techchallenge.domain.mapper;

import br.com.fiap.postech.soat.techchallenge.application.dto.response.CustomerResponse;
import br.com.fiap.postech.soat.techchallenge.infraestructure.persistence.CustomerDocument;
import br.com.fiap.postech.soat.techchallenge.domain.model.Customer;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    default Optional<Customer> toDomain(Optional<CustomerDocument> optional) {
        if (optional.isEmpty()) {
            return Optional.empty();
        }

        CustomerDocument entity = optional.get();
        Customer customer = new Customer(entity.getId(), entity.getName(), entity.getCpf(), entity.getEmail(), entity.getPhone());
        return Optional.of(customer);
    }

    default CustomerDocument toEntity(Customer customer){
        if (customer == null) return null;
        return new CustomerDocument(customer.getId(), customer.getName(), customer.getCpf(), customer.getEmail(), customer.getPhone());
    }

    default CustomerResponse toResponse(Optional<CustomerDocument> customer){
        return customer.map(customerEntity -> new CustomerResponse(customerEntity.getId(), customerEntity.getName(), customerEntity.getCpf(), customerEntity.getEmail(), customerEntity.getPhone())).orElse(null);
    }
}
