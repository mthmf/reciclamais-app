package br.com.app.reciclamais.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.app.reciclamais.R;
import br.com.app.reciclamais.ReciclaApplication;
import br.com.app.reciclamais.adapter.LixeiraAdapter;
import br.com.app.reciclamais.model.BaixaCarrinho;
import br.com.app.reciclamais.model.Lixeira;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RealizaBaixaView extends Activity {

    @BindView(R.id.text_criacao_id_baixa)
    public TextView textCriacaoId;

    @BindView(R.id.image_carrinho_baixa)
    public ImageView imageCarrinho;

    @BindView(R.id.text_peso_id_baixa)
    public TextView textPesoId;

    @BindView(R.id.text_criacao_baixa)
    public TextView textCriacao;

    @BindView(R.id.text_total_carrinho_baixa)
    public TextView  textTotalCarrinho;


    @BindView(R.id.lixeira_titulo)
    public TextView lixeiraTitulo;

    @BindView(R.id.recycler_lixeiras)
    public RecyclerView recyclerLixeira;

    @BindView(R.id.btn_confirmar_baixa)
    public Button btnConfirmarBaixa;

    private List<Lixeira> lixeiras = new ArrayList<>();
    private LixeiraAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_baixa);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startElements();
    }

    public void startElements(){
        btnConfirmarBaixa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapter.getLixeiraSelecionada() == null){
                    new AlertDialog.Builder(RealizaBaixaView.this)
                            .setTitle("Selecione uma lixeira para fazer a baixa")
                            .setMessage("Não foi possível fazer a baixa")
                            .setPositiveButton("OK", null)
                            .show();
                }
                System.out.println("Lixeira selecionado " + adapter.getLixeiraSelecionada().getEndereco());
                BaixaCarrinho baixaCarrinho = new BaixaCarrinho();
                baixaCarrinho.setCodigoCarrinho(9); //TODO
                baixaCarrinho.setCodigoLixeira(adapter.getLixeiraSelecionada().getCodigo());
                baixaCarrinho.setDataBaixa("2019-09-24 21:25:55");
                baixaCarrinho.setStatus("P");

                Call<Integer> callBaixa = ReciclaApplication.getInstance().getAPI().confirmaAgendamentoBaixa(baixaCarrinho);
                callBaixa.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        System.out.println(" Código " + response.code());
                        if(response.code()== 201){
                            new AlertDialog.Builder(RealizaBaixaView.this)
                                    .setTitle("Baixa agendada")
                                    .setMessage("Verifique a lista de agendamentos")
                                    .setPositiveButton("OK", null)
                                    .show();
                        } else {
                            new AlertDialog.Builder(RealizaBaixaView.this)
                                    .setTitle("Ocorreu um erro ao cadastrar o agendamento")
                                    .setMessage("Favor tente novamente mais tarde.")
                                    .setPositiveButton("OK", null)
                                    .show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Log.e("Erro ao cadastrar baixa", "Erro ao cadastrar baixa"+ t.getMessage());
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
                adapter = new LixeiraAdapter(lixeiras, RealizaBaixaView.this);
                recyclerLixeira.setAdapter(adapter);
                RecyclerView.LayoutManager layout = new LinearLayoutManager(RealizaBaixaView.this, RecyclerView.VERTICAL, false);
                recyclerLixeira.setLayoutManager(layout);
            }

            @Override
            public void onFailure(Call<List<Lixeira>> call, Throwable t) {
                Log.e("Não foi possível buscar os produtos do carrinho", "Erro ao buscar produtos do carrinho"+ t.getMessage());
            }
        });
    }
}
