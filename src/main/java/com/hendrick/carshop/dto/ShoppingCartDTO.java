package com.hendrick.carshop.dto;

import com.hendrick.carshop.enums.ShoppingCartStatus;

import java.time.LocalDateTime;

public class ShoppingCartDTO {

    private Long id;
    private Long client;
    private String clientName;
    private ShoppingCartStatus status;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private Long userId;
    private Long items;

    public ShoppingCartDTO (){}

    public ShoppingCartDTO(Long id, Long client, String clientName, ShoppingCartStatus status, LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, String updatedBy, Long userId, Long items) {
        this.id = id;
        this.client = client;
        this.clientName = clientName;
        this.status = status;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.userId = userId;
        this.items = items;
    }





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClient() {
        return client;
    }

    public void setClient(Long client) {
        this.client = client;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public ShoppingCartStatus getStatus() {
        return status;
    }

    public void setStatus(ShoppingCartStatus status) {
        this.status = status;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getItems() {
        return items;
    }

    public void setItems(Long items) {
        this.items = items;
    }
}
