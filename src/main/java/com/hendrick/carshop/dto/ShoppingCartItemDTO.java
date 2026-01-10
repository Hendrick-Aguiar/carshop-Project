package com.hendrick.carshop.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ShoppingCartItemDTO {


    private Long id;
    private Long vehicleId;
    private String vehicleName;
    private BigDecimal price;



    public ShoppingCartItemDTO(Long id, Long vehicleId, String vehicleName, BigDecimal price) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.vehicleName = vehicleName;
        this.price = price;
    }

    public ShoppingCartItemDTO() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
