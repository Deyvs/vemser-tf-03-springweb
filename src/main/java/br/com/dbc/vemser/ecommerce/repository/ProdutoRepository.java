package br.com.dbc.vemser.ecommerce.repository;

import br.com.dbc.vemser.ecommerce.db.ConexaoBancoDeDados;
import br.com.dbc.vemser.ecommerce.entity.Produto;
import br.com.dbc.vemser.ecommerce.exceptions.BancoDeDadosException;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProdutoRepository {

    public Integer getProximoId(Connection connection) throws BancoDeDadosException {
        try {
            String sql = "SELECT SEQ_PRODUTO.nextval mysequence from DUAL";
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

    public Produto criarProduto(Produto produto) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            Integer proximoId = this.getProximoId(con);
            produto.setIdProduto(proximoId);

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO PRODUTO ");
            sql.append("ID_PRODUTO, MODELO, TAMANHO, COR, SETOR, VALOR ");
            sql.append("VALUES");
            sql.append("(?, ?, ?, ?, ?, ?)");


            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setInt(1, produto.getIdProduto());
            stmt.setString(2, produto.getModelo());
            stmt.setString(3, produto.getTamanho());
            stmt.setString(4, produto.getCor());
            stmt.setString(5, produto.getSetor());
            stmt.setDouble(6, produto.getValor());

            int res = stmt.executeUpdate();
            return produto;
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

    public List<Produto> listar() throws BancoDeDadosException {
        List<Produto> produtos = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();


            StringBuilder sql = new StringBuilder();
            sql.append("SELECT ");
            sql.append("ID_PRODUTO, MODELO, TAMANHO, COR, SETOR, VALOR ");
            sql.append("FROM PRODUTO");


            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql.toString());

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

    public Produto buscarProduto(int idProduto) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            String sql = "SELECT ID_PRODUTO, MODELO, TAMANHO, COR, SETOR, VALOR FROM PRODUTO WHERE ID_PRODUTO = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idProduto);
            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                Produto produto = getProdutoFromResultSet(res);
                return produto;
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

//    public Integer getProximoId(Connection connection) throws BancoDeDadosException {
//        try {
//            String sql = "SELECT SEQ_PRODUTO.nextval mysequence from DUAL";
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
//    public List<Produto> listar() throws BancoDeDadosException {
//        List<Produto> produtos = new ArrayList<>();
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.getConnection();
//            Statement stmt = con.createStatement();
//
//            String sql = "SELECT ID_PRODUTO, MODELO, TAMANHO, COR, SETOR, VALOR FROM PRODUTO";
//
//            // Executa-se a consulta
//            ResultSet res = stmt.executeQuery(sql);
//
//            while (res.next()) {
//                Produto produto = getProdutoFromResultSet(res);
//                produtos.add(produto);
//            }
//            return produtos;
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
//    public Produto buscarProduto(int idProduto) throws BancoDeDadosException {
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.getConnection();
//            String sql = "SELECT ID_PRODUTO, MODELO, TAMANHO, COR, SETOR, VALOR FROM PRODUTO WHERE ID_PRODUTO = ?";
//
//            PreparedStatement stmt = con.prepareStatement(sql);
//            stmt.setInt(1, idProduto);
//            ResultSet res = stmt.executeQuery();
//
//            if (res.next()) {
//                Produto produto = getProdutoFromResultSet(res);
//                return produto;
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
//    public Integer getMaximoId() throws BancoDeDadosException {
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.getConnection();
//            String sql = "SELECT MAX(ID_PRODUTO) AS MAIOR_ID FROM PRODUTO";
//            Statement stmt = con.createStatement();
//            ResultSet res = stmt.executeQuery(sql);
//
//            if (res.next()) {
//                int maiorId = res.getInt("MAIOR_ID");
//                return maiorId;
//            }
//            return 0;
//
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        }
//    }
//
//
//    private Produto getProdutoFromResultSet(ResultSet res) throws SQLException {
//        Produto produto = new Produto();
//        produto.setId(res.getInt("id_produto"));
//        produto.setModelo(res.getString("modelo"));
//        produto.setTamanho(res.getString("tamanho"));
//        produto.setCor(res.getString("cor"));
//        produto.setSetor(res.getString("setor"));
//        produto.setValor(res.getDouble("valor"));
//        return produto;
//    }