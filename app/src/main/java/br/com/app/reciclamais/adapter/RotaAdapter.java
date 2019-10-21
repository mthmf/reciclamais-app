package br.com.app.reciclamais.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.app.reciclamais.R;
import br.com.app.reciclamais.holder.LixeiraHolder;
import br.com.app.reciclamais.holder.RotaHolder;
import br.com.app.reciclamais.model.Lixeira;
import br.com.app.reciclamais.model.Rota;


public class RotaAdapter extends RecyclerView.Adapter {

    private List<Rota> rotas;
    private Context context;
    private Rota rotaSelecionada;

    public RotaAdapter(List<Rota> rotas, Context context){
        this.rotas = rotas;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.row_rota, parent, false);
        return new RotaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        final RotaHolder holder = (RotaHolder) viewHolder;
        final Rota rota = rotas.get(position);
        holder.getDescricao().setText(rota.getDescricao());
        holder.getDataInicio().setText(rota.getDataInicio());
        holder.getDataFim().setText(rota.getDataFinal());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.selected = !holder.selected;
                System.out.println("Posição "+  position);
                if(holder.selected && rotaSelecionada == null){
                    rotaSelecionada = rotas.get(position);
                } else {
                    rotaSelecionada = null;
                }
                int color = (holder.selected ? R.color.colorPrimaryDark : R.color.colorAccent);
                holder.itemView.setBackgroundColor(ContextCompat.getColor(v.getContext(), color));
                notifyDataSetChanged();
            }
        });
    }

    public Rota getRotaSelecionada() {
        return rotaSelecionada;
    }

    @Override
    public int getItemCount() {
        return rotas.size();
    }
}
