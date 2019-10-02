package br.com.app.reciclamais.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.app.reciclamais.R;
import br.com.app.reciclamais.holder.ListaProdutoCarrinhoHolder;
import br.com.app.reciclamais.holder.LixeiraHolder;
import br.com.app.reciclamais.model.Lixeira;
import br.com.app.reciclamais.model.Produto;


public class LixeiraAdapter extends RecyclerView.Adapter {

    private List<Lixeira> lixeiras;
    private Context context;
    private Lixeira lixeiraSelecionada;

    public LixeiraAdapter(List<Lixeira> lixeiras, Context context){
        this.lixeiras = lixeiras;
        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.row_lixeira_baixa, parent, false);
        return new LixeiraHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        final LixeiraHolder holder = (LixeiraHolder) viewHolder;
        final Lixeira lixeira = lixeiras.get(position);
        holder.getEndereco().setText(lixeira.getEndereco());
        holder.getPontoRef().setText(lixeira.getPontoReferencia());
        holder.getCapTotal().setText(String.valueOf(lixeira.getCapacidadeTotal()));
        holder.getCapAtual().setText(String.valueOf(lixeira.getCapacidadeAtual()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.selected = !holder.selected;
                System.out.println("Posição "+  position);
                if(holder.selected && lixeiraSelecionada == null){
                    lixeiraSelecionada = lixeiras.get(position);
                } else {
                    lixeiraSelecionada = null;
                }
                int color = (holder.selected ? R.color.colorPrimaryDark : R.color.colorAccent);
                holder.itemView.setBackgroundColor(ContextCompat.getColor(v.getContext(), color));
                holder.getPontoRef().setText("PONTO DA MERDA");
                notifyDataSetChanged();
            }
        });
    }

    public Lixeira getLixeiraSelecionada() {
        return lixeiraSelecionada;
    }

    @Override
    public int getItemCount() {
        return lixeiras.size();
    }
}
