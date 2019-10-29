package br.com.app.reciclamais.holder;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.com.app.reciclamais.R;
import br.com.app.reciclamais.commons.OnItemClickListener;
import br.com.app.reciclamais.model.Produto;
import butterknife.BindView;
import butterknife.ButterKnife;


public class ListaProdutoCarrinhoHolder extends RecyclerView.ViewHolder {

    private Integer codigo;
    @BindView(R.id.row_text_nome_produto)
    public TextView nome;
    @BindView(R.id.row_fabricante)
    public TextView fabricante;
    @BindView(R.id.row_peso)
    public TextView peso;

    public boolean selected;


    public ListaProdutoCarrinhoHolder(@NonNull View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void bind(final Produto produto) {
        codigo = produto.getCodigo();
        nome.setText(produto.getNome());
        fabricante.setText(produto.getNomeFabricante());
        peso.setText(String.valueOf(produto.getPeso()));
        selected = false;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected = !selected;
                /*if(selected){
                    itemView.setBackgroundColor(Color.BLUE);
                } else {
                    itemView.setBackgroundColor(Color.WHITE);
                }*/
            }
        });
    }


    public TextView getNome() {
        return nome;
    }

    public TextView getFabricante() {
        return fabricante;
    }

    public TextView getPeso() {
        return peso;
    }
}
