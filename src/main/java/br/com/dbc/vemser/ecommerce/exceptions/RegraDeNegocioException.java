package br.com.dbc.vemser.ecommerce.exceptions;

public class RegraDeNegocioException extends Exception {
    public RegraDeNegocioException(String mensagemDeErro) {
        super(mensagemDeErro);
    }

}