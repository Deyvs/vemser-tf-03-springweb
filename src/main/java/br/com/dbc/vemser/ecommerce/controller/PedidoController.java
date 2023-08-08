package br.com.dbc.vemser.ecommerce.controller;

import br.com.dbc.vemser.ecommerce.dto.pedido.PedidoOutputDTO;
import br.com.dbc.vemser.ecommerce.service.PedidoService;
import br.com.dbc.vemser.ecommerce.service.PedidoXProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<PedidoOutputDTO> criarPedido(@PathVariable ("idPessoa") @Positive Integer idPessoa) throws Exception{

        return new ResponseEntity<>(pedidoService.adicionar(idPessoa), HttpStatus.OK);
    }
    @DeleteMapping("/{idPedido}")
    public ResponseEntity<Void> delete(@PathVariable("idPedido") @Positive Integer idPedido) throws Exception{

         pedidoService.deletePedido(idPedido);
         return ResponseEntity.ok().build();
    }


    // Implementação dos métodos da classe PedidoXProdutoService

    @PostMapping("/{idPedido}/adicionar-produto/{idProduto}")
    public ResponseEntity<String> adicionarProdutoAoPedido(@PathVariable Integer idPedido,
                                                           @PathVariable Integer idProduto) throws Exception {
        try {
            pedidoXProdutoService.adicionarProdutoAoPedido(idPedido, idProduto);
            return ResponseEntity.ok("Seu produto foi adicionado ao pedido com sucesso!");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao adicionar seu produto ao pedido.");
        }
    }

    @GetMapping("/atualizar/{idPedido}/{idProduto}")
    public ResponseEntity<Void> atualizarValor(@PathVariable ("idPedido") Integer idPedido,
                                               @PathVariable("idProduto") Integer idProduto) throws Exception{
        pedidoService.atualizarValorPedido(idPedido,idProduto);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("{idPedido}/remover-produto/{idProduto}")
    public ResponseEntity<String> removerProdutoDoPedido(@PathVariable Integer idPedido,
                                                         @PathVariable Integer idProduto) throws Exception {
        try {
            pedidoXProdutoService.removerProdutoDoPedido(idPedido, idProduto);
            return ResponseEntity.ok("Seu produto foi removido do pedido com sucesso!");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao remover seu produto do pedido.");
        }
    }

    @DeleteMapping("/{idPedido}/remover-todos-produtos")
    public ResponseEntity<String> removerTodosProdutosDoPedido(@PathVariable Integer idPedido) {
        try {
            pedidoXProdutoService.removerTodosProdutosDoPedido(idPedido);
            return ResponseEntity.ok("Todos os produtos foram removidos do seu pedido com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao remover todos os seus produtos do pedido.");
        }
    }


}
