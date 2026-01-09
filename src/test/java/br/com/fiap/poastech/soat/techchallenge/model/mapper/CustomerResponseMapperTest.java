package br.com.fiap.poastech.soat.techchallenge.model.mapper;

import br.com.fiap.postech.soat.techchallenge.model.domain.Customer;
import br.com.fiap.postech.soat.techchallenge.model.dto.response.CustomerResponse;
import br.com.fiap.postech.soat.techchallenge.model.mapper.CustomerResponseMapper;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerResponseMapperTest {

    private final CustomerResponseMapper mapper = new CustomerResponseMapper() {};

    @Test
    void deveMapearEntityParaResponse() {
        UUID id = UUID.randomUUID();
        Customer customer = new Customer(id, "Maria Eduarda", "123", "maria.eduarda@domain.com", "11987654321");

        CustomerResponse response = mapper.toResponse(customer);

        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(id);
        assertThat(response.name()).isEqualTo("Maria Eduarda");
        assertThat(response.cpf()).isEqualTo("123");
        assertThat(response.email()).isEqualTo("maria.eduarda@domain.com");
        assertThat(response.phone()).isEqualTo("11987654321");
    }

    @Test
    void deveRetornarNullQuandoInputNull() {
        CustomerResponse response = mapper.toResponse(null);
        assertThat(response).isNull();
    }
}
