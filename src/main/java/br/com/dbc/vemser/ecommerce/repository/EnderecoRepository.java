package br.com.dbc.vemser.ecommerce.repository;

import br.com.dbc.vemser.ecommerce.entity.Endereco;
import br.com.dbc.vemser.ecommerce.entity.enums.TipoEndereco;
import br.com.dbc.vemser.ecommerce.exceptions.EscolherOpcaoException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class EnderecoRepository {
    private static List<Endereco> listaEnderecos = new ArrayList<>();

    private final AtomicInteger COUNTER = new AtomicInteger();

    public EnderecoRepository() {
        listaEnderecos.add(new Endereco(COUNTER.incrementAndGet(), 1, TipoEndereco.COMERCIAL, "Rua das Margaridas", 123, "Sala 201", "12345-698", "Rio de Janeiro", "RJ", "Brasil"));
        listaEnderecos.add(new Endereco(COUNTER.incrementAndGet(), 1, TipoEndereco.COMERCIAL, "Rua das Bromélias", 231, "Travessa B", "12345-698", "Rio de Janeiro", "RJ", "Brasil"));
        listaEnderecos.add(new Endereco(COUNTER.incrementAndGet(), 3, TipoEndereco.COMERCIAL, "Avenida Aparecida", 342, "Sala 2001", "12345-698", "Rio de Janeiro", "RJ", "Brasil"));
        listaEnderecos.add(new Endereco(COUNTER.incrementAndGet(), 4, TipoEndereco.COMERCIAL, "Rua das Couves", 547, "Sala 2001", "12345-698", "Rio de Janeiro", "RJ", "Brasil"));
        listaEnderecos.add(new Endereco(COUNTER.incrementAndGet(), 2, TipoEndereco.COMERCIAL, "Avenida do Sol", 159, "Sala 2010", "12345-698", "Rio de Janeiro", "RJ", "Brasil"));
    }
    public List<Endereco> listarEnderecos() {
        return listaEnderecos;
    };

    public Endereco getEnderecoById(Integer idEndereco) throws EscolherOpcaoException {
        Endereco enderecoRecuperado = listarEnderecos().stream()
                .filter(endereco -> endereco.getIdEndereco().equals(idEndereco))
                .findFirst()
                .orElseThrow(() -> new EscolherOpcaoException("Endereco não encontrado!"));
        return enderecoRecuperado;
    }

    public List<Endereco> listarEnderecoByIdPessoa(Integer idCliente) {
        List<Endereco> enderecos = new ArrayList<>();
        for(Endereco endereco : listarEnderecos()) {
            if(idCliente == endereco.getIdCliente()) {
                enderecos.add(endereco);
            }
        }
        return enderecos;
    }

    public Endereco create(Integer idCliente, Endereco endereco) throws EscolherOpcaoException {
        endereco.setIdEndereco(COUNTER.incrementAndGet());
        endereco.setIdCliente(idCliente);
        listaEnderecos.add(endereco);
        return endereco;
    }


    public Endereco update(Integer idEndereco, Endereco enderecoByUpdate) throws EscolherOpcaoException {
        Endereco endereco = getEnderecoById(idEndereco);
        endereco.setTipoEndereco(enderecoByUpdate.getTipoEndereco());
        endereco.setNumero(enderecoByUpdate.getNumero());
        endereco.setLogradouro(enderecoByUpdate.getLogradouro());
        endereco.setComplemento(enderecoByUpdate.getComplemento());
        endereco.setCep(enderecoByUpdate.getCep());
        endereco.setCidade(enderecoByUpdate.getCidade());
        endereco.setEstado(enderecoByUpdate.getEstado());
        endereco.setPais(enderecoByUpdate.getPais());

        return endereco;
    }

    public void delete(Endereco endereco) {
        listaEnderecos.remove(endereco);
    }
}
