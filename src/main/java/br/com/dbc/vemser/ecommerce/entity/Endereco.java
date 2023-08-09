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
    @NotBlank
    @Size(max = 250, message = "Não pode conter mais de 250 caracteres")
    private String logradouro;
    @NotNull
    private Integer numero;
    private String complemento;
    @NotBlank
    @Size(max = 9, message = "Não pode conter mais que 9 caracteres")
    private String cep;
    @NotBlank
    @Size(max = 250, message = "Não pode conter mais de 250 caracteres")
    private String cidade;
    @NotNull
    private String estado;

    @NotNull
    private String bairro;
}


