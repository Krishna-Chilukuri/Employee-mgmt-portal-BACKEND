package com.employee.portal.model;

import jakarta.persistence.Entity;
import org.springframework.stereotype.Component;
import com.employee.portal.model.Login;

@Component
public class viewableUser {
    private String username;
    private String privilege;

    public viewableUser(Login log) {
        this.username = log.getUsername();
        this.privilege = log.getPrivilege();
    }

    @Override
    public String toString() {
        return "viewableUser{" +
                "username='" + username + '\'' +
                ", privilege='" + privilege + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }
}
