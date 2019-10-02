package br.com.app.reciclamais;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import br.com.app.reciclamais.commons.Session;
import br.com.app.reciclamais.model.Usuario;
import br.com.app.reciclamais.util.MaskEditUtil;
import br.com.app.reciclamais.view.LixeiraView;
import br.com.app.reciclamais.view.RotaView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    @BindView(R.id.edit_cpf)
    public EditText editCpf;

    @BindView(R.id.edit_nome)
    public EditText editNome;

    @BindView(R.id.edit_email)
    public EditText editEmail;

    @BindView(R.id.edit_pwd)
    public EditText editPwd;

    @BindView(R.id.text_mensagem)
    public TextView textMensagem;

    @BindView(R.id.button_login_save)
    public Button buttonLoginSave;

    private boolean novoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startElements();
    }


    public View.OnClickListener login(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = new Usuario();
                usuario.setEmail(editEmail.getText().toString());
                usuario.setSenha(editPwd.getText().toString());
                final Context context = v.getContext();

                //Session.getInstance().setUsuario(usuario);
               /* Intent intent = new Intent(context, CarrinhoAtivoView.class);
                startActivity(intent);*/

                Call<Usuario> call = ReciclaApplication.getInstance().getAPI().login(usuario);
                call.enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        Usuario usuario = response.body();
                        if(usuario == null || usuario.getCodigo() <= 0){
                            System.out.println("Codigo: " + response.code());
                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("Usuário ou senha incorretos")
                                    .setMessage("Não foi possível realizar o login")
                                    .setPositiveButton("OK", null)
                                    .show();
                        } else {
                            afterLoginSuccess(context, usuario);
                        }
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        Log.e("Não foi possível fazer o login", "Erro ao realizar login"+ t.getMessage());
                    }
                });
            }
        };
    }

    public void afterLoginSuccess(Context context,Usuario usuario){

        Session.getInstance().setUsuario(usuario);
        Intent intent = new Intent(context, LixeiraView.class);
        startActivity(intent);
    }



    public View.OnClickListener salvarUsuario(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = new Usuario();
                usuario.setCpf(editCpf.getText().toString());
                usuario.setNome(editNome.getText().toString());
                usuario.setEmail(editEmail.getText().toString());
                usuario.setSenha(editPwd.getText().toString());
                Context context = v.getContext();

                Call<Integer> call = ReciclaApplication.getInstance().getAPI().sendUsuario(usuario);
                call.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if(response.code() != 201){
                            System.out.println("Codigo: " + response.code());
                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("CPF ou E-mail já cadastrado")
                                    .setMessage("Não foi possível cadastrar o usuário")
                                    .setPositiveButton("OK", null)
                                    .show();
                        } else {
                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("Usuário cadastrado com sucesso")
                                    .setPositiveButton("OK", null)
                                    .show();
                             textMensagem.callOnClick();
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Log.e("Usuário não cadastrado", "Erro ao cadastrar usuario"+ t.getMessage());
                    }
                });
            }
        };
    }



    public void startElements(){
        novoLogin = true;
        textMensagem.setText("Não possui uma conta? Crie agora!");
        editCpf.setVisibility(View.GONE);
        editNome.setVisibility(View.GONE);
        editCpf.addTextChangedListener(MaskEditUtil.mask(editCpf, MaskEditUtil.FORMAT_CPF));
        buttonLoginSave.setOnClickListener(login());
        textMensagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                novoLogin = !novoLogin;
                if(novoLogin){
                    textMensagem.setText("Não possui uma conta? Crie agora!");
                    buttonLoginSave.setText("Login");
                    buttonLoginSave.setOnClickListener(login());
                    editCpf.setVisibility(View.GONE);
                    editNome.setVisibility(View.GONE);
                } else {
                    textMensagem.setText("Já é cadastrado? Faça seu login!");
                    buttonLoginSave.setText("Salvar");
                    buttonLoginSave.setOnClickListener(salvarUsuario());
                    editCpf.setVisibility(View.VISIBLE);
                    editNome.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
