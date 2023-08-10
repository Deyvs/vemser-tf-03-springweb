package br.com.dbc.vemser.ecommerce.service;


import br.com.dbc.vemser.ecommerce.dto.produto.ProdutoDTO;

import br.com.dbc.vemser.ecommerce.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.ecommerce.repository.PedidoXProdutoRepository;
import br.com.dbc.vemser.ecommerce.utils.ConverterProdutoParaDTOutil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PedidoXProdutoService {

    private final PedidoXProdutoRepository pedidoXProdutoRepository;
    private final ConverterProdutoParaDTOutil converterProdutoParaDTOutil;

    public List<ProdutoDTO> listarProdutosDoPedido(Integer idPedido) throws Exception {

        List<ProdutoDTO> produtos = pedidoXProdutoRepository.listarProdutosDoPedido(idPedido).stream()
                .map(converterProdutoParaDTOutil::converteProdutoParaDTO)
                .collect(Collectors.toList());
        return produtos;
    }

    public Boolean adicionarProdutoAoPedido(Integer idPedido, Integer idProduto) throws Exception {

        Boolean produtoAdicionado = pedidoXProdutoRepository.adicionarProdutoAoPedido(idPedido, idProduto);

        if (!produtoAdicionado) throw new RegraDeNegocioException("Erro ao adicionar produto");

        return produtoAdicionado;
    }


    public Boolean removerProdutoDoPedido(Integer idPedido, Integer idProduto) throws Exception {

        Boolean produtoRemovido = pedidoXProdutoRepository.removerProdutoDoPedido(idPedido, idProduto);

        if (!produtoRemovido) throw new RegraDeNegocioException("Erro ao remover produto");

        return produtoRemovido;

    }

    public void removerTodosProdutosDoPedido(int idPedido) throws Exception {

        pedidoXProdutoRepository.removerTodosProdutosDoPedido(idPedido);
    }

}
