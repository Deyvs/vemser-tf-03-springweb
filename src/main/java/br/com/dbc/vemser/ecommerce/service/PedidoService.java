package br.com.dbc.vemser.ecommerce.service;

import br.com.dbc.vemser.ecommerce.dto.pedido.PedidoOutputDTO;
import br.com.dbc.vemser.ecommerce.dto.produto.ProdutoDTO;
import br.com.dbc.vemser.ecommerce.entity.Pedido;
import br.com.dbc.vemser.ecommerce.exceptions.ProdutoNaoEncontradoException;
import br.com.dbc.vemser.ecommerce.repository.PedidoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoService produtoService;
    private final ObjectMapper objectMapper;

    public PedidoOutputDTO criarPedido(Integer idCliente) throws Exception {
        // Validar ID Cliente e ID Produto
        // Criar pedido
        // Inserir produto na tabela PedidoxPedidoBruto
        PedidoOutputDTO pedidoOutputDTO = null;
        try {
            Pedido pedido = pedidoRepository.adicionar(new Pedido(null, idCliente, 0.0, "N"));
            pedidoOutputDTO = objectMapper.convertValue(pedido,PedidoOutputDTO.class);
            return  pedidoOutputDTO;
        }catch (SQLException e){
          e.printStackTrace();
        }
        return pedidoOutputDTO;
    }

    public List<PedidoOutputDTO> listar() throws Exception{
        List<PedidoOutputDTO>listaOut = new ArrayList<>();

        for(Pedido p: pedidoRepository.listar()){
            listaOut.add(objectMapper.convertValue(p,PedidoOutputDTO.class));
        }
        return listaOut;
    }

    public Boolean atualizarValorPedido(Integer idPedido,Integer idProduto) throws Exception {

        Pedido pedidoAchado = pedidoRepository.getPedidoPorId(idPedido);

        if (pedidoAchado == null) {
            throw new ProdutoNaoEncontradoException("Pedido não encontrado");
        }

        if (pedidoAchado.getStatusPedido().equalsIgnoreCase("S")) {
            throw new ProdutoNaoEncontradoException("Pedido finalizado");
        }

        ProdutoDTO produtoDTO = produtoService.buscarProduto(idProduto);

        Double valor = pedidoAchado.getValor() + produtoDTO.getValor();

        if (pedidoRepository.editarValorDoPedido(valor, pedidoAchado.getIdPedido())) {
            return true;
        }

        return false;
    }


    public void deletePedido(Integer idPedido) throws Exception{
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