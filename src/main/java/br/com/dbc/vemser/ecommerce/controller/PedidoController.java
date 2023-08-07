package br.com.dbc.vemser.ecommerce.controller;

import br.com.dbc.vemser.ecommerce.dto.PedidoOutputDTO;
import br.com.dbc.vemser.ecommerce.service.PedidoService;
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

}
