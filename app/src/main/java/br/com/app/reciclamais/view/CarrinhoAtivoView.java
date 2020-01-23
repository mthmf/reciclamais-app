package br.com.app.reciclamais.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.app.reciclamais.R;
import br.com.app.reciclamais.ReciclaApplication;
import br.com.app.reciclamais.adapter.ProdutoCarrinhoAdapter;
import br.com.app.reciclamais.commons.Session;
import br.com.app.reciclamais.model.Carrinho;
import br.com.app.reciclamais.model.Produto;
import br.com.app.reciclamais.util.Util;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarrinhoAtivoView extends AbstractView implements View.OnClickListener {

    @BindView(R.id.text_criacao_id)
    public TextView textCriacaoId;

    @BindView(R.id.image_carrinho)
    public ImageView imageCarrinho;

    @BindView(R.id.text_peso_id)
    public TextView textPesoId;

    @BindView(R.id.text_criacao)
    public TextView textCriacao;

    @BindView(R.id.text_total_carrinho)
    public TextView  textTotalCarrinho;

    @BindView(R.id. recycler_produtos)
    public RecyclerView recyclerView;

    @BindView(R.id.text_produto_titulo)
    public TextView textProdutoTitulo;

    @BindView(R.id.btn_fecha_carrinho)
    public Button btnFechaCarrinho;

    private Carrinho carrinho;

    private List<Produto> produtos = new ArrayList<Produto>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho_ativo);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startElements();
    }

    public void startElements(){
        btnFechaCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BaixaCarrinhoView.class);
                intent.putExtra("carrinho", carrinho);
                startActivity(intent);
            }
        });

        if(Session.getInstance().getTrialVersion()) {
            dataProvider.
        } else {
            Call<Carrinho> call =  ReciclaApplication.getInstance().getAPI().buscaCarrinhoAtual(Session.getInstance().getUsuario());
            call.enqueue(new Callback<Carrinho>() {
                @Override
                public void onResponse(Call<Carrinho> call, Response<Carrinho> response) {
                    carrinho = response.body();

                    // Seta valores do carrinho
                    textTotalCarrinho.setText(String.valueOf(carrinho.getTotalPesoReciclavel()));
                    textCriacao.setText(Util.getDate(carrinho.getDataCriacao()));

                    Call<List<Produto>> callProdutos =  ReciclaApplication.getInstance().getAPI().buscaProdutosCarrinho(carrinho);
                    callProdutos.enqueue(callBackProdutos());
                }

                @Override
                public void onFailure(Call<Carrinho> call, Throwable t) {
                    Log.e("Não foi possível buscar o carrinho", "Erro ao buscar carrinho"+ t.getMessage());
                }
            });
        }
    }


    @Override
    public void onClick(View v) {

    }

    public Callback<List<Produto>> callBackProdutos(){
        return new Callback<List<Produto>>() {
            @Override
            public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response) {
                produtos = response.body();
                // Seta os produtos
                recyclerView.setAdapter(new ProdutoCarrinhoAdapter(produtos));
                RecyclerView.LayoutManager layout = new LinearLayoutManager(CarrinhoAtivoView.this, RecyclerView.VERTICAL, false);
                recyclerView.setLayoutManager(layout);
            }

            @Override
            public void onFailure(Call<List<Produto>> call, Throwable t) {
                Log.e("Não foi possível buscar os produtos do carrinho", "Erro ao buscar produtos do carrinho"+ t.getMessage());

            }
        };
    }



}
