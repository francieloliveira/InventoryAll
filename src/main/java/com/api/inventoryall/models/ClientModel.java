package com.api.inventoryall.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.util.UUID;

@Entity
@Table(name = "TB_CLIENT")
public class ClientModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID client_id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "ADDRESS", nullable = true)
    private String address;
    @Column(name = "CITY", nullable = true)
    private String city;
    @Column(name = "STATE", nullable = true)
    private String state;
    @Column(name = "COUNTRY", nullable = true)
    private String country;
    @Column(name = "ZIP", nullable = true)
    private String zip;
    @Column(name = "STATUS", nullable = true)
    private String status;
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    public UUID getClient_id() {
        return client_id;
    }

    public void setClient_id(UUID client_id) {
        this.client_id = client_id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}