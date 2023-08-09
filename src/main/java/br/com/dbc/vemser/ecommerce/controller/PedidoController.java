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

    @PostMapping("/{idPedido}/carrinho/{idProduto}") //  add prod no pedido
    public ResponseEntity<PedidoOutputDTO> adicionarProdutoAoPedido(@PathVariable Integer idPedido,
                                                           @PathVariable Integer idProduto) throws Exception {
        return new ResponseEntity<>(pedidoService.adicionarProdutoaoPedido(idPedido,idProduto),HttpStatus.OK);
    }

    @DeleteMapping("/{idPedido}/carrinho/{idProduto}")
    public ResponseEntity<PedidoOutputDTO> removerProdutoDoPedido(@PathVariable Integer idPedido,
                                                         @PathVariable Integer idProduto) throws Exception {
       return new ResponseEntity<>(pedidoService.RemoverProdutoDoPedido(idPedido,idProduto),HttpStatus.OK);
    }

}
