package br.com.dbc.vemser.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoOutputDTO{


    private Integer idPedido;

    private Integer idCliente;

    private Integer idProduto;

    private Double valor;


}
