package br.com.dbc.vemser.ecommerce.doc;

import br.com.dbc.vemser.ecommerce.dto.pedido.PedidoCreateDTO;
import br.com.dbc.vemser.ecommerce.dto.pedido.PedidoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

public interface PedidoControllerDoc {


    @GetMapping
    ResponseEntity<List<PedidoDTO>> listar();


    @PostMapping("/{idPessoa}")
    ResponseEntity<PedidoDTO> criarPedido(@PathVariable("idPessoa")
                                          @Positive Integer idPessoa,
                                          @RequestBody @Valid PedidoCreateDTO idPedido);


    @DeleteMapping("/{idPedido}")
    ResponseEntity<Void> removerPedido(@PathVariable("idPedido")
                                       @Positive Integer idPedido);


    @PostMapping("/{idPedido}/carrinho/{idProduto}")
    ResponseEntity<String> adicionarProdutoAoPedido(@PathVariable Integer idPedido,
                                                           @PathVariable Integer idProduto);


    @DeleteMapping("/{idPedido}/carrinho/{idProduto}")
    ResponseEntity<String> removerProdutoDoPedido(@PathVariable Integer idPedido,
                                                         @PathVariable Integer idProduto);
}
