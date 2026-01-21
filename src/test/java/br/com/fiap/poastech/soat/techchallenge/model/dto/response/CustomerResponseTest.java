package br.com.fiap.poastech.soat.techchallenge.model.dto.response;

import br.com.fiap.postech.soat.techchallenge.application.dto.response.CustomerResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerResponseTest {

    @Test
    void recordAccessors() {
        CustomerResponse r = new CustomerResponse("ba8ac0a7-07a9-4ab6-9041-ebddd476a214", "Nome", "123", "a@b.com", "119");
        assertThat(r.id()).isEqualTo("ba8ac0a7-07a9-4ab6-9041-ebddd476a214");
        assertThat(r.name()).isEqualTo("Nome");
        assertThat(r.cpf()).isEqualTo("123");
    }
}

