package br.com.fiap.poastech.soat.techchallenge.model.dto.response;

import br.com.fiap.postech.soat.techchallenge.model.dto.response.CustomerResponse;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerResponseTest {

    @Test
    void recordAccessors() {
        UUID id = UUID.randomUUID();
        CustomerResponse r = new CustomerResponse(id, "Nome", "123", "a@b.com", "119");
        assertThat(r.id()).isEqualTo(id);
        assertThat(r.name()).isEqualTo("Nome");
        assertThat(r.cpf()).isEqualTo("123");
    }
}

