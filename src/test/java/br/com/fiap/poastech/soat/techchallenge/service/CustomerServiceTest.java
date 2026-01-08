package br.com.fiap.poastech.soat.techchallenge.service;

import br.com.fiap.postech.soat.techchallenge.application.exceptions.CustomerAlreadyExistsException;
import br.com.fiap.postech.soat.techchallenge.model.domain.Customer;
import br.com.fiap.postech.soat.techchallenge.persistence.CustomerRepository;
import br.com.fiap.postech.soat.techchallenge.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.UUID;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService service;

    @Mock
    private CustomerRepository repository;

    @Test
    void deveCriarClienteQuandoNaoExiste() {
        when(repository.save(any())).thenReturn(new Customer(UUID.randomUUID(), "Maria Josefina", "11122233344", "maria.josefina@gmail.com", "11988887777"));

        assertDoesNotThrow(() -> service.create(mock(CustomerRequest.class)));
    }

    @Test
    void deveLancarExcecaoQuandoClienteJaExiste() {
        when(repository.existsByCpf(anyString())).thenReturn(true);

        assertThrows(CustomerAlreadyExistsException.class,
                () -> service.create(mock(CustomerRequest.class)));
    }
}
