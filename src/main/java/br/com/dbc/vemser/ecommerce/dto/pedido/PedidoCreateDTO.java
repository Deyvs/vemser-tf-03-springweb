package br.com.dbc.vemser.ecommerce.dto.pedido;


import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class PedidoCreateDTO {

    @NotNull
    @Positive
    private Integer idProduto;


}
