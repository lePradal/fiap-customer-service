package br.com.fiap.poastech.soat.techchallenge.persistence;

import br.com.fiap.postech.soat.techchallenge.persistence.CustomerEntity;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerEntityTest {

    @Test
    void gettersAndSetters() {
        UUID id = UUID.randomUUID();
        CustomerEntity e = new CustomerEntity();
        e.setId(id);
        e.setName("Nome");
        e.setCpf("123");
        e.setEmail("a@b.com");
        e.setPhone("119");

        assertThat(e.getId()).isEqualTo(id);
        assertThat(e.getName()).isEqualTo("Nome");
        assertThat(e.getCpf()).isEqualTo("123");
        assertThat(e.getEmail()).isEqualTo("a@b.com");
        assertThat(e.getPhone()).isEqualTo("119");

        CustomerEntity e2 = new CustomerEntity(id, "Nome2", "456", "b@c.com", "122");
        assertThat(e2.getName()).isEqualTo("Nome2");
    }
}

