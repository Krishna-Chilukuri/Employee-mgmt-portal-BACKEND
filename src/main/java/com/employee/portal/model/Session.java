package com.employee.portal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name = "sessions")
public class Session {
    @Id
    @Column(name = "sessionId")
    private String sessionId;

    @Column(name = "ipAddress")
    private String ipAddress;

    @Column(name = "username")
    private String username;

    @Column(name = "privilege")
    private String privilege;

    @Column(name = "expiry")
    private Timestamp expiry;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public Timestamp getExpiry() {
        return expiry;
    }

    public void setExpiry(Timestamp expiry) {
        this.expiry = expiry;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Session{" +
                "sessionId='" + sessionId + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", username='" + username + '\'' +
                ", privilege='" + privilege + '\'' +
                ", expiry=" + expiry +
                '}';
    }
}
