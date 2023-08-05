package br.com.dbc.vemser.ecommerce.service;

import org.springframework.stereotype.Service;

@Service
public class PedidoService {

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