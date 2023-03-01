package com.api.inventoryall.models;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_ITEM_INVENTORY")
public class InventoryModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true, length = 10)
    private String nomeEquioamento;
    @Column(nullable = false, unique = true, length = 255)
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
    @Column(nullable = true, length = 20)
    private String anydesk;
    @Column(nullable = false, length = 70)
    private String email;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomeEquioamento() {
        return nomeEquioamento;
    }

    public void setNomeEquioamento(String nomeEquioamento) {
        this.nomeEquioamento = nomeEquioamento;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setRegstrationDAte(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getProcessador() {
        return processador;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }

    public String getMemoria() {
        return memoria;
    }

    public void setMemoria(String memoria) {
        this.memoria = memoria;
    }

    public String getArmazenamento() {
        return armazenamento;
    }

    public void setArmazenamento(String armazenamento) {
        this.armazenamento = armazenamento;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public String getReclamacaoCliente() {
        return reclamacaoCliente;
    }

    public void setReclamacaoCliente(String reclamacaoCliente) {
        this.reclamacaoCliente = reclamacaoCliente;
    }

    public String getAtivarChamado() {
        return ativarChamado;
    }

    public void setAtivarChamado(String ativarChamado) {
        this.ativarChamado = ativarChamado;
    }

    public String getAnydesk() {
        return anydesk;
    }

    public void setAnydesk(String anydesk) {
        this.anydesk = anydesk;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}