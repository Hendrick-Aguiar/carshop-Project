package com.hendrick.carshop.model;

import com.hendrick.carshop.enums.ShoppingCartStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(schema = "carshop_prdb", name = "shopping_carts")
public class ShoppingCart {

    private Long id;
    private Client client;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ShoppingCartStatus status;
    private LocalDateTime createdAt;
    private User createdBy;
    private LocalDateTime updatedAt;
    private User updatedBy;
    private List<ShoppingCartItem> items;

    public ShoppingCart(Long id, Client client, ShoppingCartStatus status, LocalDateTime createdAt, User createdBy, LocalDateTime updatedAt, User updatedBy, List<ShoppingCartItem> items) {
        this.id = id;
        this.client = client;
        this.status = status;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.items = items;
    }

    public ShoppingCart() {
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

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    public ShoppingCartStatus getStatus() {

        return status;

    }

    public void setStatus(ShoppingCartStatus status) {

        this.status = status;

    }

    @Column(name = "client")
    public void getClient(ShoppingCartStatus status) {

        this.status = status;

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

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL)
    public List<ShoppingCartItem> getItems() {
        return this.items;
    }

    public void setShoppingCartItem(List<ShoppingCartItem> items) {
        this.items = items;
    }

    public void setItems(List<ShoppingCartItem> items) {
        this.items = items;
    }

    public void addItem(Vehicle vehicle, User user) {

        if (this.status != ShoppingCartStatus.ACTIVE) {

            throw new IllegalStateException("Cart is not avaliable.");

        }


        boolean alreadyExists = items.stream().anyMatch(item -> item.getVehicle().equals(vehicle));

        if (alreadyExists) {

            throw new IllegalArgumentException("Vehicle in the way.");

        }

        ShoppingCartItem item = new ShoppingCartItem(this, vehicle, user);

        items.add(item);

    }

}
