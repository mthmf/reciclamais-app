package br.com.app.reciclamais.model;

public class BaixaRota {

    private Integer codigo;
    private Integer codigoRota;
    private String dataBaixa;
    private String dataSaida;
    private String dataChegada;
    private String observacao;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigoRota() {
        return codigoRota;
    }

    public void setCodigoRota(Integer codigoRota) {
        this.codigoRota = codigoRota;
    }

    public String getDataBaixa() {
        return dataBaixa;
    }

    public void setDataBaixa(String dataBaixa) {
        this.dataBaixa = dataBaixa;
    }

    public String getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(String dataChegada) {
        this.dataChegada = dataChegada;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
