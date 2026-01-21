package br.com.fiap.postech.soat.techchallenge.infraestructure.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerDocument, UUID> {
    Optional<CustomerDocument> findByCpf(String cpf);
    void deleteByCpf(String cpf);
}
