package br.com.dbc.vemser.ecommerce.repository;

import br.com.dbc.vemser.ecommerce.db.ConexaoBancoDeDados;
import br.com.dbc.vemser.ecommerce.entity.Produto;
import br.com.dbc.vemser.ecommerce.exceptions.BancoDeDadosException;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PedidoXProdutoRepository {

    public Integer getProximoId(Connection connection) throws BancoDeDadosException {
        try {
            String sql = "SELECT SEQ_PEDIDOXPRODUTO.nextval mysequence from DUAL";
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


    public List<Produto> listarProdutosDoPedido(int idPedido) throws BancoDeDadosException {
        List<Produto> produtos = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT p.ID_PRODUTO, p.MODELO, p.TAMANHO, p.COR, p.SETOR, p.VALOR FROM PRODUTO p JOIN PEDIDO_X_PRODUTO pp ON pp.ID_PRODUTO  = p.ID_PRODUTO AND pp.ID_PEDIDO = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idPedido);
            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                Produto produto = getProdutoFromResultSet(res);
                produtos.add(produto);
            }
            return produtos;
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

    public boolean adicionarProdutoAoPedido(int idPedido, int idProduto) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Integer proximoId = this.getProximoId(con);

            String sql = "INSERT INTO PEDIDO_X_PRODUTO(ID_PEDIDOPRODUTO ,ID_PEDIDO, ID_PRODUTO) VALUES(?, ?, ?)";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, proximoId);
            stmt.setInt(2, idPedido);
            stmt.setInt(3, idProduto);

            int res = stmt.executeUpdate();
            return true;
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

    public boolean removerProdutoDoPedido(int idPedido, int idProduto) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "DELETE FROM PEDIDO_X_PRODUTO WHERE ID_PEDIDO=? AND ID_PRODUTO=? AND ROWNUM = 1";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, idPedido);
            stmt.setInt(2, idProduto);

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

    public void removerTodosProdutosDoPedido(int idPedido) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "DELETE FROM PEDIDO_X_PRODUTO WHERE ID_PEDIDO=?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, idPedido);

            int res = stmt.executeUpdate();

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

    private Produto getProdutoFromResultSet(ResultSet res) throws SQLException {
        Produto produto = new Produto();
        produto.setIdProduto(res.getInt("id_produto"));
        produto.setModelo(res.getString("modelo"));
        produto.setTamanho(res.getString("tamanho"));
        produto.setCor(res.getString("cor"));
        produto.setSetor(res.getString("setor"));
        produto.setValor(res.getDouble("valor"));
        return produto;
    }

}
