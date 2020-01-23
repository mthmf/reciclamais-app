package br.com.app.reciclamais.model;


public class Usuario {

    private Integer codigo;
    private String cpf;
    private String nome;
    private String email;
    private String senha;
    private Integer perfil;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo){ this.codigo = codigo;}

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getPerfil() {
        return perfil;
    }

    public void setPerfil(Integer perfil) {
        this.perfil = perfil;
    }
}