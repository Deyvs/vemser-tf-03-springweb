package br.com.dbc.vemser.ecommerce.service;


import br.com.dbc.vemser.ecommerce.dto.cliente.ClienteDTO;
import br.com.dbc.vemser.ecommerce.dto.pedido.PedidoCreateDTO;
import br.com.dbc.vemser.ecommerce.dto.pedido.PedidoDTO;
import br.com.dbc.vemser.ecommerce.dto.produto.ProdutoDTO;
import br.com.dbc.vemser.ecommerce.entity.Pedido;
import br.com.dbc.vemser.ecommerce.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.ecommerce.repository.PedidoRepository;
import br.com.dbc.vemser.ecommerce.repository.PedidoXProdutoRepository;
import br.com.dbc.vemser.ecommerce.utils.NotificacaoByEmail;
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
    private final PedidoXProdutoService pedidoXProdutoService;
    private final ClienteService clienteService;
    private final NotificacaoByEmail notificacaoByEmail;
    private final ObjectMapper objectMapper;

    public PedidoDTO criarPedido(Integer idCliente, PedidoCreateDTO idProduto) throws Exception {

        ClienteDTO cliente = clienteService.getClienteById(idCliente);

        ProdutoDTO produtoDTO = produtoService.buscarProduto(idProduto.getIdProduto());

        PedidoDTO pedidoOutputDTO = objectMapper.convertValue(pedidoRepository.adicionar(
                new Pedido(null, idCliente, produtoDTO.getValor(),"N"))
                , PedidoDTO.class);

        pedidoXProdutoRepository.adicionarProdutoAoPedido(pedidoOutputDTO.getIdPedido(),idProduto.getIdProduto());

        List<ProdutoDTO> produtosPedido = pedidoXProdutoRepository.listarProdutosDoPedido(pedidoOutputDTO.getIdPedido())
                .stream()
                .map(produto -> objectMapper.convertValue(produto,ProdutoDTO.class)).collect(Collectors.toList());

        pedidoOutputDTO.setProdutos(produtosPedido);

        return pedidoOutputDTO;
    }

    public List<PedidoDTO> listar() throws Exception {
        List<PedidoDTO>listaOut = new ArrayList<>();

        for(Pedido p: pedidoRepository.listar()){
            PedidoDTO pedidoDTO = objectMapper.convertValue(p,PedidoDTO.class);

            List<ProdutoDTO> produtosPedido = pedidoXProdutoRepository.listarProdutosDoPedido(pedidoDTO.getIdPedido())
                    .stream()
                    .map(produto -> objectMapper.convertValue(produto,ProdutoDTO.class)).collect(Collectors.toList());

            pedidoDTO.setProdutos(produtosPedido);
            listaOut.add(pedidoDTO);

        }
        return listaOut;
    }
    public PedidoDTO buscarByIdPedido(Integer idPedido) throws Exception {

        PedidoDTO pedidoDTO = objectMapper.convertValue(pedidoRepository.getPedidoPorId(idPedido),PedidoDTO.class);

        if(pedidoDTO == null ){
            throw new RegraDeNegocioException("Pedido não encontrado");
        }
        pedidoDTO.setProdutos(pedidoXProdutoService.listarProdutosDoPedido(idPedido));
        return pedidoDTO;
    }

    public PedidoDTO adicionarProdutoaoPedido(Integer idPedido, Integer idProduto) throws Exception{
        Boolean status = atualizarValorPedido("i",idPedido,idProduto);
        if(status){
            pedidoXProdutoRepository.adicionarProdutoAoPedido(idPedido,idProduto);
        }

        PedidoDTO pedidoDTO =objectMapper.convertValue( pedidoRepository.getPedidoPorId(idPedido),PedidoDTO.class);


        List<ProdutoDTO> produtosPedido = pedidoXProdutoRepository.listarProdutosDoPedido(pedidoDTO.getIdPedido())
                .stream()
                .map(produto -> objectMapper.convertValue(produto,ProdutoDTO.class)).collect(Collectors.toList());

        pedidoDTO.setProdutos(produtosPedido);

        return pedidoDTO;
    }
    public PedidoDTO RemoverProdutoDoPedido(Integer idPedido, Integer idProduto) throws Exception{
        Boolean status = atualizarValorPedido("d",idPedido,idProduto);
        if(status){
            pedidoXProdutoRepository.removerProdutoDoPedido(idPedido,idProduto);
        }

        PedidoDTO pedidoDTO =objectMapper.convertValue( pedidoRepository.getPedidoPorId(idPedido),PedidoDTO.class);

        List<ProdutoDTO> produtosPedido = pedidoXProdutoRepository.listarProdutosDoPedido(pedidoDTO.getIdPedido())
                .stream()
                .map(produto -> objectMapper.convertValue(produto,ProdutoDTO.class)).collect(Collectors.toList());

        pedidoDTO.setProdutos(produtosPedido);

        return pedidoDTO;
    }

    public Boolean atualizarValorPedido(String flag,Integer idPedido,Integer idProduto) throws Exception {

        Pedido pedidoAchado = pedidoRepository.getPedidoPorId(idPedido);


        if (pedidoAchado == null) {
            throw new RegraDeNegocioException("Pedido não encontrado");
        }

        if (pedidoAchado.getStatusPedido().equalsIgnoreCase("S")) {
            throw new RegraDeNegocioException("Pedido finalizado");
        }
        ProdutoDTO produtoDTO = produtoService.buscarProduto(idProduto);

        if(produtoDTO == null){
            throw new RegraDeNegocioException("Produto não encontrado");
        }

        List<ProdutoDTO> produtosDTO = pedidoXProdutoService.listarProdutosDoPedido(pedidoAchado.getIdPedido());

        if(flag.equalsIgnoreCase("i")){

            Double valor = pedidoAchado.getValor() + produtoDTO.getValor();

            if (pedidoRepository.editarValorDoPedido(valor, pedidoAchado.getIdPedido())) {

                return true;
            }

        }
        if (flag.equalsIgnoreCase("d")) {
            for (ProdutoDTO produto:produtosDTO) {
                if(produto.getIdProduto().equals(idProduto)){
                    Double valor = pedidoAchado.getValor() - produtoDTO.getValor();

                    if (pedidoRepository.editarValorDoPedido(valor, pedidoAchado.getIdPedido())) {

                        return true;
                    }
                }
            }
        }

        return false;
    }

    public void deletePedido(Integer idPedido) throws Exception{
        pedidoXProdutoRepository.removerTodosProdutosDoPedido(idPedido);
        pedidoRepository.remover(idPedido);
    }

    public PedidoDTO atualizarStatusPedido(Integer idPedido) throws Exception{

        PedidoDTO pedidoDTO = buscarByIdPedido(idPedido);

        if(pedidoDTO.getStatusPedido().equalsIgnoreCase("s")){
            throw new RegraDeNegocioException("Pedido finalizado");
        }
        pedidoRepository.editarStatusPedido("S",idPedido);
        pedidoDTO.setStatusPedido("S");
        ClienteDTO clienteDTO = clienteService.getClienteById(pedidoDTO.getIdCliente());

        notificacaoByEmail.notificarByEmailPedidoCliente(clienteDTO, pedidoDTO);

        return pedidoDTO;
    }

}

