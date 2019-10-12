package br.com.app.reciclamais.dto;

public class ProdutoDTO {

    private Integer codigoProduto;
    private Integer codigoUsuario;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Integer codigoProduto, Integer codigoUsuario) {
        this.codigoProduto = codigoProduto;
        this.codigoUsuario = codigoUsuario;
    }

    public Integer getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(Integer codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public Integer getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Integer codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }
}
