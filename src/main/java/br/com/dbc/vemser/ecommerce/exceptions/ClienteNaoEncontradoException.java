package br.com.dbc.vemser.ecommerce.exceptions;

public class ClienteNaoEncontradoException extends Exception {
    public ClienteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
