package br.com.dbc.vemser.ecommerce.repository;

import br.com.dbc.vemser.ecommerce.db.ConexaoBancoDeDados;
import br.com.dbc.vemser.ecommerce.entity.Pedido;
import br.com.dbc.vemser.ecommerce.exceptions.BancoDeDadosException;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class PedidoRepository {
    public Integer getProximoId(Connection connection) throws BancoDeDadosException {
        try {
            String sql = "SELECT SEQ_PEDIDO.nextval mysequence from DUAL";
            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            if (res.next()) {
                return res.getInt("mysequence");
            }

            return null;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        }
    }
    public Pedido adicionar(Pedido pedido) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            Integer proximoId = this.getProximoId(con);
            pedido.setIdPedido(proximoId);

            String sql = "INSERT INTO PEDIDO (ID_PEDIDO, VALOR, ID_CLIENTE, PAGO) VALUES(?, ?, ?, ?)";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, proximoId);
            stmt.setDouble(2, pedido.getValor());
            stmt.setObject(3, pedido.getIdCliente());
            stmt.setString(4, pedido.getStatusPedido());

            int res = stmt.executeUpdate();

            return pedido;

        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean remover(Integer idPedido) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "DELETE FROM PEDIDO WHERE ID_PEDIDO = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, idPedido);


            int res = stmt.executeUpdate();


            return res > 0;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public List<Pedido> listar() throws BancoDeDadosException {
        List<Pedido> pedidos = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT ID_PEDIDO, VALOR, ID_CLIENTE, PAGO FROM PEDIDO";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Pedido pedido = getPedidoFromResultSet(res);
                pedidos.add(pedido);
            }
            return pedidos;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public Pedido getPedidoPorId(Integer idPedido) throws BancoDeDadosException {

        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT ID_PEDIDO, VALOR, ID_CLIENTE, PAGO FROM PEDIDO WHERE ID_PEDIDO = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setInt(1,idPedido);

            ResultSet res = preparedStatement.executeQuery();

            if(res.next()){

                Pedido pedido = new Pedido();
                pedido.setIdPedido(res.getInt("ID_PEDIDO"));
                pedido.setIdCliente(res.getInt("ID_CLIENTE"));
                pedido.setValor(res.getDouble("VALOR"));
                pedido.setStatusPedido(res.getString("PAGO"));

                return pedido;
            }

            return null;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean editarValorDoPedido(Double valor, Integer idPedido) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "UPDATE PEDIDO SET VALOR=?  WHERE ID_PEDIDO=?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setDouble(1, valor);
            stmt.setInt(2, idPedido);

            Integer res = stmt.executeUpdate();

            return res>0;

        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean editarStatusPedido(String status, Integer idPedido) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "UPDATE PEDIDO SET PAGO=?  WHERE ID_PEDIDO=?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, status);
            stmt.setInt(2, idPedido);

            Integer res = stmt.executeUpdate();

            return res>0;

        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Pedido getPedidoFromResultSet(ResultSet res) throws SQLException {
        Pedido pedido = new Pedido();
        pedido.setIdPedido(res.getInt("id_pedido"));
        pedido.setValor(res.getDouble("valor"));
        pedido.setIdCliente(res.getInt("id_cliente"));
        pedido.setStatusPedido(res.getString("pago"));
        return pedido;
    }

}