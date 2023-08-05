package br.com.dbc.vemser.ecommerce.service;

import org.springframework.stereotype.Service;

@Service
public class PedidoXProdutoService {

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
