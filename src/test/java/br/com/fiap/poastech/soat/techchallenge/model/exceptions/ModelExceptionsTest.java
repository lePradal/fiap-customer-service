package br.com.fiap.poastech.soat.techchallenge.model.exceptions;

import br.com.fiap.postech.soat.techchallenge.model.exceptions.InvalidEmailException;
import br.com.fiap.postech.soat.techchallenge.model.exceptions.InvalidNameException;
import br.com.fiap.postech.soat.techchallenge.model.exceptions.InvalidPhoneException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ModelExceptionsTest {

    @Test
    void invalidName() {
        assertThatThrownBy(() -> { throw new InvalidNameException("nome"); }).isInstanceOf(InvalidNameException.class);
    }

    @Test
    void invalidEmail() {
        assertThatThrownBy(() -> { throw new InvalidEmailException("email"); }).isInstanceOf(InvalidEmailException.class);
    }

    @Test
    void invalidPhone() {
        assertThatThrownBy(() -> { throw new InvalidPhoneException("phone"); }).isInstanceOf(InvalidPhoneException.class);
    }
}

