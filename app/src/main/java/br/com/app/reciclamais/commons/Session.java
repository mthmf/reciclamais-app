package br.com.app.reciclamais.commons;

import br.com.app.reciclamais.model.Usuario;

public class Session {

    private static final Session SESSION = new Session();

    public Usuario usuario;

    private Session() {}

    public static Session getInstance() {
        return SESSION;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
