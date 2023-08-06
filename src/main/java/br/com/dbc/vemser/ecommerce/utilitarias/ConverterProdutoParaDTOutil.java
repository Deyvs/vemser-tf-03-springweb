package br.com.dbc.vemser.ecommerce.utilitarias;

import br.com.dbc.vemser.ecommerce.dto.produto.ProdutoDTO;
import br.com.dbc.vemser.ecommerce.dto.produto.ProdutoInputDTO;
import br.com.dbc.vemser.ecommerce.entity.Produto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Component
public class ConverterProdutoParaDTOutil {


    private ObjectMapper objectMapper;

    public ProdutoDTO converteProdutoParaDTO(Produto produtoUpdate) {
        ProdutoDTO produtoUpdateDTO = new ProdutoDTO();

        produtoUpdateDTO.setIdProduto(produtoUpdate.getIdProduto());
        produtoUpdateDTO.setCor(produtoUpdate.getCor());
        produtoUpdateDTO.setModelo(produtoUpdate.getModelo());
        produtoUpdateDTO.setTamanho(produtoUpdate.getTamanho());
        produtoUpdateDTO.setIdProduto(produtoUpdate.getIdProduto());
        produtoUpdateDTO.setSetor(produtoUpdate.getSetor());
        produtoUpdateDTO.setValor(produtoUpdate.getValor());


        return produtoUpdateDTO;
    }

    public Produto converteDTOparaProduto(ProdutoInputDTO produtoInputDTO) {
        Produto produtoConvertido = objectMapper.convertValue(produtoInputDTO, Produto.class);

        produtoConvertido.setCor(produtoInputDTO.getCor());
        produtoConvertido.setModelo(produtoInputDTO.getModelo());
        produtoConvertido.setTamanho(produtoInputDTO.getTamanho());
        produtoConvertido.setSetor(produtoInputDTO.getSetor());
        produtoConvertido.setValor(produtoInputDTO.getValor());

        return produtoConvertido;
    }
}
