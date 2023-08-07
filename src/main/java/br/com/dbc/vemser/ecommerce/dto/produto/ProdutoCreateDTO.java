package br.com.dbc.vemser.ecommerce.dto.produto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoCreateDTO {

    @NotNull
    @NotBlank
    private String modelo;

    @NotNull
    @NotBlank
    private String tamanho;
    @NotNull
    @NotBlank
    private String cor;
    @NotNull
    @NotBlank
    private String setor;

    @NotNull
    private Double valor;
}
