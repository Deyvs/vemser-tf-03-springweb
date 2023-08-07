package br.com.dbc.vemser.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    private Integer idPedido;
    private Integer idCliente;
    private Double valor;
    private String statusPedido;
}
