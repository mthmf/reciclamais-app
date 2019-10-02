package br.com.app.reciclamais.model;

import java.time.LocalDateTime;

public class BaixaCarrinho {

    private Integer codigo;
    private Integer codigoLixeira;
    private Integer codigoCarrinho;
    private String dataAgendamento;
    private String dataBaixa;
    private String status;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigoLixeira() {
        return codigoLixeira;
    }

    public void setCodigoLixeira(Integer codigoLixeira) {
        this.codigoLixeira = codigoLixeira;
    }

    public Integer getCodigoCarrinho() {
        return codigoCarrinho;
    }

    public void setCodigoCarrinho(Integer codigoCarrinho) {
        this.codigoCarrinho = codigoCarrinho;
    }

    public String getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(String dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public String getDataBaixa() {
        return dataBaixa;
    }

    public void setDataBaixa(String dataBaixa) {
        this.dataBaixa = dataBaixa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
