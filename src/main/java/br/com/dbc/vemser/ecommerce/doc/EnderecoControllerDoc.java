package br.com.dbc.vemser.ecommerce.doc;


import br.com.dbc.vemser.ecommerce.dto.endereco.EnderecoCreateDTO;
import br.com.dbc.vemser.ecommerce.dto.endereco.EnderecoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

public interface EnderecoControllerDoc {

    @Operation(summary = "Listar todos endereços", description = "Lista todos endereços no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna lista de endereços"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Página não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    ResponseEntity<List<EnderecoDTO>> listarEnderecos() throws Exception;

    @Operation(summary = "Listar endereço por ID", description = "Lista o endereço selecionado pelo ID no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna endereço solicitado pelo ID"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "ID não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idEndereco}")
    ResponseEntity<EnderecoDTO> getEnderecoById(@Positive(message = "id deve ser maior que zero")
                                                @PathVariable("idEndereco") Integer idEndereco) throws Exception;

    @Operation(summary = "Listar endereço por ID do cliente", description = "Lista endereço pelo ID do cliente cadastrado no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o endereço do cliente selecionada pelo ID"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "ID não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/cliente/{idCliente}")
    ResponseEntity<List<EnderecoDTO>> listarEnderecoByIdCliente(@Positive(message = "id deve ser maior que zero")
                                                             @PathVariable("idCliente") Integer idCliente) throws Exception;

    @Operation(summary = "Criar um novo endereço", description = "Cria um novo endereço informando o cliente destinada pelo ID no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o novo endereço cadastrado"),
                    @ApiResponse(responseCode = "201", description = "Retorna o endereço criado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "ID não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/{idCliente}")
    ResponseEntity<EnderecoDTO> create(@Positive(message = "id deve ser maior que zero")
                                       @PathVariable("idPessoa") Integer idCliente,
                                       @Valid @RequestBody EnderecoCreateDTO enderecoCreateDTO) throws Exception;

    @Operation(summary = "Atualizar endereço por ID", description = "Atualiza o endereço selecionado pelo ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o endereço ataualizado no banco"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "ID não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idEndereco}")
    ResponseEntity<EnderecoDTO> update(@Positive(message = "id deve ser maior que zero")
                                              @PathVariable("idEndereco") Integer idEndereco,
                                              @Valid @RequestBody EnderecoCreateDTO enderecoCreateDTO) throws Exception;

    @Operation(summary = "Deletar endereço por ID", description = "Deleta o endereço selecionado pelo ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna Ok"),
                    @ApiResponse(responseCode = "204", description = "Retorna endereço deletado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "ID não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idEndereco}")
    ResponseEntity<Void> delete(@Positive(message = "id deve ser maior que zero")
                                @PathVariable("idEndereco") Integer idEndereco) throws Exception;

}
