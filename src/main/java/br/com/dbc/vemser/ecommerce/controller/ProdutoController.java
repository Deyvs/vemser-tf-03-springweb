package br.com.dbc.vemser.ecommerce.controller;

import br.com.dbc.vemser.ecommerce.dto.produto.ProdutoDTO;
import br.com.dbc.vemser.ecommerce.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.ecommerce.service.ProdutoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/produto")
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarProdutos() throws BancoDeDadosException {
//        log.info("listando produtos");
        return new ResponseEntity<>(produtoService.listar(), HttpStatus.OK);
    }


    @GetMapping("/{idProduto}")
    public ResponseEntity<ProdutoDTO> buscarProduto(
            @Positive(message = "O número precisa ser positivo.")
            @PathVariable Integer idProduto) throws BancoDeDadosException {
//        log.info("buscar produtos");
        ProdutoDTO produtoDTO = produtoService.buscarProduto(idProduto);
        return new ResponseEntity<>(produtoDTO, HttpStatus.OK);
    }
}
