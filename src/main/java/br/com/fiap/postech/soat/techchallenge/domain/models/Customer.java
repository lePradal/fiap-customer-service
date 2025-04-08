package br.com.fiap.postech.soat.techchallenge.domain.models;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Customer {
    private UUID id;
    private String name;
    private String cpf;
    private String email;
    private String phone;
}

