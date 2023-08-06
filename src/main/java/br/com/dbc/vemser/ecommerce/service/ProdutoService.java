package br.com.dbc.vemser.ecommerce.service;

import br.com.dbc.vemser.ecommerce.dto.produto.ProdutoDTO;
import br.com.dbc.vemser.ecommerce.dto.produto.ProdutoInputDTO;
import br.com.dbc.vemser.ecommerce.entity.Produto;
import br.com.dbc.vemser.ecommerce.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.ecommerce.exceptions.ProdutoNaoEncontradoException;
import br.com.dbc.vemser.ecommerce.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    private ObjectMapper objectMapper;


    public List<ProdutoDTO> listar() throws BancoDeDadosException {

        return produtoRepository.listar().stream()
                .map(ProdutoService::converteProdutoParaDTO)
                .collect(Collectors.toList());
    }

    public ProdutoDTO buscarProduto(Integer idProduto) throws BancoDeDadosException, ProdutoNaoEncontradoException {

        Produto produto = produtoRepository.buscarProduto(idProduto);

        if (produto == null) {
            throw new ProdutoNaoEncontradoException("Produto não cadastrado.");
        }

        return converteProdutoParaDTO(produto);

    }

    private Produto converteDTOparaProduto(ProdutoInputDTO produtoInputDTO) {
        Produto produtoConvertido = objectMapper.convertValue(produtoInputDTO, Produto.class);

        produtoConvertido.setCor(produtoInputDTO.getCor());
        produtoConvertido.setModelo(produtoInputDTO.getModelo());
        produtoConvertido.setTamanho(produtoInputDTO.getTamanho());
        produtoConvertido.setSetor(produtoInputDTO.getSetor());
        produtoConvertido.setValor(produtoInputDTO.getValor());

        return produtoConvertido;
    }

    private static ProdutoDTO converteProdutoParaDTO(Produto produtoUpdate) {
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


    public ProdutoDTO salvar(ProdutoInputDTO produtoInputDTO) throws BancoDeDadosException {

        Produto produto = converteDTOparaProduto(produtoInputDTO);

        Produto produtoBuscado = produtoRepository.criarProduto(produto);

        return converteProdutoParaDTO(produtoBuscado);
    }

//    public ProdutoDTO atualizar(ProdutoInputDTO produtoInputDTO) {
//
//         produtoRepository.atualizar(produtoInputDTO);
//    }
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