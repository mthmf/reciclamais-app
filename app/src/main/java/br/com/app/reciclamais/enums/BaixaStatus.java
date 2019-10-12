package br.com.app.reciclamais.enums;

public enum PerfilEnum {

    COMUM(1, "Usuario comum"),
    COLETADOR(2, "Usuario coletador");

    PerfilEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    private Integer codigo;
    private String descricao;

    public Integer getCodigo() {
        return codigo;
    }
}
