package br.com.app.reciclamais.enums;

public enum BaixaStatus {

    PENDENTE("P", "Pendente"),
    BAIXADO("B", "Baixado");

    BaixaStatus(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    private String codigo;
    private String descricao;

    public String getCodigo() {
        return codigo;
    }
}
