package br.com.dbc.vemser.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoCreateDTO {
    @Positive
    private Integer idProduto;

    @PositiveOrZero
    private Integer quantidade;

    @NotBlank
    @Size(max = 3)
    private String statusPedido;
}
