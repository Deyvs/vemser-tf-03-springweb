package br.com.dbc.vemser.ecommerce.dto.produto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoCreateDTO {


    @NotBlank
    @Schema(description = "Modelo do produto", required = true, example = "MANGALONGA")
    private String modelo;


    @NotBlank
    @Schema(description = "Tamanho do produto", required = true, example = "P")
    private String tamanho;


    @NotBlank
    @Schema(description = "Cor do produto", required = true, example = "Branco")
    private String cor;


    @NotBlank
    @Schema(description = "Setor do produto", required = true, example = "FEMININO")
    private String setor;

    @NotNull
    @Schema(description = "Valor do produto", required = true)
    private Double valor;
}