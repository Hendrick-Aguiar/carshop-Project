package com.hendrick.carshop.dto;

import com.hendrick.carshop.enums.Role;

public class UserDTO {

    private Long id;
    private String login;
    private Role role;
    private Boolean active;

    public UserDTO(Long id, Boolean active) {
    }

    public UserDTO(Long id, String login, Role role, Boolean active) {
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
}
