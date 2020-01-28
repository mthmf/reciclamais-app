package br.com.app.reciclamais.util;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.app.reciclamais.model.BaixaCarrinho;
import br.com.app.reciclamais.model.BaixaRota;
import br.com.app.reciclamais.model.Carrinho;
import br.com.app.reciclamais.model.Lixeira;
import br.com.app.reciclamais.model.Produto;
import br.com.app.reciclamais.model.Rota;
import br.com.app.reciclamais.model.Usuario;

public class DataProvider {

    private List<BaixaCarrinho> listaBaixaCarrinho;
    private List<BaixaRota> listaBaixaRota;
    private List<Carrinho> listaCarrinho;
    private List<Lixeira> listaLixeira;
    private List<Produto> listaProduto;
    private List<Rota> listaRota;
    private List<Usuario> listaUsuario;

    public DataProvider(){
        iniciaListaUsuario();
        iniciaListaRota();
        iniciaListaProduto();
        iniciaListaLixeira();
        iniciaListaBaixaRota();
        iniciaListaCarrinho();
        iniciaListaBaixaCarrinho();
    }

    public void iniciaListaBaixaCarrinho(){
        listaBaixaCarrinho = new ArrayList<>();
    }
    public void iniciaListaBaixaRota(){
        listaBaixaRota = new ArrayList<>();
    }

    public void iniciaListaCarrinho(){
        listaCarrinho = new ArrayList<>();

        Carrinho carrinho = new Carrinho();
        carrinho.setCodigo(1);
        carrinho.setTotalPesoReciclavel(new BigDecimal(100));
        carrinho.setDataCriacao(Util.getDate(LocalDateTime.now()));
        carrinho.setUsuario(1);
        carrinho.setAtivo(true);

        Carrinho carrinho2 = new Carrinho();
        carrinho2.setCodigo(1);
        carrinho2.setTotalPesoReciclavel(new BigDecimal(100));
        carrinho2.setDataCriacao(Util.getDate(LocalDateTime.now()));
        carrinho2.setUsuario(2);
        carrinho2.setAtivo(true);

        listaCarrinho.add(carrinho);
        listaCarrinho.add(carrinho2);
    }

    public void iniciaListaLixeira(){
        listaLixeira = new ArrayList<>();

        Lixeira lixeira = new Lixeira();
        lixeira.setCodigo(1);
        lixeira.setPontoReferencia("Igreja matriz");
        lixeira.setCapacidadeTotal(new BigDecimal(100));
        lixeira.setEndereco("R. Frieda Weber, Fortaleza");
        lixeira.setLatitude("-26.878937");
        lixeira.setLongitude("-49.067446");


        Lixeira lixeira2 = new Lixeira();
        lixeira2.setCodigo(2);
        lixeira2.setPontoReferencia("Atêlie Papel");
        lixeira2.setCapacidadeTotal(new BigDecimal(100));
        lixeira2.setEndereco("R. Francisco Vahldieck - Fortaleza");
        lixeira2.setLatitude("-26.875856");
        lixeira2.setLongitude("-49.065354");

        Lixeira lixeira3 = new Lixeira();
        lixeira3.setCodigo(3);
        lixeira3.setPontoReferencia("Atêlie Papel");
        lixeira3.setCapacidadeTotal(new BigDecimal(100));
        lixeira3.setEndereco("R. Fritz Koegler - Fortaleza");
        lixeira3.setLatitude("-26.875248");
        lixeira3.setLongitude("-49.066825");

        listaLixeira.add(lixeira);
        listaLixeira.add(lixeira2);
        listaLixeira.add(lixeira3);
    }

    public void iniciaListaProduto(){

        listaProduto = new ArrayList<>();
        Produto produto = new Produto();
        produto.setCodigo(1);
        produto.setIdentificador("PRV-01-CAFE-BR");
        produto.setNome("Cafe solúvel");
        produto.setNomeFabricante("Caboclo");
        produto.setPeso(new BigDecimal(1.0));

        Produto produto2 = new Produto();
        produto2.setCodigo(2);
        produto2.setIdentificador("PRV-02-FILTRO-CAFE-BR");
        produto2.setNome("Filtro de Café");
        produto2.setNomeFabricante("Melitta");
        produto2.setPeso(new BigDecimal(0.503));

        Produto produto3 = new Produto();
        produto3.setCodigo(3);
        produto3.setIdentificador("PRV-03-SABAO-PO-BR");
        produto3.setNome("Sabão em pó");
        produto3.setNomeFabricante("Omo");
        produto3.setPeso(new BigDecimal(0.900));

        Produto produto4 = new Produto();
        produto4.setCodigo(4);
        produto4.setIdentificador("PRV-04-AGUA-SANITARIA-BR");
        produto4.setNome("Água sanitária");
        produto4.setNomeFabricante("Cruzado");
        produto4.setPeso(new BigDecimal(0.098));

        Produto produto5 = new Produto();
        produto5.setCodigo(5);
        produto5.setIdentificador("PRV-05-DETERGENTE-BR");
        produto5.setNome("Detergente");
        produto5.setNomeFabricante("Limpol");
        produto5.setPeso(new BigDecimal(0.500));


        listaProduto.add(produto);
        listaProduto.add(produto2);
        listaProduto.add(produto3);
        listaProduto.add(produto4);
        listaProduto.add(produto5);
    }

