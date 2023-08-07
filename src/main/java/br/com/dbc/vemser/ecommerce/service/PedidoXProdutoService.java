package br.com.dbc.vemser.ecommerce.service;

import br.com.dbc.vemser.ecommerce.dto.produto.ProdutoDTO;
import br.com.dbc.vemser.ecommerce.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.ecommerce.repository.PedidoXProdutoRepository;
import br.com.dbc.vemser.ecommerce.utilitarias.ConverterProdutoParaDTOutil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PedidoXProdutoService {

    private PedidoXProdutoRepository pedidoXProdutoRepository;
    private ConverterProdutoParaDTOutil converterProdutoParaDTOutil;

    public List<ProdutoDTO> listarProdutosDoPedido(int idPedido) throws BancoDeDadosException {

        List<ProdutoDTO> produtos = pedidoXProdutoRepository.listarProdutosDoPedido(idPedido).stream()
                .map(converterProdutoParaDTOutil::converteProdutoParaDTO)
                .collect(Collectors.toList());
        return produtos;
    }

    public Boolean adicionarProdutoAoPedido(int idPedido, int idProduto) throws Exception {

        boolean produtoAdicionado = pedidoXProdutoRepository.adicionarProdutoAoPedido(idPedido, idProduto);

        if (!produtoAdicionado) throw new Exception("Erro ao adicionar produto");

        return produtoAdicionado;
    }


    public Boolean removerProdutoDoPedido(int idPedido, int idProduto) throws Exception {

        boolean produtoRemovido = pedidoXProdutoRepository.removerProdutoDoPedido(idPedido, idProduto);

        if (!produtoRemovido) throw new Exception("Erro ao remover produto");

        return produtoRemovido;

    }

    public void removerTodosProdutosDoPedido(int idPedido) throws BancoDeDadosException {

        pedidoXProdutoRepository.removerTodosProdutosDoPedido(idPedido);
    }


}

//    private PedidoXProdutoRepository pedidoXProdutoRepository;
//    public PedidoXProdutoService() {
//        pedidoXProdutoRepository = new PedidoXProdutoRepository();
//    }
//    public void listarProdutosDoPedido(int idPedido) {
//        try {
//            for (Produto produto : pedidoXProdutoRepository.listarProdutosDoPedido(idPedido)) {
//                produto.imprimir();
//            }
//
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void adicionarProdutoAoPedido(int idPedido, int idProduto){
//        try {
//            if (pedidoXProdutoRepository.adicionarProdutoAoPedido(idPedido, idProduto) == false) {
//                System.out.println("Erro ao adicionar produto");
//            }
//
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void removerProdutoDoPedido(int idPedido, int idProduto){
//        try {
//            if (pedidoXProdutoRepository.removerProdutoDoPedido(idPedido, idProduto) == false) {
//                System.out.println("Erro ao remover produto");
//            }
//
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void removerTodosProdutosDoPedido(int idPedido){
//        try {
//            pedidoXProdutoRepository.removerTodosProdutosDoPedido(idPedido);
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//        }
//    }
