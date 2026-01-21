package br.com.fiap.postech.soat.techchallenge.infraestructure.persistence;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDocument {
    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    @CPF
    private String cpf;

    @Email
    private String email;

    private String phone;
}
