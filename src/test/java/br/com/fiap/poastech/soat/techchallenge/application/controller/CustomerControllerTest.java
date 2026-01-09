package br.com.fiap.poastech.soat.techchallenge.application.controller;

import br.com.fiap.postech.soat.techchallenge.application.controller.CustomerController;
import br.com.fiap.postech.soat.techchallenge.model.dto.request.CreateCustomerRequest;
import br.com.fiap.postech.soat.techchallenge.model.dto.response.CustomerResponse;
import br.com.fiap.postech.soat.techchallenge.model.mapper.CustomerResponseMapper;
import br.com.fiap.postech.soat.techchallenge.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerService service;

    @Mock
    private CustomerResponseMapper responseMapper;

    @InjectMocks
    private CustomerController controller;

    @Test
    void createCustomer_returnsOk() {
        CreateCustomerRequest req = new CreateCustomerRequest("Nome","123","a@b.com","119");
        CustomerResponse resp = new CustomerResponse(UUID.randomUUID(), "Nome","123","a@b.com","119");

        when(service.createCustomer(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(resp);

        var result = controller.createCustomer(req);

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody()).isEqualTo(resp);
    }

    @Test
    void getById_returnsOptional() {
        UUID id = UUID.randomUUID();
        CustomerResponse resp = new CustomerResponse(id, "Nome","123","a@b.com","119");
        when(service.getCustomerById(id)).thenReturn(Optional.of(resp));

        var result = controller.getById(id);

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody()).isPresent();
        assertThat(result.getBody().get()).isEqualTo(resp);
    }

    @Test
    void getByCpf_returnsOk() {
        CustomerResponse resp = new CustomerResponse(UUID.randomUUID(), "Nome","123","a@b.com","119");
        when(service.getCustomerByCpf("123")).thenReturn(resp);

        var result = controller.getByCpf("123");

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody()).isEqualTo(resp);
    }

    @Test
    void deleteCustomer_returnsNoContent() {
        var result = controller.deleteCustomer("123");
        assertThat(result.getStatusCodeValue()).isEqualTo(204);
    }
}

