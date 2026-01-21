package br.com.fiap.poastech.soat.techchallenge.service;

import br.com.fiap.postech.soat.techchallenge.application.exceptions.CustomerAlreadyExistsException;
import br.com.fiap.postech.soat.techchallenge.application.exceptions.NotFoundException;
import br.com.fiap.postech.soat.techchallenge.application.dto.response.CustomerResponse;
import br.com.fiap.postech.soat.techchallenge.domain.mapper.CustomerMapper;
import br.com.fiap.postech.soat.techchallenge.infraestructure.persistence.CustomerDocument;
import br.com.fiap.postech.soat.techchallenge.infraestructure.persistence.CustomerRepository;
import br.com.fiap.postech.soat.techchallenge.application.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;

    @Mock
    private CustomerMapper mapper;

    @InjectMocks
    private CustomerService service;

    @Test
    void deveBuscarClientePorCpf() {
        UUID id = UUID.randomUUID();
        CustomerDocument entity = new CustomerDocument(String.valueOf(id), "Maria Eduarda", "68747772034", "maria.eduarda@domain.com", "11987654321");

        when(repository.findByCpf("68747772034")).thenReturn(Optional.of(entity));
        when(mapper.toResponse(ArgumentMatchers.<Optional<CustomerDocument>>any())).thenAnswer(invocation -> {
            Optional<CustomerDocument> opt = invocation.getArgument(0);
            CustomerDocument e = opt.orElse(null);
            return e == null ? null : new CustomerResponse(e.getId(), e.getName(), e.getCpf(), e.getEmail(), e.getPhone());
        });

        CustomerResponse result = service.getCustomerByCpf("68747772034");

        assertThat(result).isNotNull();
        assertThat(result.cpf()).isEqualTo("68747772034");
    }

    @Test
    void deveLancarNotFoundQuandoCpfNaoExiste() {
        when(repository.findByCpf("68747772034")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getCustomerByCpf("68747772034"))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Customer not found.");
    }

    @Test
    void deveBuscarPorId() {
        UUID id = UUID.randomUUID();
        CustomerDocument entity = new CustomerDocument(String.valueOf(id), "Maria Eduarda", "68747772034", "maria.eduarda@domain.com", "11987654321");

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toResponse(ArgumentMatchers.<Optional<CustomerDocument>>any())).thenAnswer(invocation -> {
            Optional<CustomerDocument> opt = invocation.getArgument(0);
            CustomerDocument e = opt.orElse(null);
            return e == null ? null : new CustomerResponse(e.getId(), e.getName(), e.getCpf(), e.getEmail(), e.getPhone());
        });

        Optional<CustomerResponse> result = service.getCustomerById(id);

        assertThat(result).isPresent();
        assertThat(result.get().cpf()).isEqualTo("68747772034");
    }

    @Test
    void deveCriarClienteQuandoNaoExiste() {
        when(repository.findByCpf("68747772034")).thenReturn(Optional.empty());
        when(repository.save(ArgumentMatchers.any(CustomerDocument.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(mapper.toResponse(ArgumentMatchers.<Optional<CustomerDocument>>any())).thenAnswer(invocation -> {
            Optional<CustomerDocument> opt = invocation.getArgument(0);
            CustomerDocument e = opt.orElse(null);
            return e == null ? null : new CustomerResponse(e.getId(), e.getName(), e.getCpf(), e.getEmail(), e.getPhone());
        });

        CustomerResponse result = service.createCustomer("Maria Eduarda", "68747772034", "maria.eduarda@domain.com", "11987654321");

        assertThat(result).isNotNull();
        assertThat(result.cpf()).isEqualTo("68747772034");
        verify(repository).save(ArgumentMatchers.any(CustomerDocument.class));
    }

    @Test
    void deveLancarErroQuandoClienteJaExiste() {
        CustomerDocument entity = new CustomerDocument(String.valueOf(UUID.randomUUID()), "Maria Eduarda", "68747772034", "maria.eduarda@domain.com", "11987654321");

        when(repository.findByCpf("68747772034")).thenReturn(Optional.of(entity));

        assertThatThrownBy(() -> service.createCustomer("Maria Eduarda", "68747772034", "maria.eduarda@domain.com", "11987654321"))
                .isInstanceOf(CustomerAlreadyExistsException.class);
    }

    @Test
    void deveDeletarClientePorCpf() {
        String cpf = "68747772034";

        service.deleteCustomer(cpf);

        verify(repository).deleteByCpf(cpf);
    }


}
