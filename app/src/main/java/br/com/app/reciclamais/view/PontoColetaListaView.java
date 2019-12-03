package br.com.app.reciclamais.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

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

public class PontoColetaListaView extends Activity  {

    @BindView(R.id.recycler_pontos)
    public RecyclerView recyclerPontos;

    @BindView(R.id.btn_select)
    public Button btnSalvarRota;

    private List<Lixeira> lixeiras;
    private LixeiraAdapter adapter;
    private Rota rota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ponto_coleta);
        ButterKnife.bind(this);
        if(getIntent().getExtras() != null ){
            rota = (Rota) getIntent().getExtras().getSerializable("rota");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        startElements();
    }

    public void startElements(){

        if(rota == null){
            btnSalvarRota.setVisibility(View.GONE);
        }

        btnSalvarRota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapter.getLixeiraSelecionada() == null){
                    new AlertDialog.Builder(PontoColetaListaView.this)
                            .setTitle("Selecione uma lixeira para associar a rota")
                            .setMessage("Pelo menos uma lixeira deve ser associada a rota cadastrada")
                            .setPositiveButton("OK", null)
                            .show();
                }

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
                            new AlertDialog.Builder(PontoColetaListaView.this)
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
                adapter = new LixeiraAdapter(lixeiras, PontoColetaListaView.this);
                recyclerPontos.setAdapter(adapter);
                RecyclerView.LayoutManager layout = new LinearLayoutManager(PontoColetaListaView.this, RecyclerView.VERTICAL, false);
                recyclerPontos.setLayoutManager(layout);
            }

            @Override
            public void onFailure(Call<List<Lixeira>> call, Throwable t) {
                Log.e("Não foi possível buscar os produtos do carrinho", "Erro ao buscar pontos de coleta"+ t.getMessage());
            }
        });

    }

}
