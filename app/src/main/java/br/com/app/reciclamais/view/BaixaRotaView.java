package br.com.app.reciclamais.view;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.app.reciclamais.R;
import br.com.app.reciclamais.ReciclaApplication;
import br.com.app.reciclamais.adapter.LixeiraAdapter;
import br.com.app.reciclamais.adapter.RotaAdapter;
import br.com.app.reciclamais.model.BaixaCarrinho;
import br.com.app.reciclamais.model.Rota;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;


public class BaixaRotaView extends Activity {

    @BindView(R.id.recycler_rotas)
    public RecyclerView recyclerRotas;

    private RotaAdapter adapter;

    private List<Rota> rotas = new ArrayList<Rota>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baixa_diaria_rota);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startElements();
    }

    public void startElements(){

        Call<Rota> rotaCall =  ReciclaApplication.getInstance().getAPI().buscaRotas("{}");

        adapter = new RotaAdapter(rotas, BaixaRotaView.this);
        recyclerRotas.setAdapter(adapter);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(BaixaRotaView.this, RecyclerView.VERTICAL, false);
        recyclerRotas.setLayoutManager(layout);
    }
}
