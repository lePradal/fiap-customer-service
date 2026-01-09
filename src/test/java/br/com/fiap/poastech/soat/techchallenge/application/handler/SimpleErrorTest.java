package br.com.fiap.poastech.soat.techchallenge.application.handler;

import br.com.fiap.postech.soat.techchallenge.application.handler.SimpleError;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleErrorTest {

    @Test
    void gettersAndSettersWork() {
        SimpleError err = new SimpleError("mensagem");
        assertThat(err.getMessage()).isEqualTo("mensagem");

        err.setMessage("outra");
        assertThat(err.getMessage()).isEqualTo("outra");
    }
}

