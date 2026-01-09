package br.com.fiap.postech.soat.techchallenge.domain.models;

import br.com.fiap.postech.soat.techchallenge.domain.exceptions.InvalidStatusException;

public enum PaymentStatus {
    PENDING, PAID;

    public PaymentStatus next() {
        return switch (this) {
            case PENDING -> PAID;
            case PAID -> throw new InvalidStatusException("The status PAID does not have a next status.");
        };
    }

    public boolean verifyTransition(PaymentStatus nextStatus) {
        return this.next() == nextStatus;
    }

}
