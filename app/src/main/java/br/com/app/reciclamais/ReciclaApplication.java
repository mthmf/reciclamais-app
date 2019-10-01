package br.com.app.reciclamais;

import android.app.Application;

import br.com.app.reciclamais.commons.API;
import br.com.app.reciclamais.commons.RetrofitApiBuilder;

public class ReciclaApplication extends Application {

    private static ReciclaApplication application;
    private API api;

    public ReciclaApplication() {
    }

    public static ReciclaApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public API getAPI() {
        if (api == null) {
            synchronized (ReciclaApplication.class) {
                if (api == null) {
                    api = new RetrofitApiBuilder().build();
                    //api = new MockApiBuilder().build();
                }
            }
        }
        return api;
    }
}
