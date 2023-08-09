package br.com.dbc.vemser.ecommerce.doc;

import br.com.dbc.vemser.ecommerce.dto.cliente.ClienteCreateDTO;
import br.com.dbc.vemser.ecommerce.dto.cliente.ClienteDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ClienteControllerDoc {

    @Operation(summary = "Listar todos clientes", description = "Lista todos os clientes do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de clientes"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    ResponseEntity<List<ClienteDTO>> List();


    @GetMapping("/{idCliente}")
    ResponseEntity<ClienteDTO> getClienteById(@PathVariable Integer idCliente);


    @PostMapping
    ResponseEntity<ClienteDTO> create(@RequestBody ClienteCreateDTO cliente);


    @PutMapping("/{idCliente}")
    ResponseEntity<ClienteDTO> update(@PathVariable Integer idCliente,
                                             @RequestBody ClienteCreateDTO cliente);


    @DeleteMapping("/{idCliente}")
    ResponseEntity<Void> delete(@PathVariable Integer idCliente);

}
