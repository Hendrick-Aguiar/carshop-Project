package com.hendrick.carshop.dto;

public class OrderDTO {

    private Long id;
    private String client;
    private Long vehicleId;
    private String vehicleName;
    private String status;




    public OrderDTO() {
    }

    public OrderDTO(Long id, String client, Long vehicleId, String status, String vehicleName) {
        this.id = id;
        this.client = client;
        this.vehicleId = vehicleId;
        this.status = status;
        this.vehicleName = vehicleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }
}
