package br.com.app.reciclamais.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.app.reciclamais.R;
import br.com.app.reciclamais.holder.ListaProdutoCarrinhoHolder;
import br.com.app.reciclamais.model.Produto;


public class ListaProdutoCarrinhoAdapter extends RecyclerView.Adapter {

    private List<Produto> produtos;
    private Context context;


    public ListaProdutoCarrinhoAdapter (List<Produto> produtos, Context context){
        this.produtos = produtos;
        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.row_produto_carrinho_ativo, parent, false);
        return new ListaProdutoCarrinhoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ListaProdutoCarrinhoHolder holder = (ListaProdutoCarrinhoHolder) viewHolder;
        Produto produto = produtos.get(position);
        holder.getNome().setText(produto.getNome());
        holder.getFabricante().setText(produto.getNomeFabricante());
        holder.getPeso().setText(String.valueOf(produto.getPeso()));
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }
}
