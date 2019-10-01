package br.com.app.reciclamais.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.app.reciclamais.util.LocalDateTimeDeserializer;
import br.com.app.reciclamais.util.LocalDateTimeSerializer;

public class Carrinho {

    private Integer codigo;
    private Integer usuario;

    private String dataCriacao;

    private BigDecimal totalPesoReciclavel;
    private Boolean ativo;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public BigDecimal getTotalPesoReciclavel() {
        return totalPesoReciclavel;
    }

    public void setTotalPesoReciclavel(BigDecimal totalPesoReciclavel) {
        this.totalPesoReciclavel = totalPesoReciclavel;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
