package com.hendrick.carshop.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(schema = "carshop_prdb", name = "client")
public class Client {

    private Long id;
    private String name;
    private String email;
    private String cpf;
    private String phone;
    private Users user;
    private LocalDateTime createdAt;
    private Users createdBy;
    private LocalDateTime updatedAt;
    private Users updatedBy;

    public Client(Long id, String name, String email, String cpf, String phone, Users user, LocalDateTime createdAt, Users createdBy, LocalDateTime updatedAt, Users updatedBy) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.phone = phone;
        this.user = user;
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

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "cpf")
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "user_id")
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
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
