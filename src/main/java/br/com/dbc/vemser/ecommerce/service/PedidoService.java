package br.com.dbc.vemser.ecommerce.service;

import br.com.dbc.vemser.ecommerce.dto.cliente.ClienteDTO;
import br.com.dbc.vemser.ecommerce.dto.pedido.PedidoCreateDTO;
import br.com.dbc.vemser.ecommerce.dto.pedido.PedidoOutputDTO;
import br.com.dbc.vemser.ecommerce.dto.produto.ProdutoDTO;
import br.com.dbc.vemser.ecommerce.entity.Pedido;
import br.com.dbc.vemser.ecommerce.exceptions.ProdutoNaoEncontradoException;
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

    public PedidoOutputDTO criarPedido(Integer idCliente, PedidoCreateDTO idProduto) throws Exception {

        ClienteDTO cliente = clienteService.getClienteById(idCliente);
        if(cliente == null ){
            throw new ProdutoNaoEncontradoException("Cliente não encontrado");
        }
        ProdutoDTO produtoDTO = produtoService.buscarProduto(idProduto.getIdProduto());
        if(produtoDTO == null ){
            throw new ProdutoNaoEncontradoException("Produto não encontrado");
        }
        PedidoOutputDTO pedidoOutputDTO = objectMapper.convertValue(pedidoRepository.adicionar(
                new Pedido(null, idCliente, produtoDTO.getValor(),"n"))
                ,PedidoOutputDTO.class);

        pedidoXProdutoRepository.adicionarProdutoAoPedido(pedidoOutputDTO.getIdPedido(),idProduto.getIdProduto());

        List<ProdutoDTO> produtosPedido = pedidoXProdutoRepository.listarProdutosDoPedido(pedidoOutputDTO.getIdPedido())
                .stream()
                .map(produto -> objectMapper.convertValue(produto,ProdutoDTO.class)).collect(Collectors.toList());

        pedidoOutputDTO.setProdutos(produtosPedido);

        return pedidoOutputDTO;
    }

    public List<PedidoOutputDTO> listar() throws Exception{
        List<PedidoOutputDTO>listaOut = new ArrayList<>();

        for(Pedido p: pedidoRepository.listar()){
            listaOut.add(objectMapper.convertValue(p,PedidoOutputDTO.class));
        }
        return listaOut;
    }

    public PedidoOutputDTO adicionarProdutoaoPedido(Integer idPedido, Integer idProduto) throws Exception{
        Boolean status = atualizarValorPedido("i",idPedido,idProduto);
        if(status){
            pedidoXProdutoRepository.adicionarProdutoAoPedido(idPedido,idProduto);
        }
        PedidoOutputDTO pedidoOutputDTO =objectMapper.convertValue( pedidoRepository.getPedidoPorId(idPedido),PedidoOutputDTO.class);

        List<ProdutoDTO> produtosPedido = pedidoXProdutoRepository.listarProdutosDoPedido(pedidoOutputDTO.getIdPedido())
                .stream()
                .map(produto -> objectMapper.convertValue(produto,ProdutoDTO.class)).collect(Collectors.toList());

        pedidoOutputDTO.setProdutos(produtosPedido);

        return pedidoOutputDTO;
    }
    public PedidoOutputDTO RemoverProdutoDoPedido(Integer idPedido, Integer idProduto) throws Exception{
        Boolean status = atualizarValorPedido("d",idPedido,idProduto);
        if(status){
            pedidoXProdutoRepository.removerProdutoDoPedido(idPedido,idProduto);
        }
        PedidoOutputDTO pedidoOutputDTO =objectMapper.convertValue( pedidoRepository.getPedidoPorId(idPedido),PedidoOutputDTO.class);

        List<ProdutoDTO> produtosPedido = pedidoXProdutoRepository.listarProdutosDoPedido(pedidoOutputDTO.getIdPedido())
                .stream()
                .map(produto -> objectMapper.convertValue(produto,ProdutoDTO.class)).collect(Collectors.toList());

        pedidoOutputDTO.setProdutos(produtosPedido);

        return pedidoOutputDTO;
    }

    public Boolean atualizarValorPedido(String flag,Integer idPedido,Integer idProduto) throws Exception {

        Pedido pedidoAchado = pedidoRepository.getPedidoPorId(idPedido);

        if (pedidoAchado == null) {
            throw new ProdutoNaoEncontradoException("Pedido não encontrado");
        }

        if (pedidoAchado.getStatusPedido().equalsIgnoreCase("S")) {
            throw new ProdutoNaoEncontradoException("Pedido finalizado");
        }
        ProdutoDTO produtoDTO = produtoService.buscarProduto(idProduto);

        if(produtoDTO == null){
            throw new ProdutoNaoEncontradoException("Produto não encontrado");
        }

        if(flag.equalsIgnoreCase("i")){

            Double valor = pedidoAchado.getValor() + produtoDTO.getValor();

            if (pedidoRepository.editarValorDoPedido(valor, pedidoAchado.getIdPedido())) {

                return true;
            }

        }
        if (flag.equalsIgnoreCase("d")) {

            Double valor = pedidoAchado.getValor() - produtoDTO.getValor();

            if (pedidoRepository.editarValorDoPedido(valor, pedidoAchado.getIdPedido())) {

                return true;
            }
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