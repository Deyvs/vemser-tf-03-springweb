package br.com.dbc.vemser.ecommerce.service;

import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
}

//    private ProdutoRepository produtoRepository;
//
//    public ProdutoService() {
//        produtoRepository = new ProdutoRepository();
//    }
//
//    public void listar() {
//        try {
//            for (Produto produto : produtoRepository.listar()) {
//                produto.imprimir();
//            }
//
//
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public Integer getMaximoID() {
//        try {
//            return produtoRepository.getMaximoId();
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }
//
//    public Produto buscarProduto(int idProduto) {
//        try {
//            Produto produto = produtoRepository.buscarProduto(idProduto);
//            if (produto == null) {
//                System.out.println("Produto não encontrado");
//            } return produto;
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }