package com.hendrick.carshop.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(schema = "carshop_prdb", name = "shopping_cart")
public class ShoppingCart {

    private Long id;
    private Client client;
    private LocalDateTime createdAt;
    private User createdBy;
    private LocalDateTime updatedAt;
    private User updatedBy;

    public ShoppingCart(Long id, Client client, LocalDateTime createdAt, User createdBy, LocalDateTime updatedAt, User updatedBy) {
        this.id = id;
        this.client = client;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "client_id")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Column(name = "created_at")
    public LocalDateTime getCreatedAt() {

        return createdAt;

    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @ManyToOne
    @JoinColumn(name = "created_by")
    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "updated_at")
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @ManyToOne
    @JoinColumn(name = "updated_by")
    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {

        this.updatedBy = updatedBy;
    }

}
