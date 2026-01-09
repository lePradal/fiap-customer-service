package br.com.fiap.postech.soat.techchallenge.mapper;

import br.com.fiap.postech.soat.techchallenge.model.dto.response.CustomerResponse;
import br.com.fiap.postech.soat.techchallenge.model.domain.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerResponseMapper {
    default CustomerResponse toResponse(Customer customer){
        if (customer == null) return null;
        return new CustomerResponse(customer.getId(), customer.getName(), customer.getCpf(), customer.getEmail(), customer.getPhone());
    }
}
