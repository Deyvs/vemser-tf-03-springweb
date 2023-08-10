package br.com.dbc.vemser.ecommerce.dto.pedido;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class PedidoCreateDTO {

    @NotNull
    @Positive
    @Schema(description = "O ID do produto associado ao pedido", required = true)
    private Integer idProduto;


}