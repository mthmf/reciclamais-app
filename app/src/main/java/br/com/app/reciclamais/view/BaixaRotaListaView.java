package br.com.app.reciclamais.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.app.reciclamais.R;
import br.com.app.reciclamais.adapter.RotaAdapter;
import br.com.app.reciclamais.model.Rota;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BaixaRotaListaView extends AbstractView {

    @BindView(R.id.recycler_rotas)
    public RecyclerView recyclerRotas;

    @BindView(R.id.button_realiza_baixa)
    public Button buttonRealizaBaixa;

    private RotaAdapter adapter;

    private List<Rota> rotas = new ArrayList<Rota>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_baixa_rota);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startElements();
    }

    public void startElements(){
        buttonRealizaBaixa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaixaRotaListaView.this, BaitaRotaView.class);
                intent.putExtra("rota", adapter.getRotaSelecionada());
                startActivity(intent);
            }
        });

        if(trial){
            dataProvider.buscaRotas();
        } else {
            Call<List<Rota>> rotaCall = api.buscaRotasParaBaixa("{}");
            rotaCall.enqueue(new Callback<List<Rota>>() {
            @Override
            public void onResponse(Call<List<Rota>> call, Response<List<Rota>> response) {
                rotas = response.body();

                adapter = new RotaAdapter(rotas, BaixaRotaListaView.this);
                recyclerRotas.setAdapter(adapter);
                RecyclerView.LayoutManager layout = new LinearLayoutManager(BaixaRotaListaView.this, RecyclerView.VERTICAL, false);
                recyclerRotas.setLayoutManager(layout);
            }


            @Override
            public void onFailure(Call<List<Rota>> call, Throwable t) {
                Log.e("Não foi possível buscar as rotas do carrinho", "Erro ao buscar produtos do carrinho"+ t.getMessage());

            }
        });
        }
    }
}
