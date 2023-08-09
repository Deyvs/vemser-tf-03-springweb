package br.com.dbc.vemser.ecommerce.doc;

import br.com.dbc.vemser.ecommerce.dto.endereco.EnderecoCreateDTO;
import br.com.dbc.vemser.ecommerce.dto.endereco.EnderecoDTO;
import br.com.dbc.vemser.ecommerce.entity.Endereco;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

public interface EnderecoControllerDoc {

    @GetMapping
    ResponseEntity<List<EnderecoDTO>> listarEnderecos();

    @GetMapping("/{idEndereco}")
    ResponseEntity<EnderecoDTO> getEnderecoById(@Positive(message = "id deve ser maior que zero")
                                                @PathVariable("idEndereco") Integer idEndereco);


    @GetMapping("/{idPessoa}/pessoa")
    ResponseEntity<List<Endereco>> listarEnderecosByIdPessoa(@Positive(message = "id deve ser maior que zero")
                                                             @PathVariable("idPessoa") Integer idPessoa);


    @PostMapping("/{idPessoa}")
    ResponseEntity<EnderecoDTO> create(@Positive(message = "id deve ser maior que zero")
                                       @PathVariable("idPessoa") Integer idPessoa,
                                       @Valid @RequestBody EnderecoCreateDTO enderecoCreateDTO);


    @PutMapping("/{idEndereco}")
    ResponseEntity<EnderecoDTO> update(@Positive(message = "id deve ser maior que zero")
                                              @PathVariable("idEndereco") Integer idEndereco,
                                              @Valid @RequestBody EnderecoCreateDTO enderecoCreateDTO);


    @DeleteMapping("/{idEndereco}")
    ResponseEntity<Void> delete(@Positive(message = "id deve ser maior que zero")
                                @PathVariable("idEndereco") Integer idEndereco);

}
