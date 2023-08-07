package br.com.dbc.vemser.ecommerce.service;

import br.com.dbc.vemser.ecommerce.dto.produto.ProdutoDTO;
import br.com.dbc.vemser.ecommerce.dto.produto.ProdutoInputDTO;
import br.com.dbc.vemser.ecommerce.entity.Produto;
import br.com.dbc.vemser.ecommerce.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.ecommerce.exceptions.ProdutoNaoEncontradoException;
import br.com.dbc.vemser.ecommerce.repository.ProdutoRepository;
import br.com.dbc.vemser.ecommerce.utilitarias.ConverterProdutoParaDTOutil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    private ConverterProdutoParaDTOutil converterProdutoParaDTOutil;


    public List<ProdutoDTO> listar() throws BancoDeDadosException {

        return produtoRepository.listar().stream().map(converterProdutoParaDTOutil::converteProdutoParaDTO).collect(Collectors.toList());
    }

    public ProdutoDTO buscarProduto(Integer idProduto) throws BancoDeDadosException, ProdutoNaoEncontradoException {

        Produto produto = produtoRepository.buscarProduto(idProduto);

        if (produto == null) {
            throw new ProdutoNaoEncontradoException("Produto não cadastrado.");
        }

        return converterProdutoParaDTOutil.converteProdutoParaDTO(produto);

    }


    public ProdutoDTO salvar(ProdutoInputDTO produtoInputDTO) throws BancoDeDadosException {

        Produto produto = converterProdutoParaDTOutil.converteDTOparaProduto(produtoInputDTO);

        Produto produtoBuscado = produtoRepository.criarProduto(produto);

        return converterProdutoParaDTOutil.converteProdutoParaDTO(produtoBuscado);
    }

    public ProdutoDTO atualizar(Integer idProduto, ProdutoInputDTO produtoInputDTO) throws BancoDeDadosException, ProdutoNaoEncontradoException {

        Produto buscarProduto = produtoRepository.buscarProduto(idProduto);
        if (buscarProduto == null) {
            throw new ProdutoNaoEncontradoException("Produto não cadastrado.");
        }
        Produto produto = converterProdutoParaDTOutil.converteDTOparaProduto(produtoInputDTO);

        Produto produtoAtualizado = produtoRepository.atualizar(idProduto, produto);

        return converterProdutoParaDTOutil.converteProdutoParaDTO(produtoAtualizado);
    }
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