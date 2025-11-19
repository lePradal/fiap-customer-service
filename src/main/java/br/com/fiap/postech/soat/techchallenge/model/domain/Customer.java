package br.com.fiap.postech.soat.techchallenge.model.domain;

import br.com.fiap.postech.soat.techchallenge.model.exceptions.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private UUID id;
    private String name;
    private String cpf;
    private String email;
    private String phone;

    public void setPhone(String phone) {
        if (!phone.matches("^\\d+$")) throw new InvalidPhoneException("Phone must be numeric.");
        this.phone = phone;
    }
}

