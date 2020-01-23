package br.com.app.reciclamais.commons;

import br.com.app.reciclamais.model.Usuario;

public class Session {

    private static final Session SESSION = new Session();

    private Usuario usuario;
    private boolean trialVersion;

    private Session() {}

    public static Session getInstance() {
        return SESSION;
    }

    public boolean getTrialVersion(){ return trialVersion;}

    public void setTrialVersion(boolean trialVersion){ this.trialVersion = trialVersion;}

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
