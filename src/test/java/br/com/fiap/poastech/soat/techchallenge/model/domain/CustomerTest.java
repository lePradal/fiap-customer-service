package br.com.fiap.poastech.soat.techchallenge.model.domain;

import br.com.fiap.postech.soat.techchallenge.model.domain.Customer;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerTest {

    @Test
    void gettersAndSetters() {
        UUID id = UUID.randomUUID();
        Customer c = new Customer();
        c.setId(id);
        c.setName("Nome");
        c.setCpf("123");
        c.setEmail("a@b.com");
        c.setPhone("119");

        assertThat(c.getId()).isEqualTo(id);
        assertThat(c.getName()).isEqualTo("Nome");
        assertThat(c.getCpf()).isEqualTo("123");
        assertThat(c.getEmail()).isEqualTo("a@b.com");
        assertThat(c.getPhone()).isEqualTo("119");

        Customer c2 = new Customer(id, "N", "1", "e@e","p");
        assertThat(c2.getName()).isEqualTo("N");
    }
}

