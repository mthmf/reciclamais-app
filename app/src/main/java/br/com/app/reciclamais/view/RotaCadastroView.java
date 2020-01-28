package br.com.app.reciclamais.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.app.reciclamais.R;
import br.com.app.reciclamais.ReciclaApplication;
import br.com.app.reciclamais.adapter.LixeiraAdapter;
import br.com.app.reciclamais.model.Lixeira;
import br.com.app.reciclamais.model.Rota;
import br.com.app.reciclamais.util.Util;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.util.List;

public class RotaCadastroView extends AbstractView {

    @BindView(R.id.btn_salvar_rota)
    public Button btnSalvarRota;

    @BindView(R.id.rota_data_inicio)
    public TextView rotaDataInicio;

    @BindView(R.id.rota_data_fim)
    public TextView rotaDataFim;

    @BindView(R.id.edit_descricao)
    public EditText editDescricao;

    @BindView(R.id.edit_data_inicio)
    public EditText editDataInicio;

    @BindView(R.id.edit_data_fim)
    public EditText editDataFim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_rota);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startElements();
    }

    public void startElements() {

        btnSalvarRota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Rota rota = new Rota();
                rota.setDescricao(editDescricao.getText().toString());
                rota.setDataInicio(Util.getDateTime(LocalDateTime.now()));
                rota.setDataFinal(Util.getDateTime(LocalDateTime.now()));

                Intent intent = new Intent(RotaCadastroView.this, PontoColetaListaView.class);
                intent.putExtra("rota", rota);
                startActivity(intent);

            }
        });
    }

}
