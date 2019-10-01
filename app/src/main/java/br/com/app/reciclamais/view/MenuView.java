package br.com.app.reciclamais.view;

import android.app.Activity;
import android.os.Bundle;

import br.com.app.reciclamais.R;

public class MenuView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
