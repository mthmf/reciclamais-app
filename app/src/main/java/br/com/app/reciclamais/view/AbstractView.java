package br.com.app.reciclamais.view;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import br.com.app.reciclamais.ReciclaApplication;
import br.com.app.reciclamais.commons.API;
import br.com.app.reciclamais.commons.Session;
import br.com.app.reciclamais.util.DataProvider;
import butterknife.ButterKnife;

public abstract class AbstractView extends Activity {

    protected DataProvider dataProvider = new DataProvider();
    protected API api = ReciclaApplication.getInstance().getAPI();
    protected boolean trial = Session.getInstance().getTrialVersion();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startElements();
    }

    abstract void startElements();

    void startListenerHints(){

    }
}
