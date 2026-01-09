package br.com.fiap.postech.soat.techchallenge.domain.models;

import br.com.fiap.postech.soat.techchallenge.domain.exceptions.InvalidEmailException;
import br.com.fiap.postech.soat.techchallenge.domain.exceptions.InvalidIdException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Order {

    private UUID id;
    private Customer customer;
    private OrderStatus status;
    private String observation;
    private Integer number;
    private List<OrderItem> items;
    private LocalDateTime creationDate;

    public Order(UUID id, Customer customer, OrderStatus status, String observation, Integer number, List<OrderItem> items, LocalDateTime creationDate) {
        this.setId(id);
        this.setCustomer(customer);
        this.setStatus(status);
        this.setObservation(observation);
        this.setNumber(number);
        this.setItems(items);
        this.setCreationDate(creationDate);
    }

    public Integer getNumber() {
        return number;
    }

    private void setNumber(Integer number) {
        this.number = number;
    }

    public String getObservation() {
        return observation;
    }

    private void setObservation(String observation) {
        this.observation = observation;
    }

    public UUID getId() {
        return id;
    }

    private void setId(UUID id) {
        if (id == null) {
            throw new InvalidIdException("Order ID cannot be null.");
        }
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    private void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void update(Customer customer, OrderStatus orderStatus) {
        this.setCustomer(customer);
        this.setStatus(orderStatus);
    }

    public List<OrderItem> getItems() {
        return items;
    }

    private void setItems(List<OrderItem> items) {
//        if (items == null || items.isEmpty()) {
//            throw new ListEmptyException("Order items cannot be empty.");
//        }
        this.items = items;
    }

    public BigDecimal getTotalAmount() {
        return items.stream()
                .map(item -> item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}