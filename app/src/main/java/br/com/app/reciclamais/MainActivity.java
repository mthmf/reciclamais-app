package br.com.app.reciclamais;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import br.com.app.reciclamais.view.LoginView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, LoginView.class);
        //Session.getInstance().setTrialVersion(true);
        startActivity(intent);

    }
}
