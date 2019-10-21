package br.com.app.reciclamais.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.app.reciclamais.R;
import br.com.app.reciclamais.ReciclaApplication;
import br.com.app.reciclamais.adapter.LixeiraAdapter;
import br.com.app.reciclamais.model.BaixaRota;
import br.com.app.reciclamais.model.Lixeira;
import br.com.app.reciclamais.model.Rota;
import br.com.app.reciclamais.util.MaskEditUtil;
import br.com.app.reciclamais.util.Util;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaitaRotaView extends Activity {

    @BindView(R.id.recycler_lixeiras_rota_consulta)
    public RecyclerView recyclerLixeira;

    @BindView(R.id.btn_confirmar_baixa_rota)
    public Button btnConfirmaBaixa;

    @BindView(R.id.text_data_baixa)
    public TextView dataBaixa;

    @BindView(R.id.edit_hora_chegada)
    public EditText horaChegada;

    @BindView(R.id.edit_hora_saida)
    public EditText horaSaida;

    @BindView(R.id.observacao)
    public EditText observacao;


    private LixeiraAdapter adapter;

    private Rota rota;
    private BaixaRota baixaRota;

    private List<Lixeira> lixeiras = new ArrayList<Lixeira>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baixa_rota_cadastro);
        ButterKnife.bind(this);
        dataBaixa.setText(Util.getDate(LocalDateTime.now()));
        horaChegada.addTextChangedListener(MaskEditUtil.mask(horaChegada, MaskEditUtil.FORMAT_HOUR));
        horaSaida.addTextChangedListener(MaskEditUtil.mask(horaSaida, MaskEditUtil.FORMAT_HOUR));

        rota = (Rota) getIntent().getExtras().getSerializable("rota");

    }

    @Override
    protected void onResume() {
        super.onResume();
        startElements();
    }

    public void startElements() {
        baixaRota = new BaixaRota();

        Call<List<Lixeira>> lixeirasCall = ReciclaApplication.getInstance().getAPI().buscaLixeiraDaRota(rota.getCodigo());
        lixeirasCall.enqueue(new Callback<List<Lixeira>>() {
            @Override
            public void onResponse(Call<List<Lixeira>> call, Response<List<Lixeira>> response) {
                adapter = new LixeiraAdapter(lixeiras, BaitaRotaView.this);
                recyclerLixeira.setAdapter(adapter);
                RecyclerView.LayoutManager layout = new LinearLayoutManager(BaitaRotaView.this, RecyclerView.VERTICAL, false);
                recyclerLixeira.setLayoutManager(layout);
            }

            @Override
            public void onFailure(Call<List<Lixeira>> call, Throwable t) {
                Log.e("Não foi possível buscar as rotas do carrinho", "Erro ao buscar produtos do carrinho"+ t.getMessage());

            }
        });


        btnConfirmaBaixa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String [] chegada =  horaChegada.getText().toString().split(":");
                String [] saida = horaSaida.getText().toString().split(":");

                LocalDateTime chegadaFinal = LocalDate.now().atTime(Integer.valueOf(chegada[0]), Integer.valueOf(chegada[1]));
                LocalDateTime saidaFinal = LocalDate.now().atTime(Integer.valueOf(saida[0]), Integer.valueOf(saida[0]));

                baixaRota.setCodigoRota(rota.getCodigo());
                baixaRota.setObservacao(observacao.getText().toString());
                baixaRota.setDataBaixa(Util.getDateTime(LocalDateTime.now()));
                baixaRota.setDataSaida(Util.getDateTime(saidaFinal));
                baixaRota.setDataChegada(Util.getDateTime(chegadaFinal));

                Call<Integer> baixaRotaCall= ReciclaApplication.getInstance().getAPI().sendBaixaRota(baixaRota);
                baixaRotaCall.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if(response.code() != 201){
                            new AlertDialog.Builder(BaitaRotaView.this)
                                    .setTitle("Não foi possível cadastrar a baixa")
                                    .setMessage("Ocorreu um problema ao realizar a baixa")
                                    .setPositiveButton("OK", null)
                                    .show();
                        } else {
                            new AlertDialog.Builder(BaitaRotaView.this)
                                    .setTitle("Baixa realizada com sucesso")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            Intent intent = new Intent(BaitaRotaView.this, MenuView.class);
                                            startActivity(intent);
                                        }
                                    }).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {

                    }
                });




            }
        });

    }

}
