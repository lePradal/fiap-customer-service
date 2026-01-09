package br.com.fiap.poastech.soat.techchallenge.model.mapper;

import br.com.fiap.postech.soat.techchallenge.model.domain.Customer;
import br.com.fiap.postech.soat.techchallenge.model.dto.response.CustomerResponse;
import br.com.fiap.postech.soat.techchallenge.model.mapper.CustomerMapper;
import br.com.fiap.postech.soat.techchallenge.persistence.CustomerEntity;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerMapperTest {

    private final CustomerMapper mapper = new CustomerMapper() {};

    @Test
    void deveMapearRequestParaEntity() {
        Customer customer = new Customer(UUID.randomUUID(), "Maria Eduarda", "68747772034", "maria.eduarda@domain.com", "11987654321");

        CustomerEntity entity = mapper.toEntity(customer);

        assertThat(entity).isNotNull();
        assertThat(entity.getCpf()).isEqualTo("68747772034");
        assertThat(entity.getName()).isEqualTo("Maria Eduarda");
        assertThat(entity.getEmail()).isEqualTo("maria.eduarda@domain.com");
        assertThat(entity.getPhone()).isEqualTo("11987654321");
    }

    @Test
    void deveRetornarNullAoMapearEntityQuandoInputNull() {
        CustomerEntity entity = mapper.toEntity(null);
        assertThat(entity).isNull();
    }

    @Test
    void deveMapearEntityParaResponseQuandoPresente() {
        UUID id = UUID.randomUUID();
        CustomerEntity entity = new CustomerEntity(id, "Joao", "11122233344", "joao@dominio.com", "11999999999");

        CustomerResponse response = mapper.toResponse(Optional.of(entity));

        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(id);
        assertThat(response.cpf()).isEqualTo("11122233344");
        assertThat(response.name()).isEqualTo("Joao");
    }

    @Test
    void deveRetornarNullAoMapearResponseQuandoVazio() {
        CustomerResponse response = mapper.toResponse(Optional.empty());
        assertThat(response).isNull();
    }

    @Test
    void deveMapearEntityParaDomainQuandoPresente() {
        UUID id = UUID.randomUUID();
        CustomerEntity entity = new CustomerEntity(id, "Ana", "99988877766", "ana@dominio.com", "11912345678");

        Optional<Customer> domain = mapper.toDomain(Optional.of(entity));

        assertThat(domain).isPresent();
        assertThat(domain.get().getId()).isEqualTo(id);
        assertThat(domain.get().getCpf()).isEqualTo("99988877766");
        assertThat(domain.get().getName()).isEqualTo("Ana");
    }

    @Test
    void deveRetornarEmptyAoMapearDomainQuandoVazio() {
        Optional<Customer> domain = mapper.toDomain(Optional.empty());
        assertThat(domain).isEmpty();
    }
}
