package br.com.fiap.postech.soat.techchallenge.model.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @NotBlank
    private UUID id;

    @NotBlank(message = "{customer.name.notblank}")
    private String name;

    @NotBlank(message = "{customer.cpf.notblank}")
    @CPF(message = "{customer.cpf.invalid}")
    private String cpf;

    @NotBlank(message = "{customer.email.notblank}")
    @Email(message = "{customer.email.invalid}")
    private String email;

    @NotBlank(message = "{customer.phone.notblank}")
    @Pattern(regexp = "^\\d+$\n", message = "{customer.phone.invalid}")
    private String phone;
}

