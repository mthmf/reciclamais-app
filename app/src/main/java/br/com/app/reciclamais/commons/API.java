package br.com.app.reciclamais.commons;


import java.util.List;

import br.com.app.reciclamais.dto.ProdutoDTO;
import br.com.app.reciclamais.model.BaixaCarrinho;
import br.com.app.reciclamais.model.BaixaRota;
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

    @POST("api/v1/baixarota")
    Call<Integer> sendBaixaRota(@Body BaixaRota baixa);

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
    Call<Integer> confirmaBaixa(@Body BaixaCarrinho baixaCarrinho);

    @POST("api/v1/rota")
    Call<Integer> cadastraRota(@Body Rota rota);

    @POST("api/v1/produto")
    Call<Integer> cadastraProduto(@Body ProdutoDTO produto);

    @GET("api/v1/produto/ident/{identificador}")
    Call<Produto> buscaProduto(@Path("identificador") String identificador);

    @PUT("api/v1/carrinho")
    Call<Carrinho> alteraCarrinho(@Body Carrinho carrinho);

    @POST("api/v1//rota/all")
    Call<List<Rota>> buscaRotasParaBaixa(@Body String st);


    @GET("api/v1/lixeira/rota/{idRota}")
    Call<List<Lixeira>> buscaLixeiraDaRota(@Path("idRota") Integer idRota);


    interface Builder {
        API build();
    }
}
