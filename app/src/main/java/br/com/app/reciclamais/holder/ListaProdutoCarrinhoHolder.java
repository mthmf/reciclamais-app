package br.com.app.reciclamais.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.com.app.reciclamais.R;


public class ListaProdutoCarrinhoHolder extends RecyclerView.ViewHolder {

    private TextView nome;
    private TextView fabricante;
    private TextView peso;


    public ListaProdutoCarrinhoHolder(@NonNull View view) {
        super(view);
        nome = (TextView) view.findViewById(R.id.row_text_nome_produto);
        fabricante = (TextView) view.findViewById(R.id.row_fabricante);
        peso = (TextView) view.findViewById(R.id.row_peso);
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
