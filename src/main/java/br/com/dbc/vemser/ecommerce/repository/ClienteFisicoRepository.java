package br.com.dbc.vemser.ecommerce.repository;

import org.springframework.stereotype.Repository;

@Repository
public class ClienteFisicoRepository {

}

//    public Integer getProximoId(Connection connection) throws BancoDeDadosException {
//        try {
//            String sql = "SELECT SEQ_CLIENTE_FISICO.nextval mysequence from DUAL";
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
//    public ClienteFisico buscarCliente(int idCliente) throws BancoDeDadosException {
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.getConnection();
//            String sql = "SELECT ID_CLIENTE, NOME, TELEFONE, EMAIL, CPF FROM CLIENTE_FISICO WHERE ID_CLIENTE = ?";
//
//            PreparedStatement stmt = con.prepareStatement(sql);
//            stmt.setInt(1, idCliente);
//            ResultSet res = stmt.executeQuery();
//
//            if (res.next()) {
//                ClienteFisico clienteFisico = getClienteFromResultSet(res);
//                return clienteFisico;
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
//    public List<ClienteFisico> listar() throws BancoDeDadosException {
//        List<ClienteFisico> clientesFisico = new ArrayList<>();
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.getConnection();
//            Statement stmt = con.createStatement();
//
//            String sql = "SELECT ID_CLIENTE, NOME, TELEFONE, EMAIL, CPF FROM CLIENTE_FISICO";
//
//            ResultSet res = stmt.executeQuery(sql);
//
//            while (res.next()) {
//                ClienteFisico clienteFisico = getClienteFromResultSet(res);
//                clientesFisico.add(clienteFisico);
//            }
//            return clientesFisico;
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
//            String sql = "SELECT MAX(ID_CLIENTE) AS MAIOR_ID FROM CLIENTE_FISICO";
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
//    private ClienteFisico getClienteFromResultSet(ResultSet res) throws SQLException {
//        ClienteFisico clienteFisico = new ClienteFisico();
//        clienteFisico.setIdCliente(res.getInt("id_cliente"));
//        clienteFisico.setNome(res.getString("nome"));
//        clienteFisico.setTelefone(res.getString("telefone"));
//        clienteFisico.setEmail(res.getString("email"));
//        clienteFisico.setCpf(res.getString("cpf"));
//        return clienteFisico;
//    }
