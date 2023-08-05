package br.com.dbc.vemser.ecommerce.repository;

import org.springframework.stereotype.Repository;

@Repository
public class PedidoRepository {

}

//    public Integer getProximoId(Connection connection) throws BancoDeDadosException {
//        try {
//            String sql = "SELECT SEQ_PEDIDO.nextval mysequence from DUAL";
//            Statement stmt = connection.createStatement();
//            ResultSet res = stmt.executeQuery(sql);
//
//            if (res.next()) {
//                return res.getInt("mysequence");
//            }
//
//            return null;
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        }
//    }
//
//    public Pedido adicionar(Pedido pedido) throws BancoDeDadosException {
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.getConnection();
//
//            Integer proximoId = this.getProximoId(con);
//            pedido.setId(proximoId);
//
//            String sql = "INSERT INTO PEDIDO (ID_PEDIDO, VALOR, ID_CLIENTE, PAGO) VALUES(?, ?, ?, ?)";
//
//            PreparedStatement stmt = con.prepareStatement(sql);
//
//            stmt.setInt(1, proximoId);
//            stmt.setDouble(2, pedido.getValor());
//            stmt.setObject(3, pedido.getCliente().getIdCliente());
//            stmt.setString(4, pedido.getPago());
//
//            int res = stmt.executeUpdate();
//
//            return pedido;
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public boolean remover(int idPedido) throws BancoDeDadosException {
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.getConnection();
//
//            String sql = "DELETE FROM PEDIDO WHERE ID_PEDIDO = ?";
//
//            PreparedStatement stmt = con.prepareStatement(sql);
//
//            stmt.setInt(1, idPedido);
//
//
//            int res = stmt.executeUpdate();
//
//
//            return res > 0;
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//
//    public List<Pedido> listar() throws BancoDeDadosException {
//        List<Pedido> pedidos = new ArrayList<>();
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.getConnection();
//            Statement stmt = con.createStatement();
//
//            String sql = "SELECT ID_PEDIDO, VALOR, ID_CLIENTE, PAGO FROM PEDIDO";
//
//            ResultSet res = stmt.executeQuery(sql);
//
//            while (res.next()) {
//                Pedido pedido = getPedidoFromResultSet(res);
//                pedidos.add(pedido);
//            }
//            return pedidos;
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public List<Pedido> listarPedidosDoCliente(int idCliente) throws BancoDeDadosException {
//        List<Pedido> pedidos = new ArrayList<>();
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.getConnection();
//
//            String sql = "SELECT ID_PEDIDO, VALOR, ID_CLIENTE, PAGO FROM PEDIDO WHERE ID_CLIENTE = ?";
//
//            PreparedStatement stmt = con.prepareStatement(sql);
//            stmt.setInt(1, idCliente);
//            ResultSet res = stmt.executeQuery();
//
//            while (res.next()) {
//                Pedido pedido = getPedidoFromResultSet(res);
//                pedidos.add(pedido);
//            }
//            return pedidos;
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public List<Pedido> listarPedidosNaoPagosDoCliente(int idCliente) throws BancoDeDadosException {
//        List<Pedido> pedidos = new ArrayList<>();
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.getConnection();
//
//            String sql = "SELECT ID_PEDIDO, VALOR, ID_CLIENTE, PAGO FROM PEDIDO WHERE ID_CLIENTE = ? AND PAGO = 'N'";
//
//            PreparedStatement stmt = con.prepareStatement(sql);
//            stmt.setInt(1, idCliente);
//            ResultSet res = stmt.executeQuery();
//
//            while (res.next()) {
//                Pedido pedido = getPedidoFromResultSet(res);
//                pedidos.add(pedido);
//            }
//            return pedidos;
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public Pedido buscarPedido(int idPedido) throws BancoDeDadosException {
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.getConnection();
//
//            String sql = "SELECT ID_PEDIDO, VALOR, ID_CLIENTE, PAGO FROM PEDIDO WHERE ID_PEDIDO = ?";
//
//            PreparedStatement stmt = con.prepareStatement(sql);
//            stmt.setInt(1, idPedido);
//
//            ResultSet res = stmt.executeQuery();
//
//            if (res.next()) {
//                Pedido pedido = getPedidoFromResultSet(res);
//                return pedido;
//            }
//            return null;
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public Pedido buscarPedidoDoCliente(int idPedido, int idCliente) throws BancoDeDadosException {
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.getConnection();
//
//            String sql = "SELECT ID_PEDIDO, VALOR, ID_CLIENTE, PAGO FROM PEDIDO WHERE ID_PEDIDO = ? AND ID_CLIENTE = ?";
//
//            PreparedStatement stmt = con.prepareStatement(sql);
//            stmt.setInt(1, idPedido);
//            stmt.setInt(2, idCliente);
//
//            ResultSet res = stmt.executeQuery();
//
//            if (res.next()) {
//                Pedido pedido = getPedidoFromResultSet(res);
//                return pedido;
//            }
//            return null;
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void editarValorDoPedido(Pedido pedido) throws BancoDeDadosException {
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.getConnection();
//
//            String sql = "UPDATE PEDIDO SET VALOR=?, ID_CLIENTE=?, PAGO='N' WHERE ID_PEDIDO=?";
//
//            PreparedStatement stmt = con.prepareStatement(sql);
//
//            stmt.setDouble(1, pedido.getValor());
//            stmt.setInt(2, pedido.getCliente().getIdCliente());
//            stmt.setInt(3, pedido.getId());
//
//            stmt.executeUpdate();
//
//
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void editarPedidoParaEstarPago(Pedido pedido) throws BancoDeDadosException {
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.getConnection();
//
//            String sql = "UPDATE PEDIDO SET PAGO=?, ID_CLIENTE=? WHERE ID_PEDIDO=?";
//
//            PreparedStatement stmt = con.prepareStatement(sql);
//
//            stmt.setString(1, pedido.getPago());
//            stmt.setInt(2, pedido.getCliente().getIdCliente());
//            stmt.setInt(3, pedido.getId());
//
//            stmt.executeUpdate();
//
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private Pedido getPedidoFromResultSet(ResultSet res) throws SQLException {
//        Pedido pedido = new Pedido();
//        pedido.setId(res.getInt("id_pedido"));
//        pedido.setValor(res.getDouble("valor"));
//        int idCliente = (res.getInt("id_cliente"));
//        pedido.setPago(res.getString("pago"));
//        ClienteFisicoRepository clienteFisicoRepository = new ClienteFisicoRepository();
//        pedido.setCliente(clienteFisicoRepository.buscarCliente(idCliente));
//        PedidoXProdutoRepository pedidoXProdutoRepository = new PedidoXProdutoRepository();
//        pedido.setProdutos((ArrayList<Produto>) pedidoXProdutoRepository.listarProdutosDoPedido(pedido.getId()));
//        return pedido;
//    }