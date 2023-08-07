package br.com.dbc.vemser.ecommerce.service;

import br.com.dbc.vemser.ecommerce.dto.PedidoCreateDTO;
import br.com.dbc.vemser.ecommerce.dto.PedidoOutputDTO;
import br.com.dbc.vemser.ecommerce.dto.produto.ProdutoDTO;
import br.com.dbc.vemser.ecommerce.entity.Pedido;
import br.com.dbc.vemser.ecommerce.entity.Produto;
import br.com.dbc.vemser.ecommerce.exceptions.BancoDeDadosException;
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

    public PedidoOutputDTO adicionar(Integer idCliente) throws Exception {
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

//    public PedidoOutputDTO atualizarValorPedido(Integer idPedido,Integer idProduto) throws Exception{
//
//        PedidoOutputDTO pedidoOutputDTO = null;
//
//        Produto produtoAchado = objectMapper.convertValue(produtoService.buscarProduto(idProduto), Produto.class);
//        Pedido pedidoAchado = pedidoRepository.getPedidoPorId(idPedido);
//
//        if(pedidoAchado != null  && produtoAchado != null){
//
//            pedidoAchado.setValor(pedidoAchado.getValor()+produtoAchado.getValor());
//
//            if(pedidoRepository.editarValorDoPedido(pedidoAchado)){
//
//                return pedidoOutputDTO = objectMapper.convertValue(pedidoAchado,PedidoOutputDTO.class);
//            }
//        }
//
//        return pedidoOutputDTO;
//    }


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