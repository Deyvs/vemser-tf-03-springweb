package br.com.dbc.vemser.ecommerce.service;

import br.com.dbc.vemser.ecommerce.dto.cliente.ClienteDTO;
import br.com.dbc.vemser.ecommerce.dto.pedido.PedidoCreateDTO;
import br.com.dbc.vemser.ecommerce.dto.pedido.PedidoDTO;
import br.com.dbc.vemser.ecommerce.dto.produto.ProdutoDTO;
import br.com.dbc.vemser.ecommerce.entity.Pedido;
import br.com.dbc.vemser.ecommerce.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.ecommerce.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.ecommerce.repository.PedidoRepository;
import br.com.dbc.vemser.ecommerce.repository.PedidoXProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoService produtoService;
    private final PedidoXProdutoRepository pedidoXProdutoRepository;
    private final ClienteService clienteService;
    private final ObjectMapper objectMapper;

    public PedidoDTO criarPedido(Integer idCliente, PedidoCreateDTO idProduto) throws BancoDeDadosException, RegraDeNegocioException {

        ClienteDTO cliente = clienteService.getClienteById(idCliente);

        ProdutoDTO produtoDTO = produtoService.buscarProduto(idProduto.getIdProduto());

        PedidoDTO pedidoOutputDTO = objectMapper.convertValue(pedidoRepository.adicionar(
                new Pedido(null, idCliente, produtoDTO.getValor(),"n"))
                , PedidoDTO.class);

        pedidoXProdutoRepository.adicionarProdutoAoPedido(pedidoOutputDTO.getIdPedido(),idProduto.getIdProduto());

        List<ProdutoDTO> produtosPedido = pedidoXProdutoRepository.listarProdutosDoPedido(pedidoOutputDTO.getIdPedido())
                .stream()
                .map(produto -> objectMapper.convertValue(produto,ProdutoDTO.class)).collect(Collectors.toList());

        pedidoOutputDTO.setProdutos(produtosPedido);

        return pedidoOutputDTO;
    }

    public List<PedidoDTO> listar() throws BancoDeDadosException {
        List<PedidoDTO>listaOut = new ArrayList<>();

        for(Pedido p: pedidoRepository.listar()){
            listaOut.add(objectMapper.convertValue(p, PedidoDTO.class));
        }
        return listaOut;
    }

    public Boolean atualizarValorPedido(Integer idPedido,Integer idProduto) throws BancoDeDadosException, RegraDeNegocioException {

        Pedido pedidoAchado = pedidoRepository.getPedidoPorId(idPedido);


        if (pedidoAchado == null) {
            throw new RegraDeNegocioException("Pedido não encontrado");
        }

        if (pedidoAchado.getStatusPedido().equalsIgnoreCase("S")) {
            throw new RegraDeNegocioException("Pedido finalizado");
        }

        ProdutoDTO produtoDTO = produtoService.buscarProduto(idProduto);

        Double valor = pedidoAchado.getValor() + produtoDTO.getValor();

        if (pedidoRepository.editarValorDoPedido(valor, pedidoAchado.getIdPedido())) {
            return true;
        }

        return false;
    }


    public void deletePedido(Integer idPedido) throws BancoDeDadosException {
        pedidoRepository.remover(idPedido);
    }

}

//    private PedidoRepository pedidoRepository;
//
//    public PedidoService() {
//        pedidoRepository = new PedidoRepository();
//    }
//
//    public void imprimirTodosOsPedidos() {
//        try {
//            for (Pedido pedido : pedidoRepository.listar()) {
//                pedido.imprimir();
//            }
//
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void imprimirPedidosDoCliente(int idCliente) {
//        try {
//            for (Pedido pedido : pedidoRepository.listarPedidosDoCliente(idCliente)) {
//                pedido.imprimir();
//            }
//
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void imprimirPedidosNaoPagosDoCliente(int idCliente) {
//        try {
//            for (Pedido pedido : pedidoRepository.listarPedidosNaoPagosDoCliente(idCliente)) {
//                pedido.imprimir();
//            }
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public List<Pedido> listarPedidosDoCliente(int idCliente) {
//        try {
//            return pedidoRepository.listarPedidosDoCliente(idCliente);
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public Pedido adicionar(Pedido pedido) {
//        try {
//            return pedidoRepository.adicionar(pedido);
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public void remover(Pedido pedido) {
//        try {
//            PedidoXProdutoService pedidoXProdutoService = new PedidoXProdutoService();
//            pedidoXProdutoService.removerTodosProdutosDoPedido(pedido.getId());
//            pedidoRepository.remover(pedido.getId());
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public void editarParaAdicionarProdutos(Pedido pedido, Produto produto) {
//        try {
//            PedidoXProdutoService pedidoXProdutoService = new PedidoXProdutoService();
//            pedido.adicionarProduto(produto);
//            pedidoRepository.editarValorDoPedido(pedido);
//            pedidoXProdutoService.adicionarProdutoAoPedido(pedido.getId(), produto.getId());
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void editarParaRemoverProdutos(Pedido pedido, Produto produto) {
//        try {
//            PedidoXProdutoService pedidoXProdutoService = new PedidoXProdutoService();
//            pedido.removerProduto(produto);
//            pedidoRepository.editarValorDoPedido(pedido);
//            pedidoXProdutoService.removerProdutoDoPedido(pedido.getId(), produto.getId());
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public Pedido buscarPedido(int idPedido) {
//        try {
//            Pedido pedido = pedidoRepository.buscarPedido(idPedido);
//            return pedido;
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public void editarPedidoParaEstarPago(Pedido pedido)  {
//        try {
//            pedido.setPago("S");
//            pedidoRepository.editarPedidoParaEstarPago(pedido);
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public Pedido buscarPedidoDoCliente(int idPedido, int idCliente) {
//        try {
//            Pedido pedido = pedidoRepository.buscarPedidoDoCliente(idPedido, idCliente);
//            return pedido;
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public Pedido buscarPedidoNaoPagoDoCliente(int idPedido, int idCliente) {
//        try {
//            for (Pedido pedido: pedidoRepository.listarPedidosNaoPagosDoCliente(idCliente)) {
//                if (pedido.getId() == idPedido) {
//                    return pedido;
//                }
//            }
//            return null;
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public boolean verificaSeClientePossuiPedidosParaPagar(int idCliente) {
//        try {
//            List<Pedido> pedidos = pedidoRepository.listarPedidosNaoPagosDoCliente(idCliente);
//            for (Pedido pedido: pedidos) {
//                if (pedido.getPago().equals("N")) {
//                        return true;
//                }
//            }
//            return false;
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }