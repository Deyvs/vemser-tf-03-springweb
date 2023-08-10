package br.com.dbc.vemser.ecommerce.utils;


import br.com.dbc.vemser.ecommerce.dto.produto.ProdutoCreateDTO;
import br.com.dbc.vemser.ecommerce.dto.produto.ProdutoDTO;
import br.com.dbc.vemser.ecommerce.entity.Produto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Component
public class ConverterProdutoParaDTOutil {

    @Autowired
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

    public Produto converteDTOparaProduto(ProdutoCreateDTO produtoCreateDTO) {
        Produto produtoConvertido = objectMapper.convertValue(produtoCreateDTO, Produto.class);

        produtoConvertido.setCor(produtoCreateDTO.getCor());
        produtoConvertido.setModelo(produtoCreateDTO.getModelo());
        produtoConvertido.setTamanho(produtoCreateDTO.getTamanho());
        produtoConvertido.setSetor(produtoCreateDTO.getSetor());
        produtoConvertido.setValor(produtoCreateDTO.getValor());

        return produtoConvertido;
    }
}
