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
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    ResponseEntity<List<PedidoDTO>> listar() throws Exception;

    @Operation(summary = "Criar pedido por ID", description = "Cria pedido selecionando a pessoa destinada pelo ID no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retornar pedido criado com ID"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/{idPessoa}")
    ResponseEntity<PedidoDTO> criarPedido(@PathVariable("idPessoa")
                                          @Positive Integer idPessoa,
                                          @RequestBody @Valid PedidoCreateDTO idPedido) throws Exception;

    @Operation(summary = "Criar carrinho informando ID de pedido e ID do protudo selecionado", description = "Adiciona um produto ao pedido informando o ID do pedido e ID do produto que deseja adicionar ao carrinho")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna pedido com produto criado no banco"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/{idPedido}/carrinho/{idProduto}")
    ResponseEntity<String> adicionarProdutoAoPedido(@PathVariable Integer idPedido,
                                                    @PathVariable Integer idProduto) throws Exception;

    @Operation(summary = "Deletar pedido por ID", description = "Deleta pedido informado pelo ID no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna Ok"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
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
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idPedido}/carrinho/{idProduto}")
    ResponseEntity<String> removerProdutoDoPedido(@PathVariable Integer idPedido,
                                                         @PathVariable Integer idProduto) throws Exception;
}
