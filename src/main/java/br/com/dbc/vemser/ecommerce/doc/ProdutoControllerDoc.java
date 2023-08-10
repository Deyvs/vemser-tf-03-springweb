package br.com.dbc.vemser.ecommerce.doc;


import br.com.dbc.vemser.ecommerce.dto.produto.ProdutoCreateDTO;
import br.com.dbc.vemser.ecommerce.dto.produto.ProdutoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

public interface ProdutoControllerDoc {

    @Operation(summary = "Listar todos produtos", description = "Lista todos produtos do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna lista de todos produtos"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Página não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    ResponseEntity<List<ProdutoDTO>> listarProdutos() throws Exception;

    @Operation(summary = "Listar produto por ID", description = "Lista produto por ID no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna produto listado pelo ID"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "ID não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idProduto}")
    ResponseEntity<ProdutoDTO> buscarProduto(@Positive(message = "O número precisa ser positivo.")
                                             @PathVariable Integer idProduto) throws Exception;

    @Operation(summary = "Criar novo produto", description = "Cria e salva o novo produto no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna produto criado"),
                    @ApiResponse(responseCode = "201", description = "Retorna o produto criado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "ID não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    ResponseEntity<ProdutoDTO> salvarProduto(@Valid @RequestBody ProdutoCreateDTO produtoCreateDTO) throws Exception;

    @Operation(summary = "Atualizar produto por ID", description = "Atualiza produto informando o ID no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna produto atualizado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "ID não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idProduto}")
    ResponseEntity<ProdutoDTO> atualizarProduto(@Positive(message = "O número precisa ser positivo.")
                                                @PathVariable Integer idProduto,
                                                @Valid @RequestBody ProdutoCreateDTO produtoCreateDTO) throws Exception;

    @Operation(summary = "Deletar produto por ID", description = "Deleta produto informando o ID no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna Ok"),
                    @ApiResponse(responseCode = "204", description = "Retorna produto deletado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "ID não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idProduto}")
    ResponseEntity<Void> delete(@PathVariable Integer idProduto) throws Exception;
}