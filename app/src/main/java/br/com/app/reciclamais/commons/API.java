package br.com.app.reciclamais.commons;


import java.util.List;

import br.com.app.reciclamais.model.BaixaCarrinho;
import br.com.app.reciclamais.model.Carrinho;
import br.com.app.reciclamais.model.Lixeira;
import br.com.app.reciclamais.model.Produto;
import br.com.app.reciclamais.model.Rota;
import br.com.app.reciclamais.model.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API {

    @GET("api/v1/user/{codigo}")
    Call<Usuario> buscaUsuario(@Path("codigo") Integer codigo);

    @POST("api/v1/user")
    Call<Integer> sendUsuario(@Body Usuario usuario);

    @POST("api/v1/user/login")
    Call<Usuario> login(@Body Usuario usuario);

    @POST("api/v1/carrinho/atual")
    Call<Carrinho> buscaCarrinhoAtual(@Body Usuario usuario);

    @POST("api/v1/carrinho/produtos")
    Call<List<Produto>> buscaProdutosCarrinho(@Body Carrinho carrinho);

    @POST("api/v1/lixeira/all")
    Call<List<Lixeira>> buscaLixeiras(@Body String st);

    @POST("api/v1/lixeira")
    Call<Integer> salvarLixeira(@Body Lixeira lixeira);

    @PUT("api/v1/lixeira")
    Call<Lixeira> alteraLixeira(@Body Lixeira lixeira);

    @POST("api/v1/baixacarrinho")
    Call<Integer> confirmaAgendamentoBaixa(@Body BaixaCarrinho baixaCarrinho);

    @POST("api/v1/rota")
    Call<Integer> cadastraRota(@Body Rota rota);

    interface Builder {
        API build();
    }
}
