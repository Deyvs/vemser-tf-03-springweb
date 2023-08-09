package br.com.dbc.vemser.ecommerce.doc;

import br.com.dbc.vemser.ecommerce.dto.produto.ProdutoCreateDTO;
import br.com.dbc.vemser.ecommerce.dto.produto.ProdutoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

public interface ProdutoControllerDoc {


    @GetMapping
    ResponseEntity<List<ProdutoDTO>> listarProdutos();


    @GetMapping("/{idProduto}")
    ResponseEntity<ProdutoDTO> buscarProduto(@Positive(message = "O número precisa ser positivo.")
                                             @PathVariable Integer idProduto);


    @PostMapping
    ResponseEntity<ProdutoDTO> salvarProduto(@Valid @RequestBody ProdutoCreateDTO produtoCreateDTO);

    @PutMapping("/{idProduto}")
    ResponseEntity<ProdutoDTO> atualizarProduto(@Positive(message = "O número precisa ser positivo.")
                                                @PathVariable Integer idProduto,
                                                @Valid @RequestBody ProdutoCreateDTO produtoCreateDTO);


    @DeleteMapping("/{idProduto}")
    ResponseEntity<Void> delete(@PathVariable Integer idProduto);
}
