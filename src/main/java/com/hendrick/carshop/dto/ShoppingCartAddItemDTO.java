package com.hendrick.carshop.dto;

public class ShoppingCartAddItemDTO{

    private Long userId;
    private Long vehicleId;


    public ShoppingCartAddItemDTO(){}


    public ShoppingCartAddItemDTO(Long userId, Long vehicleId) {
        this.userId = userId;
        this.vehicleId = vehicleId;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }
}
