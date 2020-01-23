package br.com.app.reciclamais.view;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import br.com.app.reciclamais.util.DataProvider;
import butterknife.ButterKnife;

public abstract class AbstractView extends Activity {

    protected DataProvider dataProvider = new DataProvider();

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
