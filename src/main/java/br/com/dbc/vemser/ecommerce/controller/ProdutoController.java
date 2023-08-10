package br.com.dbc.vemser.ecommerce.controller;

import br.com.dbc.vemser.ecommerce.doc.ProdutoControllerDoc;
import br.com.dbc.vemser.ecommerce.dto.produto.ProdutoCreateDTO;
import br.com.dbc.vemser.ecommerce.dto.produto.ProdutoDTO;
import br.com.dbc.vemser.ecommerce.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/produto")
public class ProdutoController implements ProdutoControllerDoc {

    private final ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarProdutos() throws Exception {
        return new ResponseEntity<>(produtoService.listar(), HttpStatus.OK);
    }


    @GetMapping("/{idProduto}")
    public ResponseEntity<ProdutoDTO> buscarProduto(
            @Positive(message = "O número precisa ser positivo.")
            @PathVariable Integer idProduto) throws Exception {

        ProdutoDTO produtoDTO = produtoService.buscarProduto(idProduto);
        return new ResponseEntity<>(produtoDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> salvarProduto(
            @Valid @RequestBody
            ProdutoCreateDTO produtoCreateDTO) throws Exception {


        ProdutoDTO produtoDTO = produtoService.salvar(produtoCreateDTO);

        return new ResponseEntity<>(produtoDTO, HttpStatus.OK);
    }

    @PutMapping("/{idProduto}")
    public ResponseEntity<ProdutoDTO> atualizarProduto(
            @Positive(message = "O número precisa ser positivo.")
            @PathVariable Integer idProduto,
            @Valid @RequestBody
            ProdutoCreateDTO produtoCreateDTO) throws Exception {


        ProdutoDTO produtoDTO = produtoService.atualizar(idProduto, produtoCreateDTO);

        return new ResponseEntity<>(produtoDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{idProduto}")
    public ResponseEntity<Void> delete(@PathVariable Integer idProduto) throws Exception {
        produtoService.deletar(idProduto);
        return ResponseEntity.ok().build();
    }

}