package br.com.dbc.vemser.ecommerce.controller;

import br.com.dbc.vemser.ecommerce.dto.ClienteCreateDTO;
import br.com.dbc.vemser.ecommerce.dto.ClienteDTO;
import br.com.dbc.vemser.ecommerce.entity.Cliente;
import br.com.dbc.vemser.ecommerce.service.ClienteService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@Validated
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> List() throws Exception {
        return new ResponseEntity<>(clienteService.list(), HttpStatus.OK);
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer idCliente) throws Exception {
        return new ResponseEntity<>(clienteService.getClienteById(idCliente), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@RequestBody ClienteCreateDTO cliente) throws Exception{
        return new ResponseEntity<ClienteDTO>(clienteService.create(cliente), HttpStatus.OK);
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer idCliente, @RequestBody ClienteCreateDTO cliente) throws Exception{
        return new ResponseEntity<ClienteDTO>(clienteService.update(idCliente, cliente), HttpStatus.OK);
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Void> delete(@PathVariable Integer idCliente) throws Exception{
        clienteService.delete(idCliente);
        return ResponseEntity.ok().build();
    }
}
