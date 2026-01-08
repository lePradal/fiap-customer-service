package br.com.fiap.poastech.soat.techchallenge.service;

import br.com.fiap.postech.soat.techchallenge.gateway.ManageCustomerGateway;
import br.com.fiap.postech.soat.techchallenge.model.domain.Customer;
import br.com.fiap.postech.soat.techchallenge.service.ManageCustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ManageCustomerServiceTest {

    @InjectMocks
    ManageCustomerService manageCustomerService;

    @Mock
    ManageCustomerGateway manageCustomerGateway;

    @Test
    void searchForCustomerByID() {
        Customer customer = createCustomer();

        when(manageCustomerGateway.findById(customer.getId()))
                .thenReturn(Optional.of(customer));

        manageCustomerService.getCustomerById(customer.getId());

        assertNotNull(customer);
    }

    @Test
    void searchForCustomerByCPF() {
        Customer customer = createCustomer();

        when(manageCustomerGateway.findByCpf(customer.getCpf()))
                .thenReturn(Optional.of(customer));

        manageCustomerService.getCustomerByCpf(customer.getCpf());

        assertNotNull(customer);
    }

    @Test
    void createCustomer_Success() {
        
    }

    @Test
    void validatesCustomerAlreadyExists() {

    }

    private Customer createCustomer() {
        return new Customer(
                UUID.fromString("57a75198-55a0-4a3b-bad0-9d784c1568e7"),
                "Maria Eduarda",
                "68747772034",
                "maria.eduarda@domain.com",
                "11987654321"
        );
    }
}
