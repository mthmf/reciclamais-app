package br.com.app.reciclamais.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
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

public class BaitaRotaView extends Activity {

    @BindView(R.id.recycler_lixeiras_rota_consulta)
    public RecyclerView recyclerLixeira;

    @BindView(R.id.btn_salvar_rota)
    public Button buttonSalvarRota;


    private LixeiraAdapter adapter;

    private Rota rota;

    private List<Lixeira> lixeiras = new ArrayList<Lixeira>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baixa_rota_cadastro);
        ButterKnife.bind(this);

        rota = (Rota) getIntent().getExtras().getSerializable("rota");
    }

    @Override
    protected void onResume() {
        super.onResume();
        startElements();
    }

    public void startElements() {
        Call<List<Lixeira>> lixeirasCall = ReciclaApplication.getInstance().getAPI().buscaLixeiraDaRota(rota.getCodigo());
        lixeirasCall.enqueue(new Callback<List<Lixeira>>() {
            @Override
            public void onResponse(Call<List<Lixeira>> call, Response<List<Lixeira>> response) {
                adapter = new LixeiraAdapter(lixeiras, BaitaRotaView.this);
                recyclerLixeira.setAdapter(adapter);
                RecyclerView.LayoutManager layout = new LinearLayoutManager(BaitaRotaView.this, RecyclerView.VERTICAL, false);
                recyclerLixeira.setLayoutManager(layout);
            }

            @Override
            public void onFailure(Call<List<Lixeira>> call, Throwable t) {
                Log.e("Não foi possível buscar as rotas do carrinho", "Erro ao buscar produtos do carrinho"+ t.getMessage());

            }
        });


        buttonSalvarRota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}
