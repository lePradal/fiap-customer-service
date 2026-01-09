package br.com.fiap.poastech.soat.techchallenge.model.dto.request;

import br.com.fiap.postech.soat.techchallenge.model.dto.request.CreateCustomerRequest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CreateCustomerRequestTest {

    @Test
    void recordAccessors() {
        CreateCustomerRequest req = new CreateCustomerRequest("Nome","123","a@b.com","119");
        assertThat(req.name()).isEqualTo("Nome");
        assertThat(req.cpf()).isEqualTo("123");
        assertThat(req.email()).isEqualTo("a@b.com");
        assertThat(req.phone()).isEqualTo("119");
    }
}

