package com.api.inventoryall.dtos;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class InventoryDto {
    @NotBlank
    private String nomeEquipamento;
    @NotBlank
    private String numeroSerie;
    private String processador;
    private String memoria;
    private String armazenamento;
    private String historico;
    private String tecnico;
    private String reclamacaoCliente;
    private String ativarChamado;
    private String anydesk;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String nomeCliente;
    @NotBlank
    private String qrcode;

    public String getQrcode() {
        return qrcode;
    }
    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }
    public String getNomeEquipamento() {
        return nomeEquipamento;
    }
    public void setNomeEquipamento(String nomeEquipamento) {
        this.nomeEquipamento = nomeEquipamento;
    }
    public String getNumeroSerie() {
        return numeroSerie;
    }
    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
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
    public String getNomeCliente() {
        return nomeCliente;
    }
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

}