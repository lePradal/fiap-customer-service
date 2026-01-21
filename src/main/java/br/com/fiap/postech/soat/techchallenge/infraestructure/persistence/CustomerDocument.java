package br.com.fiap.postech.soat.techchallenge.infraestructure.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDocument {
    @Id
    private String id;
    private String name;
    private String cpf;
    private String email;
    private String phone;
}
