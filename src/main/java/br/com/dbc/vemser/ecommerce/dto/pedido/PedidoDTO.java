package br.com.dbc.vemser.ecommerce.dto.pedido;

import br.com.dbc.vemser.ecommerce.dto.produto.ProdutoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoOutputDTO{


    private Integer idPedido;

    private Integer idCliente;

    private Double valor;

    private String statusPedido;

    List<ProdutoDTO> produtos;

}