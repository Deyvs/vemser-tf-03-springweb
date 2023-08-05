package br.com.dbc.vemser.ecommerce.repository;

import br.com.dbc.vemser.ecommerce.db.ConexaoBancoDeDados;
import br.com.dbc.vemser.ecommerce.entity.Cliente;
import br.com.dbc.vemser.ecommerce.exceptions.BancoDeDadosException;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteRepository {

    public Integer getProximoId(Connection connection) throws BancoDeDadosException {
        try {
            String sql = "SELECT SEQ_CLIENTE.nextval mysequence from DUAL";
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

    public List<Cliente> list() throws BancoDeDadosException {
        List<Cliente> clientes = new ArrayList<>();
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM CLIENTE";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Cliente cliente = getClienteFromResultSet(res);
                clientes.add(cliente);
            }

            return clientes;

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

    public Cliente getClienteById(Integer idCliente) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            String sql = "SELECT * FROM CLIENTE WHERE ID_CLIENTE = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                Cliente cliente = getClienteFromResultSet(res);
                return cliente;
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

    public Cliente create(Cliente cliente) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            Integer proximoIdCliente = this.getProximoId(con);
            cliente.setIdCliente(proximoIdCliente);

            String sqlCliente = "INSERT INTO CLIENTE c (ID_CLIENTE, NOME, TELEFONE, EMAIL, CPF) " +
                    "VALUES(?, ?, ?, ?, ?)\n";

            PreparedStatement stmtCliente = con.prepareStatement(sqlCliente);

            stmtCliente.setInt(1, cliente.getIdCliente());
            stmtCliente.setString(2, cliente.getNome());
            stmtCliente.setString(3, cliente.getTelefone());
            stmtCliente.setString(4, cliente.getEmail());
            stmtCliente.setString(5, cliente.getCpf());

            stmtCliente.executeUpdate();

            return cliente;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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

    public Cliente update(Integer idCliente, Cliente cliente) throws BancoDeDadosException {
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CLIENTE SET");
            sql.append(" nome = ?,");
            sql.append(" telefone = ?,");
            sql.append(" email = ?,");
            sql.append(" cpf = ?");
            sql.append(" WHERE id_cliente = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getCpf());
            stmt.setInt(5, idCliente);

            // Executa-se a consulta
            stmt.executeUpdate();

            cliente.setIdCliente(idCliente);

            return cliente;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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

    public void delete(Integer idCliente) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "DELETE FROM CLIENTE WHERE id_cliente = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, idCliente);

            // Executa-se a consulta
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } catch (Exception e) {
            System.err.println("Ocorreu um erro no banco de dados");
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

    private Cliente getClienteFromResultSet(ResultSet res) throws SQLException {
        Cliente clienteFisico = new Cliente();
        clienteFisico.setIdCliente(res.getInt("id_cliente"));
        clienteFisico.setNome(res.getString("nome"));
        clienteFisico.setTelefone(res.getString("telefone"));
        clienteFisico.setEmail(res.getString("email"));
        clienteFisico.setCpf(res.getString("cpf"));
        return clienteFisico;
    }
}

