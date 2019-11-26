package br.com.app.reciclamais.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.com.app.reciclamais.R;
import br.com.app.reciclamais.model.Lixeira;


public class LixeiraHolder extends RecyclerView.ViewHolder{

    public TextView endereco;
    public TextView pontoRef;
    public TextView capTotal;
    public TextView capAtual;

    public Lixeira lixeiraSelecionada;
    public View itemView;
    public boolean selected;

    public LixeiraHolder(@NonNull View view) {
        super(view);
        itemView = view;
        endereco = (TextView) view.findViewById(R.id.row_endereco);
        pontoRef = (TextView) view.findViewById(R.id.row_ponto_ref_);
        capTotal = (TextView) view.findViewById(R.id.row_cap_total);
        capAtual = (TextView) view.findViewById(R.id.row_cap_atual);
    }

    public TextView getEndereco() {
        return endereco;
    }

    public TextView getPontoRef() {
        return pontoRef;
    }

    public TextView getCapTotal() {
        return capTotal;
    }

    public TextView getCapAtual() {
        return capAtual;
    }
}
