package br.com.dbc.vemser.ecommerce.controller;

import br.com.dbc.vemser.ecommerce.doc.EnderecoControllerDoc;
import br.com.dbc.vemser.ecommerce.dto.endereco.EnderecoCreateDTO;
import br.com.dbc.vemser.ecommerce.dto.endereco.EnderecoDTO;
import br.com.dbc.vemser.ecommerce.service.EnderecoService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@Validated
@RestController
@RequestMapping("/endereco")
public class EnderecoController implements EnderecoControllerDoc {

    private final EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> listarEnderecos() throws Exception {
        return new ResponseEntity<>(enderecoService.listarEnderecos(), HttpStatus.OK);
    }

    @GetMapping("/{idEndereco}")
    public ResponseEntity<EnderecoDTO> getEnderecoById(@Positive(message = "id deve ser maior que zero") @PathVariable("idEndereco") Integer idEndereco) throws Exception {
        return new ResponseEntity<>(enderecoService.getEnderecoById(idEndereco), HttpStatus.OK);
    }
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<EnderecoDTO>> listarEnderecoByIdCliente(@Positive(message = "id deve ser maior que zero") @PathVariable("idCliente") Integer idCliente) throws Exception {
        return new ResponseEntity<>(enderecoService.listarEnderecoByIdCliente(idCliente), HttpStatus.OK);
    }

    @PostMapping("/{idCliente}")
    public ResponseEntity<EnderecoDTO> create(@Positive(message = "id deve ser maior que zero") @PathVariable("idCliente") Integer idCliente,
                                              @Valid @RequestBody EnderecoCreateDTO enderecoCreateDTO) throws Exception {
        return new ResponseEntity<EnderecoDTO>(enderecoService.create(idCliente , enderecoCreateDTO), HttpStatus.OK);
    }

    @PutMapping("/{idEndereco}")
    public ResponseEntity<EnderecoDTO> update(@Positive(message = "id deve ser maior que zero") @PathVariable("idEndereco") Integer idEndereco, @Valid @RequestBody EnderecoCreateDTO enderecoCreateDTO) throws Exception {
        return new ResponseEntity<>(enderecoService.update(idEndereco, enderecoCreateDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{idEndereco}")
    public ResponseEntity<Void> delete(@Positive(message = "id deve ser maior que zero") @PathVariable("idEndereco") Integer idEndereco) throws Exception {
        enderecoService.delete(idEndereco);
        return ResponseEntity.ok().build();
    }
}
