package com.hendrick.carshop.dto;

import com.hendrick.carshop.enums.Role;


public class UpdateResponseDTO {


    private Long userId;
    private Long clientId;
    private Role role;
    private Boolean active;
    private String name;
    private String email;
    private String cpf;
    private String phone;
    private String login;
    private String password;

     public UpdateResponseDTO(){}

    public UpdateResponseDTO(Long userId, Long clientId, Role role, Boolean active, String name, String email, String cpf, String phone, String login, String password) {
        this.userId = userId;
        this.clientId = clientId;
        this.role = role;
        this.active = active;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.phone = phone;
        this.login = login;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}