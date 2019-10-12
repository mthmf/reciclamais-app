package br.com.app.reciclamais.holder;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.com.app.reciclamais.R;
import br.com.app.reciclamais.model.Lixeira;
import br.com.app.reciclamais.model.Rota;


public class RotaHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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

    @Override
    public void onClick(View v) {
        this.selected = !selected;
        v.setBackgroundColor(Color.CYAN);

    }

    public TextView getDescricao() {
        return descricao;
    }

    public void setDescricao(TextView descricao) {
        this.descricao = descricao;
    }

    public TextView getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(TextView dataInicio) {
        this.dataInicio = dataInicio;
    }

    public TextView getDataFim() {
        return dataFim;
    }

    public void setDataFim(TextView dataFim) {
        this.dataFim = dataFim;
    }
}
