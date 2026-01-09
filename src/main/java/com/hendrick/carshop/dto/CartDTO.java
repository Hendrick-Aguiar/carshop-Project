package com.hendrick.carshop.dto;

import com.hendrick.carshop.model.ShoppingCartItem;

import java.util.List;

public class CartDTO {

    private Long userId;
    private Long vehicleId;
    private List<ShoppingCartItem> itemId;


    public CartDTO(){}


    public CartDTO(Long userId, Long vehicleId, List<ShoppingCartItem> items) {
        this.userId = userId;
        this.itemId = items;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<ShoppingCartItem> getItemId() {
        return itemId;
    }

    public void setItemId(List<ShoppingCartItem> itemId) {
        this.itemId = itemId;
    }
}
