package br.com.dbc.vemser.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

    private Integer idEndereco;
    private Integer idCliente;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String cep;
    private String bairro;
    private String cidade;
    private String estado;
    @NotNull
    private String tipoEndereco;

    @NotNull
    private String bairro;


    @NotNull
    private String pais;




}


