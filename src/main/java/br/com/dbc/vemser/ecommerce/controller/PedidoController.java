package br.com.dbc.vemser.ecommerce.controller;

import br.com.dbc.vemser.ecommerce.dto.pedido.PedidoCreateDTO;
import br.com.dbc.vemser.ecommerce.dto.pedido.PedidoOutputDTO;
import br.com.dbc.vemser.ecommerce.service.PedidoService;
import br.com.dbc.vemser.ecommerce.service.PedidoXProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/pedido")
@Validated
public class PedidoController {

    private final PedidoService pedidoService;
    private final PedidoXProdutoService pedidoXProdutoService;

    @GetMapping
    public ResponseEntity<List<PedidoOutputDTO>> listar() throws Exception{
        return new ResponseEntity<>(pedidoService.listar(),HttpStatus.OK);
    }
//    @PutMapping("/{idPedido}/{idProduto}")
//    public ResponseEntity<PedidoOutputDTO> atualizarValor(@PathVariable ("idPedido")  @Positive Integer idPedido,
//                                                         @PathVariable @Positive Integer idProduto) throws Exception{
//        return new ResponseEntity<>(pedidoService.atualizarValorPedido(idPedido,idProduto),HttpStatus.OK);
//    }
    @PostMapping("/{idPessoa}")
    public ResponseEntity<PedidoOutputDTO> criarPedido(@PathVariable ("idPessoa") @Positive Integer idPessoa,
                                                       @RequestBody @Valid PedidoCreateDTO idPedido) throws Exception{
        return new ResponseEntity<>(pedidoService.criarPedido(idPessoa,idPedido), HttpStatus.OK);
    }
  
    @DeleteMapping("/{idPedido}")
    public ResponseEntity<Void> removerPedido(@PathVariable("idPedido") @Positive Integer idPedido) throws Exception{

         pedidoService.deletePedido(idPedido);
         return ResponseEntity.ok().build();
    }

    // Implementação dos métodos da classe PedidoXProdutoService

    @PostMapping("/{idPedido}/carrinho/{idProduto}")
    public ResponseEntity<String> adicionarProdutoAoPedido(@PathVariable Integer idPedido,
                                                           @PathVariable Integer idProduto) throws Exception {
        try {
            pedidoXProdutoService.adicionarProdutoAoPedido(idPedido, idProduto);
            return ResponseEntity.ok("Seu produto foi adicionado ao pedido com sucesso!");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao adicionar seu produto ao pedido.");
        }
    }

    @DeleteMapping("/{idPedido}/carrinho/{idProduto}")
    public ResponseEntity<String> removerProdutoDoPedido(@PathVariable Integer idPedido,
                                                         @PathVariable Integer idProduto) throws Exception {
        try {
            pedidoXProdutoService.removerProdutoDoPedido(idPedido, idProduto);
            return ResponseEntity.ok("Seu produto foi removido do pedido com sucesso!");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao remover seu produto do pedido.");
        }
    }

}
