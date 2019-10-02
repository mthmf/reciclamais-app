package br.com.app.reciclamais.model;

import java.math.BigDecimal;

public class Lixeira {

    private Integer codigo;
    private String nomeFicticio;
    private String endereco;
    private String pontoReferencia;
    private BigDecimal capacidadeTotal;
    private BigDecimal capacidadeAtual;
    private String latitude;
    private String longitude;
    private Integer rota;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNomeFicticio() {
        return nomeFicticio;
    }

    public void setNomeFicticio(String nomeFicticio) {
        this.nomeFicticio = nomeFicticio;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getPontoReferencia() {
        return pontoReferencia;
    }

    public void setPontoReferencia(String pontoReferencia) {
        this.pontoReferencia = pontoReferencia;
    }

    public BigDecimal getCapacidadeTotal() {
        return capacidadeTotal;
    }

    public void setCapacidadeTotal(BigDecimal capacidadeTotal) {
        this.capacidadeTotal = capacidadeTotal;
    }

    public BigDecimal getCapacidadeAtual() {
        return capacidadeAtual;
    }

    public void setCapacidadeAtual(BigDecimal capacidadeAtual) {
        this.capacidadeAtual = capacidadeAtual;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Integer getRota() {
        return rota;
    }

    public void setRota(Integer rota) {
        this.rota = rota;
    }
}
