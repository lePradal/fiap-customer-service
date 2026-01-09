package br.com.fiap.postech.soat.techchallenge.domain.models;

import br.com.fiap.postech.soat.techchallenge.domain.exceptions.InvalidStatusException;

import java.math.BigDecimal;
import java.util.UUID;

public class Payment {

    private UUID id;
    private PaymentStatus status;
    private Order order;
    private String qrCode;
    private BigDecimal amount;

    public Payment(UUID id, PaymentStatus status, Order order, String qrCode) {
        this.setId(id);
        this.setStatus(status);
        this.setOrder(order);
        this.setAmount(order.getTotalAmount());
        this.setQrCode(qrCode);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        if (this.status != null && !this.status.verifyTransition(status)) {
            throw new InvalidStatusException("Invalid status transition from " + this.status + " to " + status);
        }

        this.status = status;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public void update(PaymentStatus status) {
        this.setStatus(status);
    }

    public void updateQr(String qr) {
        this.setQrCode(qr);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
