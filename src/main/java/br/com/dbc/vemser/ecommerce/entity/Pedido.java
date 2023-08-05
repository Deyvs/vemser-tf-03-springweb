package br.com.dbc.vemser.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    private Integer idPedido;
    private Integer idCliente;
    private Integer idProduto;
    private Double valor;
    private String statusPedido;
}
