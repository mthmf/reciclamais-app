package br.com.app.reciclamais.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.math.BigDecimal;

import br.com.app.reciclamais.MainActivity;
import br.com.app.reciclamais.R;
import br.com.app.reciclamais.ReciclaApplication;
import br.com.app.reciclamais.model.Lixeira;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LixeiraView extends Activity {

    @BindView(R.id.edit_nome_fic)
    public EditText editNomeFic;

    @BindView(R.id.edit_endereco)
    public EditText editEndereco;

    @BindView(R.id.edit_ponto_ref)
    public EditText editPontoRef;

    @BindView(R.id.edit_cap_total)
    public EditText editCapTotal;

    @BindView(R.id.btn_salvar_lixeira)
    public Button btnSalvar;

    private Lixeira lixeira;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_lixeira);
        ButterKnife.bind(this);
        lixeira = (Lixeira) getIntent().getExtras().getSerializable("lixeira");
    }

    @Override
    protected void onResume() {
        super.onResume();
        startElements();
    }

    public void startElements() {
        editEndereco.setText(lixeira.getEndereco());
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lixeira.setCapacidadeTotal(new BigDecimal(editCapTotal.getText().toString()));
                lixeira.setNomeFicticio(editNomeFic.getText().toString());
                lixeira.setPontoReferencia(editPontoRef.getText().toString());

                Call<Integer> call = ReciclaApplication.getInstance().getAPI().salvarLixeira(lixeira);
                call.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if(response.code() != 201){
                            new AlertDialog.Builder(LixeiraView.this)
                                    .setTitle("Lixeira cadastrada com sucesso")
                                    .setPositiveButton("OK", null)
                                    .show();
                        } else {
                            new AlertDialog.Builder(LixeiraView.this)
                                    .setTitle("Não foi possível cadastrar a lixeira")
                                    .setMessage("Ocorreu um problema ao realizar o cadastro")
                                    .setPositiveButton("OK", null)
                                    .show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Log.e("Usuário não cadastrado", "Erro ao cadastrar usuario"+ t.getMessage());
                    }
                });
            }
        });

    }

}
