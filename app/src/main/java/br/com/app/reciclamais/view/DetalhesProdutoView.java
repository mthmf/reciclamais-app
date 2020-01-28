package br.com.app.reciclamais.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import br.com.app.reciclamais.R;
import br.com.app.reciclamais.ReciclaApplication;
import br.com.app.reciclamais.commons.Session;
import br.com.app.reciclamais.dto.ProdutoDTO;
import br.com.app.reciclamais.model.Produto;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalhesProdutoView extends AbstractView{

    @BindView(R.id.nome_detalhes)
    public TextView nomeDetalhes;

    @BindView(R.id.fabr_detalhes)
    public TextView fabrDetalhes;

    @BindView(R.id.peso_detalhes)
    public TextView pesoDetalhes;

    @BindView(R.id.btn_salvar_produto)
    public Button buttonSalvarProduto;

    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_produto);
        ButterKnife.bind(this);
        produto = (Produto) getIntent().getExtras().getSerializable("produto");
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DetalhesProdutoView.this, MenuView.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startElements();
    }

    public void startElements(){
        fabrDetalhes.setText(produto.getNomeFabricante());
        nomeDetalhes.setText(produto.getNome());
        pesoDetalhes.setText(String.valueOf(produto.getPeso()));
        buttonSalvarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProdutoDTO dto = new ProdutoDTO(produto.getCodigo(), Session.getInstance().getUsuario().getCodigo());
                if(trial){
                    dataProvider.adicionaProduto(produto);
                } else {
                    Call<Integer> callProduto = api.cadastraProduto(dto);
                    callProduto.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if (response.code() != 201) {
                            System.out.println("Codigo: " + response.code());
                            new AlertDialog.Builder(DetalhesProdutoView.this)
                                    .setTitle("Não foi possível inclur o produto ao carrinho")
                                    .setMessage("Incluaum novo produto ou tente novamente mais tarde.")
                                    .setPositiveButton("OK", null)
                                    .show();
                        } else {
                            new AlertDialog.Builder(DetalhesProdutoView.this)
                                    .setTitle("Produto adicionado ao carrinho")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            Intent intent = new Intent(DetalhesProdutoView.this, MenuView.class);
                                            startActivity(intent);
                                        }
                                    })
                                    .show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        new AlertDialog.Builder(DetalhesProdutoView.this)
                                .setTitle("Não foi possível inclur o produto ao carrinho")
                                .setMessage("Incluaum novo produto ou tente novamente mais tarde.")
                                .setPositiveButton("OK", null)
                                .show();
                    }
                });
                }
            }
        });
    }
}