    public void iniciaListaRota(){
        listaRota = new ArrayList<>();
        Rota rota = new Rota();
        rota.setCodigo(1);
        rota.setDescricao("ROTA BAIRRO CENTRO");
        rota.setDataInicio("01/12/2019");
        rota.setDataFinal("01/12/2020");

        Rota rota2 = new Rota();
        rota2.setCodigo(2);
        rota2.setDescricao("ROTA BAIRRO FORTALEZA");
        rota2.setDataInicio("01/12/2019");
        rota2.setDataFinal("01/12/2020");

        Rota rota3 = new Rota();
        rota3.setCodigo(2);
        rota3.setDescricao("ROTA BAIRRO MILAGRES");
        rota3.setDataInicio("01/12/2019");
        rota3.setDataFinal("01/12/2020");

        listaRota.add(rota);
        listaRota.add(rota2);
        listaRota.add(rota3);

    }

    public void iniciaListaUsuario(){
        listaUsuario = new ArrayList<>();
        Usuario usuario = new Usuario();
        usuario.setCodigo(1);
        usuario.setEmail("teste@teste.com.br");
        usuario.setSenha("123456");
        usuario.setCpf("31778364004");
        usuario.setNome("USUARIO TESTE 1");

        Usuario usuario2 = new Usuario();
        usuario.setCodigo(2);
        usuario2.setEmail("teste2@teste2.com.br");
        usuario2.setSenha("123456");
        usuario2.setCpf("39838825018");
        usuario2.setNome("USUARIO TESTE 2");

        Usuario usuario3 = new Usuario();
        usuario.setCodigo(3);
        usuario3.setEmail("teste3@teste3.com.br");
        usuario3.setSenha("123456");
        usuario3.setCpf("29224858031");
        usuario3.setNome("USUARIO TESTE 3");

        listaUsuario.add(usuario);
        listaUsuario.add(usuario2);
        listaUsuario.add(usuario3);
    }

    public void alteraCarrinho(Carrinho carrinho){
        for(Carrinho car : listaCarrinho){
            if(car.getCodigo().equals(carrinho.getCodigo())){
                car.setAtivo(false);
            }
        }
    }

    public boolean buscaUsuario(final Usuario usuario){
        for(Usuario user: listaUsuario){
            if(user.getEmail() == usuario.getEmail() && user.getSenha() == usuario.getSenha()){
                return true;
            }
        }
        return false;
    }

    public Carrinho buscaCarrinhoAtivo(final Usuario usuario) {
        for (Carrinho carrinho : listaCarrinho) {
            if (carrinho.getUsuario() == usuario.getCodigo() && carrinho.getAtivo()) {
                return carrinho;
            }
        }
        return null;
    }

    public List<Rota> buscaRotas(){
        return listaRota;
    }

    public List<Produto> buscaProdutosCarrinho(final Carrinho carrinho){
        return listaProduto;
    }

    public boolean adicionaUsuario(Usuario usuario){
        return listaUsuario.add(usuario);
    }

    public boolean adicionarBaixaCarrinho(BaixaCarrinho baixaCarrinho) { return listaBaixaCarrinho.add(baixaCarrinho);}

    public boolean adicionaProduto(Produto produto){ return listaProduto.add(produto);}

    public boolean adicionaRota(Rota rota){ return listaRota.add(rota); }

    public boolean adicionaPontoColeta(Lixeira lixeira) { return listaLixeira.add(lixeira); }

    public boolean adicionaCarrinho(Carrinho carrinho){ return listaCarrinho.add(carrinho); }

    public boolean adicionaBaixaRota(BaixaRota baixaRota) { return listaBaixaRota.add(baixaRota);}
}
