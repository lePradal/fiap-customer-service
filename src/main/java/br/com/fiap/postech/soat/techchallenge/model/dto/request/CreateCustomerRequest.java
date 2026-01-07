package br.com.fiap.postech.soat.techchallenge.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

public record CreateCustomerRequest(
        @NotBlank(message = "{customer.name.notblank}")
        @Schema(example = "Maria Josefina da Silva", description = "Nome completo do cliente")
        String name,

        @NotBlank(message = "{customer.cpf.notblank}")
        @CPF(message = "{customer.cpf.invalid}")
        @Schema(example = "11122233344", description = "CPF do cliente (apenas numeros)")
        String cpf,

        @NotBlank(message = "{customer.email.notblank}")
        @Email(message = "{customer.email.invalid}")
        @Schema(example = "cliente.fidelidade@gmail.com", description = "E-mail válido do cliente")
        String email,

        @NotBlank(message = "{customer.phone.notblank}")
        @Digits(integer = 11, fraction = 0, message = "{customer.phone.invalid}")
        @Pattern(regexp = "^\\d+$", message = "{customer.phone.invalid}")
        @Schema(example = "11988887777", description = "DDI + DDD + Telefone completo (apenas numeros)")
        String phone
) {
}
