package br.com.fiap.postech.soat.techchallenge.domain.models;

import br.com.fiap.postech.soat.techchallenge.domain.exceptions.InvalidStatusException;

public enum OrderStatus {
    AWAITING_PAYMENT,
    RECEIVED,
    IN_PREPARATION,
    READY,
    FINALIZED;

    public OrderStatus next() {
        return switch (this) {
            case AWAITING_PAYMENT -> RECEIVED;
            case RECEIVED -> IN_PREPARATION;
            case IN_PREPARATION -> READY;
            case READY -> FINALIZED;
            case FINALIZED -> throw new InvalidStatusException("The status FINALIZED does not have a next status.");
        };
    }
}
