package br.com.app.reciclamais.view;

import android.app.Activity;
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
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RotaView extends Activity  {

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

    @BindView(R.id.recycler_lixeiras_rota)
    public RecyclerView recyclerLixeirasRota;

    private List<Lixeira> lixeiras;

    private LixeiraAdapter adapter;

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

    public void startElements(){

        btnSalvarRota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapter.getLixeiraSelecionada() == null){
                    new AlertDialog.Builder(RotaView.this)
                            .setTitle("Selecione uma lixeira para associar a rota")
                            .setMessage("Pelo menos uma lixeira deve ser associada a rota cadastrada")
                            .setPositiveButton("OK", null)
                            .show();
                }
                Rota rota = new Rota();
                rota.setDescricao(editDescricao.getText().toString());
                rota.setDataInicio("2019-09-24 21:25:55");
                rota.setDataFinal("2019-09-24 21:25:55");

                Call<Integer> callRota = ReciclaApplication.getInstance().getAPI().cadastraRota(rota);
                callRota.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if(response.code()== 201){
                            // Altera lixeiras
                            final Lixeira lixeira = adapter.getLixeiraSelecionada();
                            lixeira.setRota(response.body());
                            Call<Lixeira> callLixeira = ReciclaApplication.getInstance().getAPI().alteraLixeira(lixeira);
                            callLixeira.enqueue(new Callback<Lixeira>() {
                                @Override
                                public void onResponse(Call<Lixeira> call, Response<Lixeira> response) {
                                    if(response.code()== 200){
                                        System.out.println("FOI " + lixeira.getRota());
                                    }
                                }

                                @Override
                                public void onFailure(Call<Lixeira> call, Throwable t) {
                                    Log.e("Não foi possível buscar os produtos do carrinho", "Erro ao buscar produtos do carrinho"+ t.getMessage());

                                }
                            });


                        } else {
                            new AlertDialog.Builder(RotaView.this)
                                    .setTitle("Ocorreu um erro ao cadastrar o agendamento")
                                    .setMessage("Favor tente novamente mais tarde.")
                                    .setPositiveButton("OK", null)
                                    .show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Log.e("Não foi possível buscar os produtos do carrinho", "Erro ao buscar produtos do carrinho"+ t.getMessage());

                    }
                });

            }
        });

        Call<List<Lixeira>> callLixeiras = ReciclaApplication.getInstance().getAPI().buscaLixeiras("{}");
        callLixeiras.enqueue(new Callback<List<Lixeira>>() {
            @Override
            public void onResponse(Call<List<Lixeira>> call, Response<List<Lixeira>> response) {
                lixeiras = response.body();

                // Seta as lixeiras disponíveis
                adapter = new LixeiraAdapter(lixeiras, RotaView.this);
                recyclerLixeirasRota.setAdapter(adapter);
                RecyclerView.LayoutManager layout = new LinearLayoutManager(RotaView.this, RecyclerView.VERTICAL, false);
                recyclerLixeirasRota.setLayoutManager(layout);
            }

            @Override
            public void onFailure(Call<List<Lixeira>> call, Throwable t) {
                Log.e("Não foi possível buscar os produtos do carrinho", "Erro ao buscar produtos do carrinho"+ t.getMessage());
            }
        });

    }

}
