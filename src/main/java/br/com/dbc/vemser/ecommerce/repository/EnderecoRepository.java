package br.com.dbc.vemser.ecommerce.repository;

import br.com.dbc.vemser.ecommerce.db.ConexaoBancoDeDados;
import br.com.dbc.vemser.ecommerce.entity.Endereco;
import br.com.dbc.vemser.ecommerce.exceptions.BancoDeDadosException;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EnderecoRepository {
    private static List<Endereco> listaEnderecos = new ArrayList<>();

    public Integer getProximoId(Connection connection) throws BancoDeDadosException {
        try {
            String sql = "SELECT SEQ_ENDERECO.nextval mysequence from DUAL";
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

    public List<Endereco> listarEnderecos() throws BancoDeDadosException {

        List<Endereco> enderecos = new ArrayList<>();
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM ENDERECO";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Endereco endereco = getClienteFromResultSet(res);
                enderecos.add(endereco);
            }

            return enderecos;

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

    ;

    public Endereco getEnderecoById(Integer idEndereco) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            String sql = "SELECT * FROM ENDERECO WHERE ID_ENDERECO = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            System.out.println(stmt);
            stmt.setInt(1, idEndereco);
            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                Endereco endereco = getClienteFromResultSet(res);
                return endereco;
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

    public List<Endereco> listarEnderecoByIdPessoa(Integer idCliente) throws BancoDeDadosException {
        List<Endereco> enderecos = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            String sql = "SELECT * FROM ENDERECO WHERE ID_CLIENTE = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            System.out.println(stmt);
            stmt.setInt(1, idCliente);
            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                Endereco endereco = getClienteFromResultSet(res);
                enderecos.add(endereco);
            }

            return enderecos;
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

    public Endereco create(Integer idCliente, Endereco endereco) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            Integer proximoIdEndereco = this.getProximoId(con);
            endereco.setIdEndereco(proximoIdEndereco);


            String sqlEndereco = "INSERT INTO ENDERECO\n" +
                    "(ID_ENDERECO, CEP, CIDADE, TIPO_ENDERECO, BAIRRO, LOGRADOURO, NUMERO, COMPLEMENTO, PAIS, UF_ESTADO, ID_CLIENTE)\n" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)\n";


            PreparedStatement stmtEndereco = con.prepareStatement(sqlEndereco);

            stmtEndereco.setInt(1, proximoIdEndereco);
            stmtEndereco.setString(2, endereco.getCep());
            stmtEndereco.setString(3, endereco.getCidade());
            stmtEndereco.setString(4, endereco.getTipoEndereco());
            stmtEndereco.setString(5, endereco.getBairro());
            stmtEndereco.setString(6, endereco.getLogradouro());
            stmtEndereco.setInt(7, endereco.getNumero());
            stmtEndereco.setString(8, endereco.getComplemento());
            stmtEndereco.setString(9, endereco.getPais());
            stmtEndereco.setString(10, endereco.getEstado());
            stmtEndereco.setInt(11, idCliente);

            stmtEndereco.executeUpdate();
            endereco.setIdCliente(idCliente);

            return endereco;
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


    public Endereco update(Integer idEndereco, Endereco endereco) throws BancoDeDadosException {
        Connection con = null;


        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE ENDERECO SET");
            sql.append(" ID_CLIENTE = ?,");
            sql.append(" LOGRADOURO = ?,");
            sql.append(" PAIS = ?,");
            sql.append(" NUMERO = ?,");
            sql.append(" BAIRRO = ?,");
            sql.append(" CIDADE = ?,");
            sql.append(" CEP = ?,");
            sql.append(" UF_ESTADO = ?,");
            sql.append(" COMPLEMENTO = ?,");
            sql.append(" TIPO_ENDERECO = ?");
            sql.append(" WHERE ID_ENDERECO =?");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setInt(1, endereco.getIdCliente());
            stmt.setString(2, endereco.getLogradouro());
            stmt.setString(3, endereco.getCep());
            stmt.setString(4, endereco.getEstado());
            stmt.setString(5, endereco.getCidade());
            stmt.setInt(6, endereco.getNumero());
            stmt.setString(7, endereco.getComplemento());
            stmt.setInt(8, idEndereco);

            // Executa-se a consulta
            stmt.executeUpdate();

            endereco.setIdEndereco(idEndereco);

            return endereco;
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

    public void delete(Integer idEndereco) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "DELETE FROM ENDERECO WHERE ID_ENDERECO = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, idEndereco);

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

    private Endereco getClienteFromResultSet(ResultSet res) throws SQLException {


        Endereco endereco = new Endereco();
        endereco.setIdEndereco(res.getInt("ID_ENDERECO"));
        endereco.setIdCliente(res.getInt("id_cliente"));
        endereco.setCep(res.getString("CEP"));
        endereco.setCidade(res.getString("CIDADE"));
        endereco.setLogradouro(res.getString("LOGRADOURO"));
        endereco.setNumero(res.getInt("NUMERO"));
        endereco.setComplemento(res.getString("COMPLEMENTO"));
        endereco.setEstado(res.getString("UF_ESTADO"));
        endereco.setPais(res.getString("PAIS"));
        endereco.setTipoEndereco(res.getString("TIPO_ENDERECO"));
        endereco.setBairro(res.getString("BAIRRO"));

        return endereco;
    }
}
