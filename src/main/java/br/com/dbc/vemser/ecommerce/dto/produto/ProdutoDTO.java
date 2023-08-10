package br.com.dbc.vemser.ecommerce.dto.produto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO extends ProdutoCreateDTO {

    private Integer idProduto;
}