package com.hendrick.carshop.dto;

import com.hendrick.carshop.enums.ShoppingCartStatus;
import com.hendrick.carshop.model.ShoppingCart;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ShoppingCartDTO {

    private Long id;
    private Long clientId;
    private ShoppingCartStatus status;
    private List<ShoppingCartItemDTO> items;
    private int totalItems;
    private BigDecimal totalValue;

    public ShoppingCartDTO(){}
    public ShoppingCartDTO(Long id, Long clientId, ShoppingCartStatus status, List<ShoppingCartItemDTO> items, int totalItems, BigDecimal totalValue) {
        this.id = id;
        this.clientId = clientId;
        this.status = status;
        this.items = items;
        this.totalItems = totalItems;
        this.totalValue = totalValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public ShoppingCartStatus getStatus() {
        return status;
    }

    public void setStatus(ShoppingCartStatus status) {
        this.status = status;
    }

    public List<ShoppingCartItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ShoppingCartItemDTO> items) {
        this.items = items;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }
}
