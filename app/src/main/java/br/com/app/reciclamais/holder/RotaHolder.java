package br.com.app.reciclamais.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.com.app.reciclamais.R;
import br.com.app.reciclamais.model.Rota;


public class RotaHolder extends RecyclerView.ViewHolder {

    public TextView descricao;
    public TextView dataInicio;
    public TextView dataFim;

    public Rota rotaSelecionada;
    public View itemView;
    public boolean selected;

    public RotaHolder(@NonNull View view) {
        super(view);
        itemView = view;
        descricao = (TextView) view.findViewById(R.id.row_descricao_rota);
        dataInicio = (TextView) view.findViewById(R.id.row_data_fim_rota);
        dataFim = (TextView) view.findViewById(R.id.row_data_fim_inicio);
    }

    public TextView getDescricao() {
        return descricao;
    }
    public TextView getDataInicio() {
        return dataInicio;
    }

    public TextView getDataFim() {
        return dataFim;
    }
}
