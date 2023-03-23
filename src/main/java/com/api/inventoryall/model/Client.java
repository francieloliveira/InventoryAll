package com.api.inventoryall.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_CLIENT")
@Data
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Email
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = true)
    private String phone;
    @Column(nullable = true)
    private String address;
    @Column(nullable = true)
    private String city;
    @Column(nullable = true)
    private String state;
    @Column(nullable = true)
    private String country;
    @Column(nullable = true)
    private String zip;
    @Column(nullable = true)
    private String status;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private LocalDateTime dataCadastro;
    @Column(nullable = true, length = 20)
    private String anydesk;

}