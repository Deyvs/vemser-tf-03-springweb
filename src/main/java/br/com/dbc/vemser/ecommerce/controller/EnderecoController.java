package br.com.dbc.vemser.ecommerce.controller;

import br.com.dbc.vemser.ecommerce.doc.EnderecoControllerDoc;
import br.com.dbc.vemser.ecommerce.dto.endereco.EnderecoCreateDTO;
import br.com.dbc.vemser.ecommerce.dto.endereco.EnderecoDTO;
import br.com.dbc.vemser.ecommerce.entity.Endereco;
import br.com.dbc.vemser.ecommerce.exceptions.BancoDeDadosException;
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
    public ResponseEntity<List<EnderecoDTO>> listarEnderecos() throws BancoDeDadosException {
        return new ResponseEntity<>(enderecoService.listarEnderecos(), HttpStatus.OK);
    }

    @GetMapping("/{idEndereco}")
    public ResponseEntity<EnderecoDTO> getEnderecoById(@Positive(message = "id deve ser maior que zero") @PathVariable("idEndereco") Integer idEndereco) throws Exception {
        return new ResponseEntity<>(enderecoService.getEnderecoById(idEndereco), HttpStatus.OK);
    }
    @GetMapping("/{idPessoa}/pessoa")
    public ResponseEntity<EnderecoDTO> listarEnderecosByIdPessoa(@Positive(message = "id deve ser maior que zero") @PathVariable("idPessoa") Integer idPessoa) throws Exception {
        return new ResponseEntity<>(enderecoService.listarEnderecosByIdPessoa(idPessoa), HttpStatus.OK);
    }

    @PostMapping("/{idPessoa}")
    public ResponseEntity<EnderecoDTO> create(@Positive(message = "id deve ser maior que zero") @PathVariable("idPessoa") Integer idPessoa,
                                              @Valid @RequestBody EnderecoCreateDTO enderecoCreateDTO) throws Exception {
        return new ResponseEntity<EnderecoDTO>(enderecoService.create(idPessoa , enderecoCreateDTO), HttpStatus.OK);
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
