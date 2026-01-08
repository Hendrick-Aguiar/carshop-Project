package com.hendrick.carshop.dto;

import com.hendrick.carshop.enums.VehicleStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VehicleDTO {

    private Long id;
    private String licencePlate;
    private String chassis;
    private String renavam;
    private int year;
    private Long modelId;
    private String model;
    private Long brandId;
    private String brand;
    private Long colorId;
    private String color;
    private BigDecimal price;
    private VehicleStatus status;
    private Long shoppingCartItem;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    public VehicleDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getChassis() {
        return chassis;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public String getRenavam() {
        return renavam;
    }

    public void setRenavam(String renavam) {
        this.renavam = renavam;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Long getColorId() {
        return colorId;
    }

    public void setColorId(Long colorId) {
        this.colorId = colorId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }

    public Long getShoppingCartItem() {
        return shoppingCartItem;
    }

    public void setShoppingCartItem(Long shoppingCartItem) {
        this.shoppingCartItem = shoppingCartItem;
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

    public VehicleDTO(Long id, String licencePlate, String chassis, String renavam, int year, Long modelId, String model, Long brandId, String brand, Long colorId, String color, BigDecimal price, VehicleStatus status, Long shoppingCartItem, LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, String updatedBy) {
        this.id = id;
        this.licencePlate = licencePlate;
        this.chassis = chassis;
        this.renavam = renavam;
        this.year = year;
        this.modelId = modelId;
        this.model = model;
        this.brandId = brandId;
        this.brand = brand;
        this.colorId = colorId;
        this.color = color;
        this.price = price;
        this.status = status;
        this.shoppingCartItem = shoppingCartItem;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
    }
}
