package br.com.dbc.vemser.ecommerce.controller;

import br.com.dbc.vemser.ecommerce.entity.Produto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
//        log.info("listando produtos");
        return new ResponseEntity<>(null, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<Produto> buscarProduto() {
//        log.info("buscar produtos");
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
