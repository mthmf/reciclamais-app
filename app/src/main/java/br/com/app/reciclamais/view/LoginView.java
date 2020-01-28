package br.com.app.reciclamais.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import br.com.app.reciclamais.R;
import br.com.app.reciclamais.commons.Session;
import br.com.app.reciclamais.enums.PerfilEnum;
import br.com.app.reciclamais.model.Usuario;
import br.com.app.reciclamais.util.MaskEditUtil;
import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginView extends AbstractView {

    @BindView(R.id.edit_cpf)
    public TextInputEditText editCpf;

    @BindView(R.id.edit_nome)
    public TextInputEditText editNome;

    @BindView(R.id.edit_email)
    public TextInputEditText editEmail;

    @BindView(R.id.edit_pwd)
    public TextInputEditText editPwd;

    @BindView(R.id.text_mensagem)
    public TextView textMensagem;

    @BindView(R.id.button_login_save)
    public Button buttonLoginSave;

    @BindView(R.id.cpf_layout)
    public TextInputLayout cpfLayout;

    @BindView(R.id.nome_layout)
    public TextInputLayout nomeLayout;

    @BindView(R.id.email_layout)
    public TextInputLayout emailLayout;

    @BindView(R.id.pwd_layout)
    public TextInputLayout pwdLayout;

    private boolean novoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public View.OnClickListener login() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = new Usuario();
                usuario.setEmail(editEmail.getText().toString());
                usuario.setSenha(editPwd.getText().toString());

                if(Session.getInstance().getTrialVersion()){
                    dataProvider.buscaUsuario(usuario);
                } else {
                    final Context context = v.getContext();
                    Call<Usuario> call = api.login(usuario);
                    call.enqueue(new Callback<Usuario>() {
                        @Override
                        public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                            Usuario usuario = response.body();
                            if (usuario == null || usuario.getCodigo() <= 0) {

                                new AlertDialog.Builder(LoginView.this)
                                        .setTitle(R.string.msg_usuario_senha_incorreto)
                                        .setMessage("Não foi possível realizar o login")
                                        .setPositiveButton(R.string.ok, null)
                                        .show();
                            } else {
                                afterLoginSuccess(context, usuario);
                            }
                        }

                        @Override
                        public void onFailure(Call<Usuario> call, Throwable t) {
                            Log.e("Não foi possível fazer o login", "Erro ao realizar login: " + t.getMessage());
                        }
                    });
                }
            }
        };
    }

    public void afterLoginSuccess(Context context, Usuario usuario) {
        Session.getInstance().setUsuario(usuario);
        Intent intent = new Intent(context, MenuView.class);
        startActivity(intent);
    }

    public View.OnClickListener salvarUsuario() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = new Usuario();
                usuario.setCpf(MaskEditUtil.unmask(editCpf.getText().toString()));
                usuario.setNome(editNome.getText().toString());
                usuario.setEmail(editEmail.getText().toString());
                usuario.setSenha(editPwd.getText().toString());
                usuario.setPerfil(PerfilEnum.COMUM.getCodigo());

                if(Session.getInstance().getTrialVersion()){
                    dataProvider.adicionaUsuario(usuario);
                } else {
                    Call<Integer> call = api.sendUsuario(usuario);
                    call.enqueue(new Callback<Integer>() {
                        @Override
                        public void onResponse(Call<Integer> call, Response<Integer> response) {
                            if (response.code() != 201) {
                                new AlertDialog.Builder(LoginView.this)
                                        .setTitle(R.string.msg_usuario_ja_cadastrado)
                                        .setMessage(R.string.msg_usuario_nao_cadastrado)
                                        .setPositiveButton(R.string.ok, null)
                                        .show();
                            } else {
                                new AlertDialog.Builder(LoginView.this)
                                        .setTitle(R.string.msg_usuario_cadastrado_sucesso)
                                        .setPositiveButton(R.string.ok,  null)
                                        .show();
                                textMensagem.callOnClick();
                            }
                        }

                        @Override
                        public void onFailure(Call<Integer> call, Throwable t) {
                            Log.e("Usuário não cadastrado", "Erro ao cadastrar usuario" + t.getMessage());
                        }
                    });
                }
            }
        };
    }

    @Override
    public void startListenerHints() {
        editCpf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    cpfLayout.setHint(getString(R.string.inf_cpf));
                } else {
                    cpfLayout.setHint(getString(R.string.cpf));
                }
            }
        });

        editNome.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    nomeLayout.setHint(getString(R.string.inf_nome));
                } else {
                    nomeLayout.setHint(getString(R.string.nome));
                }
            }
        });

        editEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    emailLayout.setHint(getString(R.string.inf_email));
                } else {
                    emailLayout.setHint(getString(R.string.email));
                }
            }
        });

        editPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    pwdLayout.setHint(getString(R.string.inf_senha));
                } else {
                    pwdLayout.setHint(getString(R.string.senha));
                }
            }
        });
    }

    @Override
    public void startElements() {
        novoLogin = true;
        textMensagem.setText(R.string.criar_conta);
        editCpf.setVisibility(View.GONE);
        editNome.setVisibility(View.GONE);
        editCpf.addTextChangedListener(MaskEditUtil.mask(editCpf, MaskEditUtil.FORMAT_CPF));
        buttonLoginSave.setOnClickListener(login());
        startListenerHints();

        textMensagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                novoLogin = !novoLogin;
                if (novoLogin) {
                    textMensagem.setText(R.string.criar_conta);
                    buttonLoginSave.setText(R.string.login);
                    buttonLoginSave.setOnClickListener(login());
                    editCpf.setVisibility(View.GONE);
                    editNome.setVisibility(View.GONE);
                } else {
                    textMensagem.setText(R.string.fazer_login);
                    buttonLoginSave.setText(R.string.salvar);
                    buttonLoginSave.setOnClickListener(salvarUsuario());
                    editCpf.setVisibility(View.VISIBLE);
                    editNome.setVisibility(View.VISIBLE);
                }
            }
        });
    }

}
