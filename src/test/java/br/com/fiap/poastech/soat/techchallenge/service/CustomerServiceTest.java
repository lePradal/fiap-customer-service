package br.com.fiap.poastech.soat.techchallenge.service;

import br.com.fiap.postech.soat.techchallenge.application.exceptions.CustomerAlreadyExistsException;
import br.com.fiap.postech.soat.techchallenge.application.exceptions.NotFoundException;
import br.com.fiap.postech.soat.techchallenge.model.dto.response.CustomerResponse;
import br.com.fiap.postech.soat.techchallenge.model.mapper.CustomerMapper;
import br.com.fiap.postech.soat.techchallenge.persistence.CustomerEntity;
import br.com.fiap.postech.soat.techchallenge.persistence.CustomerRepository;
import br.com.fiap.postech.soat.techchallenge.service.CustomerService;
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
        CustomerEntity entity = new CustomerEntity(id, "Maria Eduarda", "68747772034", "maria.eduarda@domain.com", "11987654321");

        when(repository.findByCpf("68747772034")).thenReturn(Optional.of(entity));
        when(mapper.toResponse(ArgumentMatchers.<Optional<CustomerEntity>>any())).thenAnswer(invocation -> {
            Optional<CustomerEntity> opt = invocation.getArgument(0);
            CustomerEntity e = opt.orElse(null);
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
        CustomerEntity entity = new CustomerEntity(id, "Maria Eduarda", "68747772034", "maria.eduarda@domain.com", "11987654321");

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toResponse(ArgumentMatchers.<Optional<CustomerEntity>>any())).thenAnswer(invocation -> {
            Optional<CustomerEntity> opt = invocation.getArgument(0);
            CustomerEntity e = opt.orElse(null);
            return e == null ? null : new CustomerResponse(e.getId(), e.getName(), e.getCpf(), e.getEmail(), e.getPhone());
        });

        Optional<CustomerResponse> result = service.getCustomerById(id);

        assertThat(result).isPresent();
        assertThat(result.get().cpf()).isEqualTo("68747772034");
    }

    @Test
    void deveCriarClienteQuandoNaoExiste() {
        when(repository.findByCpf("68747772034")).thenReturn(Optional.empty());
        when(repository.save(ArgumentMatchers.any(CustomerEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(mapper.toResponse(ArgumentMatchers.<Optional<CustomerEntity>>any())).thenAnswer(invocation -> {
            Optional<CustomerEntity> opt = invocation.getArgument(0);
            CustomerEntity e = opt.orElse(null);
            return e == null ? null : new CustomerResponse(e.getId(), e.getName(), e.getCpf(), e.getEmail(), e.getPhone());
        });

        CustomerResponse result = service.createCustomer("Maria Eduarda", "68747772034", "maria.eduarda@domain.com", "11987654321");

        assertThat(result).isNotNull();
        assertThat(result.cpf()).isEqualTo("68747772034");
        verify(repository).save(ArgumentMatchers.any(CustomerEntity.class));
    }

    @Test
    void deveLancarErroQuandoClienteJaExiste() {
        CustomerEntity entity = new CustomerEntity(UUID.randomUUID(), "Maria Eduarda", "68747772034", "maria.eduarda@domain.com", "11987654321");

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
