package br.com.fiap.poastech.soat.techchallenge.persistence;

import br.com.fiap.postech.soat.techchallenge.infraestructure.persistence.CustomerDocument;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

class CustomerDocumentTest {

    @Test
    void gettersAndSetters() {
        String id = "ba8ac0a7-07a9-4ab6-9041-ebddd476a214";
        CustomerDocument e = new CustomerDocument();
        e.setId(id);
        e.setName("Nome");
        e.setCpf("123");
        e.setEmail("a@b.com");
        e.setPhone("119");

        assertThat(e.getId()).isEqualTo("ba8ac0a7-07a9-4ab6-9041-ebddd476a214");
        assertThat(e.getName()).isEqualTo("Nome");
        assertThat(e.getCpf()).isEqualTo("123");
        assertThat(e.getEmail()).isEqualTo("a@b.com");
        assertThat(e.getPhone()).isEqualTo("119");

        CustomerDocument e2 = new CustomerDocument(id, "Nome2", "456", "b@c.com", "122");
        assertThat(e2.getName()).isEqualTo("Nome2");
    }
}

