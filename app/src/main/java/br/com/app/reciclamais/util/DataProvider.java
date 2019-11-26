package br.com.app.reciclamais.util;

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
    }


    public void iniciaListaBaixaCarrinho(){
        listaBaixaCarrinho = new ArrayList<>();

    }
    public void iniciaListaBaixaRota(){
        listaBaixaRota = new ArrayList<>();
    }
    public void iniciaListaCarrinho(){
        listaCarrinho = new ArrayList<>();
    }

    public void iniciaListaLixeira(){
        listaLixeira = new ArrayList<>();
    }

    public void iniciaListaProduto(){
        listaProduto = new ArrayList<>();
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
        usuario.setEmail("teste@teste.com.br");
        usuario.setSenha("123456");
        usuario.setCpf("31778364004");
        usuario.setNome("USUARIO TESTE 1");

        Usuario usuario2 = new Usuario();
        usuario2.setEmail("teste2@teste2.com.br");
        usuario2.setSenha("123456");
        usuario2.setCpf("39838825018");
        usuario2.setNome("USUARIO TESTE 2");

        Usuario usuario3 = new Usuario();
        usuario3.setEmail("teste3@teste3.com.br");
        usuario3.setSenha("123456");
        usuario3.setCpf("29224858031");
        usuario3.setNome("USUARIO TESTE 3");

        listaUsuario.add(usuario);
        listaUsuario.add(usuario2);
        listaUsuario.add(usuario3);
    }
}
