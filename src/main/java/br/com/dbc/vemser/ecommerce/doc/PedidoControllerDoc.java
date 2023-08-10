package br.com.dbc.vemser.ecommerce.doc;


import br.com.dbc.vemser.ecommerce.dto.pedido.PedidoCreateDTO;
import br.com.dbc.vemser.ecommerce.dto.pedido.PedidoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

public interface PedidoControllerDoc {

    @Operation(summary = "Listar todos pedidos", description = "Lista de pedidos cadastrados no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna lista de pedidos"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Página não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    ResponseEntity<List<PedidoDTO>> listar() throws Exception;

    @Operation(summary = "Buscar pedido por ID", description = "Buscar pedidos por ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna um pedido"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Página não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idPedido}")
    ResponseEntity<PedidoDTO> buscarByIdPedido(@PathVariable("idPedido")@Positive Integer idPedido) throws Exception;


    @Operation(summary = "Criar pedido por ID", description = "Cria pedido selecionando o cliente destinado pelo ID no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retornar pedido criado com ID"),
                    @ApiResponse(responseCode = "201", description = "Retorna o pedido criado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Página não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/{idCliente}")
    ResponseEntity<PedidoDTO> criarPedido(@PathVariable("idCliente")
                                          @Positive Integer idCliente,
                                          @RequestBody @Valid PedidoCreateDTO idPedido) throws Exception;

    @Operation(summary = "Atualizar pedido por ID", description = "Atualiza pedido selecionando pelo ID no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retornar pedido atualizado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "ID não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idPedido}")
    ResponseEntity<PedidoDTO> atualizarStatusDoPedido(@PathVariable("idPedido")
                                          @Positive Integer idPedido) throws Exception;

    @Operation(summary = "Criar carrinho informando ID de pedido e ID do protudo selecionado", description = "Adiciona um produto ao pedido informando o ID do pedido e ID do produto que deseja adicionar ao carrinho")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna pedido com produto criado no banco"),
                    @ApiResponse(responseCode = "201", description = "Retorna o pedido criado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Página não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/{idPedido}/carrinho/{idProduto}")
    ResponseEntity<PedidoDTO> adicionarProdutoAoPedido(@PathVariable Integer idPedido,
                                                    @PathVariable Integer idProduto) throws Exception;

    @Operation(summary = "Deletar pedido por ID", description = "Deleta pedido informado pelo ID no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna Ok"),
                    @ApiResponse(responseCode = "204", description = "Retorna pedido deletado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "ID não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idPedido}")
    ResponseEntity<Void> removerPedido(@PathVariable("idPedido")
                                       @Positive Integer idPedido) throws Exception;

    @Operation(summary = "Deletar produto de um pedido por ID", description = "Remove o produto do carrinho informando o ID do pedido e ID do produto que deseja remover do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna Ok"),
                    @ApiResponse(responseCode = "204", description = "Retorna pedido deletado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "ID não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idPedido}/carrinho/{idProduto}")
    ResponseEntity<PedidoDTO> removerProdutoDoPedido(@PathVariable Integer idPedido,
                                                         @PathVariable Integer idProduto) throws Exception;
}
