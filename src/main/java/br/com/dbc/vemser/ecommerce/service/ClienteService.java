package br.com.dbc.vemser.ecommerce.service;

import br.com.dbc.vemser.ecommerce.dto.cliente.ClienteCreateDTO;
import br.com.dbc.vemser.ecommerce.dto.cliente.ClienteDTO;
import br.com.dbc.vemser.ecommerce.entity.Cliente;
import br.com.dbc.vemser.ecommerce.repository.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private final ObjectMapper objectMapper;

    public List<ClienteDTO> list() throws Exception {
        List<Cliente> clientes = clienteRepository.list();
        List<ClienteDTO> clienteDTOS = new ArrayList<>();

        for(Cliente cliente : clientes) {
            clienteDTOS.add(converterByClienteDTO(cliente));
        }

        return clienteDTOS;
    }

    public ClienteDTO getClienteById(Integer idCliente) throws Exception {
        Cliente cliente = clienteRepository.getClienteById(idCliente);
        return converterByClienteDTO(cliente);
    }

    public ClienteDTO create(ClienteCreateDTO clienteCreateDTO) throws Exception {
        Cliente entity = converterByCliente(clienteCreateDTO);
        Cliente cliente = clienteRepository.create(entity);

        return converterByClienteDTO(cliente);
    }

    public ClienteDTO update(Integer idCliente, ClienteCreateDTO clienteCreateDTO) throws Exception{
        Cliente entity = converterByCliente(clienteCreateDTO);
        Cliente cliente = clienteRepository.update(idCliente, entity);

        return converterByClienteDTO(cliente);
    }

    public void delete(Integer idCliente) throws Exception {
        clienteRepository.delete(idCliente);
    }

    public ClienteDTO converterByClienteDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setIdCliente(cliente.getIdCliente());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setTelefone(cliente.getTelefone());
        clienteDTO.setEmail(cliente.getEmail());
        clienteDTO.setCpf(cliente.getCpf());

        return clienteDTO;
    }

    public Cliente converterByCliente(ClienteCreateDTO clienteCreateDTO) {
        Cliente entity = objectMapper.convertValue(clienteCreateDTO, Cliente.class);
        entity.setNome(clienteCreateDTO.getNome());
        entity.setTelefone(clienteCreateDTO.getTelefone());
        entity.setEmail(clienteCreateDTO.getEmail());
        entity.setCpf(clienteCreateDTO.getCpf());

        return entity;
    }
}
