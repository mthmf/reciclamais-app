package br.com.app.reciclamais.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.app.reciclamais.R;
import br.com.app.reciclamais.holder.ProdutoCarrinhoHolder;
import br.com.app.reciclamais.model.Produto;


public class ProdutoCarrinhoAdapter extends RecyclerView.Adapter {

    private List<Produto> produtos;

    public ProdutoCarrinhoAdapter(List<Produto> produtos){
        this.produtos = produtos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_produto_carrinho_ativo, parent, false);
        return new ProdutoCarrinhoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ProdutoCarrinhoHolder holder = (ProdutoCarrinhoHolder) viewHolder;
        holder.bind(produtos.get(position));
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }
}
