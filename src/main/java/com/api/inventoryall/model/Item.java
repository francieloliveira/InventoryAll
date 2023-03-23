package com.api.inventoryall.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_ITEM")
@Data
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 100)
    private String nomeEquipamento;
    @Column(nullable = false, length = 255)
    private String numeroSerie;
    @Column(nullable = false)
    private LocalDateTime dataCadastro;
    @Column(nullable = true, length = 40)
    private String processador;
    @Column(nullable = true, length = 20)
    private String memoria;
    @Column(nullable = true, length = 20)
    private String armazenamento;
    @Column(nullable = true, length = 255)
    private String historico;
    @Column(nullable = true, length = 50)
    private String tecnico;
    @Column(nullable = true, length = 255)
    private String reclamacaoCliente;
    @Column(nullable = true, length = 20)
    private String ativarChamado;
    @Column(nullable = false, unique = true, length = 255)
    private String qrcode;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Client cliente;
}