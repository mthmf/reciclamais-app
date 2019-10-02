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

    @PUT("api/v1/lixeira")
    Call<Lixeira> alteraLixeira(@Body Lixeira lixeira);

    @POST("api/v1/baixacarrinho")
    Call<Integer> confirmaAgendamentoBaixa(@Body BaixaCarrinho baixaCarrinho);

    @POST("api/v1/rota")
    Call<Integer> cadastraRota(@Body Rota rota);


/*
    @GET("?ppopcao=55&requisicao=48&opcao=1&request=1")
    Call<ApiRoute> pdvs(@Query("cdUnb") String unb, @Query("idUsuario") String idUsuario);

    @GET("http://receitaws.com.br/v1/cnpj/{value}")
    Call<ApiReceitaInfo> searchAddress(@Path("value") String cnpj);

    @GET("http://52.70.21.228:8089/v1/cdd/nearby?limit=1&resale=true")
    Call<List<ApiNearby>> searchNearby(@Query("lat") String latitude, @Query("lon") String longitude);

    @Multipart
    @POST("http://186.250.184.95/backup")
    Call<Void> sendBackup(@Query("timestamp") String timestamp,
                          @Query("nonce") String nonce,
                          @Part MultipartBody.Part file);

    @GET("url")
    Call<ApiPesquisaNge> getPesquisaNge();


    @POST(BuildConfig.PHOENIX_URL + "/api/gcad/input/success")
    Call<ApiPhoenixResponse> sendPhoenixSuccess(@Header("Authorization") String auth, @Body ApiDischargePhoenixSuggestionSuccess success);

    @POST(BuildConfig.PHOENIX_URL + "/api/gcad/input/failure")
    Call<ApiPhoenixResponse> sendPhoenixFailure(@Header("Authorization") String auth, @Body ApiDischargePhoenixSuggestionFailure failure);

    @POST(BuildConfig.PHOENIX_URL + "/api/gcad/input/closed")
    Call<ApiPhoenixResponse> sendPhoenixClosed(@Header("Authorization") String auth, @Body ApiDischargePhoenixSuggestionFailure failure);

    @POST(BuildConfig.PHOENIX_URL + "/api/gcad/input/coordinates")
    Call<ApiPhoenixResponse> sendPhoenixCoordinate(@Header("Authorization") String auth, @Body ApiDischargePhoenixCoordinateContainer container);

    @GET(BuildConfig.PHOENIX_URL + "/api/suggestion/photos/{suggestionId}")
    Call<ApiPhoenixSuggestionImage> getPhoenixImages(@Header("Authorization") String auth, @Path("suggestionId") String suggestionId);

    @GET(BuildConfig.PHOENIX_URL + "/api/estados")
    Call<List<ApiPhoenixUFs.PhoenixUF>> getAllUfs(@Header("Authorization") String auth);

    @GET(BuildConfig.PHOENIX_URL + "/api/{uf}/cidades")
    Call<List<ApiPhoenixCities.PhoenixCity>> getAllCities(@Header("Authorization") String auth, @Path("uf") String uf);

    @GET(BuildConfig.PHOENIX_URL + "/api/{uf}/{city}/bairros")
    Call<List<ApiPhoenixNeighborhoods.PhoenixNeighborhood>> getNeighborhoods(@Header("Authorization") String auth, @Path("uf") String uf, @Path("city") String city);

    @GET(BuildConfig.PHOENIX_URL + "/api/gcad/{uf}/{city}/suggestions-not-visited")
    Call<ApiPhoenix> getSuggestions(@Header("Authorization") String auth,
                                    @Path("uf") String uf,
                                    @Path("city") String city);

    @GET(BuildConfig.PHOENIX_URL + "/api/nearest/suggestions-not-visited")
    Call<ApiPhoenix> getSuggestionsNear(@Header("Authorization") String auth,
                                        @Query("latitude") Double latitude,
                                        @Query("longitude") Double longitude, @Query("atlas") boolean atlas);

    @POST(BuildConfig.PHOENIX_URL + "/api/verify-visited-point")
    Call<Boolean> verifyIfPointIsVisited(@Header("Authorization") String auth, @Body RequestBody pointId);


    @GET(BuildConfig.PHOENIX_URL + "/api/gcad/{estado}/{cidade}/{unb}/{setor}/suggestions-not-visited")
    Call<List<ApiPhoenixSuggestion>> getSalesData(@Header("Authorization") String auth, @Path("estado") String uf,
                                                  @Path("cidade") String city,
                                                  @Path("unb") String unb,
                                                  @Path("setor") int userCode,
                                                  @Query("bairros") String[] neighborhoods);
*/

    interface Builder {
        API build();
    }
}
