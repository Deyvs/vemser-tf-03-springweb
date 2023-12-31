package br.com.dbc.vemser.ecommerce.controller;

import br.com.dbc.vemser.ecommerce.doc.PedidoControllerDoc;

import br.com.dbc.vemser.ecommerce.dto.pedido.PedidoCreateDTO;
import br.com.dbc.vemser.ecommerce.dto.pedido.PedidoDTO;
import br.com.dbc.vemser.ecommerce.service.PedidoService;
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
@RequestMapping("/pedido")
public class PedidoController implements PedidoControllerDoc {

    private final PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> listar() throws Exception {
        return new ResponseEntity<>(pedidoService.listar(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PedidoDTO> buscarByIdPedido(Integer idPedido) throws Exception {
        return new ResponseEntity<>(pedidoService.buscarByIdPedido(idPedido),HttpStatus.OK);
    }

    @PostMapping("/{idCliente}")
    public ResponseEntity<PedidoDTO> criarPedido(@PathVariable ("idCliente") @Positive Integer idCliente,
                                                 @RequestBody @Valid PedidoCreateDTO idPedido) throws Exception {
        return new ResponseEntity<>(pedidoService.criarPedido(idCliente,idPedido), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PedidoDTO> atualizarStatusDoPedido(Integer idPedido) throws Exception {
        return new ResponseEntity<>(pedidoService.atualizarStatusPedido(idPedido),HttpStatus.OK);
    }

    @DeleteMapping("/{idPedido}")
    public ResponseEntity<Void> removerPedido(@PathVariable("idPedido") @Positive Integer idPedido) throws Exception {

         pedidoService.deletePedido(idPedido);
         return ResponseEntity.ok().build();
    }

    // Implementação dos métodos da classe PedidoXProdutoService

    @PostMapping("/{idPedido}/carrinho/{idProduto}") //  add prod no pedido
    public ResponseEntity<PedidoDTO> adicionarProdutoAoPedido(@PathVariable Integer idPedido,
                                                           @PathVariable Integer idProduto) throws Exception {
        return new ResponseEntity<>(pedidoService.adicionarProdutoaoPedido(idPedido,idProduto),HttpStatus.OK);
    }

    @DeleteMapping("/{idPedido}/carrinho/{idProduto}")
    public ResponseEntity<PedidoDTO> removerProdutoDoPedido(@PathVariable Integer idPedido,
                                                         @PathVariable Integer idProduto) throws Exception {
       return new ResponseEntity<>(pedidoService.RemoverProdutoDoPedido(idPedido,idProduto),HttpStatus.OK);
    }

}
