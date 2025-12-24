package com.hendrick.carshop.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(schema = "carshop_prdb", name = "brand")
public class Brand {

    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private Users createdBy;
    private LocalDateTime updatedAt;
    private Users updatedBy;

    public Brand(Long id, String name, LocalDateTime createdAt, Users createdBy, LocalDateTime updatedAt, Users updatedBy) {
        this.id = id;
        this.name = name;
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

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public Users getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Users createdBy) {
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
    public Users getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Users updatedBy) {

        this.updatedBy = updatedBy;
    }
}
