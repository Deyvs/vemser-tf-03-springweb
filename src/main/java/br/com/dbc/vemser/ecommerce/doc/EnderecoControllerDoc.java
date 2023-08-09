package br.com.dbc.vemser.ecommerce.doc;

import br.com.dbc.vemser.ecommerce.dto.endereco.EnderecoCreateDTO;
import br.com.dbc.vemser.ecommerce.dto.endereco.EnderecoDTO;
import br.com.dbc.vemser.ecommerce.entity.Endereco;
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
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    ResponseEntity<List<EnderecoDTO>> listarEnderecos();

    @Operation(summary = "Listar endereço por ID", description = "Lista o endereço selecionado pelo ID no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna endereço solicitado pelo ID"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idEndereco}")
    ResponseEntity<EnderecoDTO> getEnderecoById(@Positive(message = "id deve ser maior que zero")
                                                @PathVariable("idEndereco") Integer idEndereco);

    @Operation(summary = "Listar endereço por ID de pessoa", description = "Lista endereço pelo ID da pessoa cadastrada no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o endereço da pessoa selecionada pelo ID"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idPessoa}/pessoa")
    ResponseEntity<List<Endereco>> listarEnderecosByIdPessoa(@Positive(message = "id deve ser maior que zero")
                                                             @PathVariable("idPessoa") Integer idPessoa);

    @Operation(summary = "Criar um novo endereço", description = "Cria um novo endereço informando a pessoa destinada pelo ID no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o novo endereço cadastrado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/{idPessoa}")
    ResponseEntity<EnderecoDTO> create(@Positive(message = "id deve ser maior que zero")
                                       @PathVariable("idPessoa") Integer idPessoa,
                                       @Valid @RequestBody EnderecoCreateDTO enderecoCreateDTO);

    @Operation(summary = "Atualizar endereço por ID", description = "Atualiza o endereço selecionado pelo ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o endereço ataualizado no banco"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idEndereco}")
    ResponseEntity<EnderecoDTO> update(@Positive(message = "id deve ser maior que zero")
                                              @PathVariable("idEndereco") Integer idEndereco,
                                              @Valid @RequestBody EnderecoCreateDTO enderecoCreateDTO);

    @Operation(summary = "Deletar endereço por ID", description = "Deleta o endereço selecionado pelo ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna Ok"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idEndereco}")
    ResponseEntity<Void> delete(@Positive(message = "id deve ser maior que zero")
                                @PathVariable("idEndereco") Integer idEndereco);

}
