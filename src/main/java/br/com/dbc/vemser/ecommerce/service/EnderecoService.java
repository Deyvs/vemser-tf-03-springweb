package br.com.dbc.vemser.ecommerce.service;

import br.com.dbc.vemser.ecommerce.dto.cliente.ClienteDTO;
import br.com.dbc.vemser.ecommerce.dto.endereco.EnderecoCreateDTO;
import br.com.dbc.vemser.ecommerce.dto.endereco.EnderecoDTO;
import br.com.dbc.vemser.ecommerce.entity.Endereco;
import br.com.dbc.vemser.ecommerce.repository.EnderecoRepository;
import br.com.dbc.vemser.ecommerce.utilitarias.NotificacaoByEmail;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final ClienteService clienteService;
    private final NotificacaoByEmail notificacaoByEmail;
    private final ObjectMapper objectMapper;

    public List<EnderecoDTO> listarEnderecos() {
        List<Endereco> enderecos = enderecoRepository.listarEnderecos();
        List<EnderecoDTO> enderecoDTOS = new ArrayList<>();

        for(Endereco endereco : enderecos) {
            enderecoDTOS.add(converterByEnderecoDTO(endereco));
        }
        return enderecoDTOS;

    }

    public EnderecoDTO getEnderecoById(Integer idEndereco) throws Exception {
        Endereco endereco = enderecoRepository.getEnderecoById(idEndereco);
        return converterByEnderecoDTO(endereco);
    }

    public List<Endereco> listarEnderecosByIdPessoa(Integer idPessoa) throws Exception {
//        pessoaRepository.getPessoaById(idPessoa);
        return enderecoRepository.listarEnderecoByIdPessoa(idPessoa);
    }

    public EnderecoDTO create(Integer idCliente, EnderecoCreateDTO enderecoCreateDTO) throws Exception {
        ClienteDTO clienteDTO = clienteService.getClienteById(idCliente);
        Endereco entity = converterByEndereco(enderecoCreateDTO);

        Endereco enderecoCreated = enderecoRepository.create(idCliente , entity);

        EnderecoDTO enderecoDTO = converterByEnderecoDTO(enderecoCreated);
        notificacaoByEmail.notificarByEmailEndereco(clienteDTO, "criado");
        return enderecoDTO;
    }

    public EnderecoDTO update(Integer idEndereco, EnderecoCreateDTO enderecoCreateDTO) throws Exception {
        Endereco entity = converterByEndereco(enderecoCreateDTO);
        entity.setIdEndereco(idEndereco);

        Endereco enderecoUpdated = enderecoRepository.update(idEndereco, entity);
        ClienteDTO clienteDTO = clienteService.getClienteById(enderecoUpdated.getIdCliente());

        EnderecoDTO enderecoDTO = converterByEnderecoDTO(enderecoUpdated);
        notificacaoByEmail.notificarByEmailEndereco(clienteDTO, "atualizado");

        return enderecoDTO;

    }

    public void delete(Integer idEndereco) throws Exception {
        Endereco endereco = enderecoRepository.getEnderecoById(idEndereco);
        ClienteDTO clienteDTO = clienteService.getClienteById(endereco.getIdCliente());
        enderecoRepository.delete(endereco);
        notificacaoByEmail.notificarByEmailEndereco(clienteDTO, "deletado");

    }

    public EnderecoDTO converterByEnderecoDTO(Endereco endereco) {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setIdEndereco(endereco.getIdEndereco());
        enderecoDTO.setIdCliente(endereco.getIdCliente());
        enderecoDTO.setTipoEndereco(endereco.getTipoEndereco());
        enderecoDTO.setNumero(endereco.getNumero());
        enderecoDTO.setLogradouro(endereco.getLogradouro());
        enderecoDTO.setComplemento(endereco.getComplemento());
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setCidade(endereco.getCidade());
        enderecoDTO.setEstado(endereco.getEstado());
        enderecoDTO.setPais(endereco.getPais());

        return enderecoDTO;
    }

    public Endereco converterByEndereco(EnderecoCreateDTO enderecoCreateDTO) {
        Endereco entity = objectMapper.convertValue(enderecoCreateDTO, Endereco.class);
        entity.setTipoEndereco(enderecoCreateDTO.getTipoEndereco());
        entity.setNumero(enderecoCreateDTO.getNumero());
        entity.setLogradouro(enderecoCreateDTO.getLogradouro());
        entity.setComplemento(enderecoCreateDTO.getComplemento());
        entity.setCep(enderecoCreateDTO.getCep());
        entity.setCidade(enderecoCreateDTO.getCidade());
        entity.setEstado(enderecoCreateDTO.getEstado());
        entity.setPais(enderecoCreateDTO.getPais());

        return entity;
    }
}