package br.com.app.reciclamais.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;

import br.com.app.reciclamais.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LixeiraView extends Activity {

    @BindView(R.id.edit_nome_fic)
    public EditText editNomeFic;

    @BindView(R.id.edit_endereco)
    public EditText editEndereco;

    @BindView(R.id.edit_ponto_ref)
    public EditText editPontoRef;

    @BindView(R.id.edit_cap_total)
    public EditText editCapTotal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_lixeira);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startElements();
    }

    public void startElements() {

    }

}
