package com.hendrick.carshop.dto;

import java.time.LocalDateTime;

public class ShoppingCartItemDTO {


    private Long id;
    private Long shoppingCart;
    private Long vehicle;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;


    public ShoppingCartItemDTO(){}

    public ShoppingCartItemDTO(Long id, Long shoppingCart, Long vehicle, LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, String updatedBy) {
        this.id = id;
        this.shoppingCart = shoppingCart;
        this.vehicle = vehicle;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(Long shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Long getVehicle() {
        return vehicle;
    }

    public void setVehicle(Long vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
