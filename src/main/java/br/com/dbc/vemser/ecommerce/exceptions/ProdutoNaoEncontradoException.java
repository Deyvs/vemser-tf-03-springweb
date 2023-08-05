package br.com.dbc.vemser.ecommerce.exceptions;

public class ProdutoNaoEncontradoException extends Exception {
    public ProdutoNaoEncontradoException(String mensagemDeErro) {
        super(mensagemDeErro);
    }

}