package com.hendrick.carshop.model;

import com.hendrick.carshop.enums.Role;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(schema = "carshop_prdb", name = "users")
public class Users {

    private Long id;
    private String login;
    private String passwordHash;
    private Role role;
    private Boolean active;
    private LocalDateTime createdAt;
    private Users createdBy;
    private LocalDateTime updatedAt;
    private Users updatedBy;


    public Users(Long id, String login, String passwordHash, Role role, Boolean active, LocalDateTime createdAt, Users createdBy, LocalDateTime updatedAt, Users updatedBy) {
        this.id = id;
        this.login = login;
        this.passwordHash = passwordHash;
        this.role = role;
        this.active = active;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
    }

    private Users(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "password_hash")
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Column(name = "active")
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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
