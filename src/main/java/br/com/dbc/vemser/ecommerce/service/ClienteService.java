package br.com.dbc.vemser.ecommerce.service;

import br.com.dbc.vemser.ecommerce.dto.ClienteCreateDTO;
import br.com.dbc.vemser.ecommerce.dto.ClienteDTO;
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
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setIdCliente(cliente.getIdCliente());
            clienteDTO.setNome(cliente.getNome());
            clienteDTO.setTelefone(cliente.getTelefone());
            clienteDTO.setEmail(cliente.getEmail());
            clienteDTO.setCpf(cliente.getCpf());
            clienteDTOS.add(clienteDTO);
        }
        return clienteDTOS;
    }

    public ClienteDTO getClienteById(Integer idCliente) throws Exception {
        Cliente cliente = clienteRepository.getClienteById(idCliente);
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setIdCliente(cliente.getIdCliente());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setTelefone(cliente.getTelefone());
        clienteDTO.setEmail(cliente.getEmail());
        clienteDTO.setCpf(cliente.getCpf());
        return clienteDTO;
    }

    public ClienteDTO create(ClienteCreateDTO cliente) throws Exception {
//        System.out.println(cliente);
        Cliente entity = objectMapper.convertValue(cliente, Cliente.class);
        entity.setNome(cliente.getNome());
        entity.setTelefone(cliente.getTelefone());
        entity.setEmail(cliente.getEmail());
        entity.setCpf(cliente.getCpf());

        Cliente clienteCreated = clienteRepository.create(entity);

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setIdCliente(clienteCreated.getIdCliente());
        clienteDTO.setNome(clienteCreated.getNome());
        clienteDTO.setTelefone(clienteCreated.getTelefone());
        clienteDTO.setEmail(clienteCreated.getEmail());
        clienteDTO.setCpf(clienteCreated.getCpf());
        return clienteDTO;
    }

    public ClienteDTO update(Integer idCliente, ClienteCreateDTO cliente) throws Exception{

        Cliente entity = objectMapper.convertValue(cliente, Cliente.class);
        entity.setNome(cliente.getNome());
        entity.setTelefone(cliente.getTelefone());
        entity.setEmail(cliente.getEmail());
        entity.setCpf(cliente.getCpf());

        Cliente clienteCreated = clienteRepository.update(idCliente, entity);

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setIdCliente(clienteCreated.getIdCliente());
        clienteDTO.setNome(clienteCreated.getNome());
        clienteDTO.setTelefone(clienteCreated.getTelefone());
        clienteDTO.setEmail(clienteCreated.getEmail());
        clienteDTO.setCpf(clienteCreated.getCpf());

        return clienteDTO;
    }


    public void delete(Integer idCliente) throws Exception {
        clienteRepository.delete(idCliente);
    }
}
