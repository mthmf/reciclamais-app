package br.com.app.reciclamais.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import br.com.app.reciclamais.MainActivity;
import br.com.app.reciclamais.R;
import br.com.app.reciclamais.ReciclaApplication;
import br.com.app.reciclamais.commons.Session;
import br.com.app.reciclamais.model.Lixeira;
import br.com.app.reciclamais.model.Produto;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalhesProdutoView extends Activity {


    @BindView(R.id.textView)
    public TextView textView;

    @BindView(R.id.textView2)
    public TextView textView2;

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
    protected void onResume() {
        super.onResume();
        startElements();
    }

    public void startElements(){
        textView.setText(produto.getNomeFabricante());
        textView2.setText(produto.getNome() + produto.getIdentificador());
        buttonSalvarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Integer> callProduto = ReciclaApplication.getInstance().getAPI().cadastraProduto(produto, Session.getInstance().getUsuario());
                callProduto.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if(response.code() != 201){
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
        });
    }
}
