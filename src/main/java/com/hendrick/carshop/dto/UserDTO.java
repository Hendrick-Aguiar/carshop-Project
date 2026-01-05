package com.hendrick.carshop.dto;

import com.hendrick.carshop.enums.Role;

public class UserDTO {

    private Long id;
    private String login;
    private String role;
    private int active;


    public UserDTO(Long id, String login, String role, int active) {
        this.id = id;
        this.login = login;
        this.role = role;
        this.active = active;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
