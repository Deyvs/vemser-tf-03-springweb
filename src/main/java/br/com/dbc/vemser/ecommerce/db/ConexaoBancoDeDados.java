package br.com.dbc.vemser.ecommerce.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBancoDeDados {
//    //CONEXÃO COM O DATABASE DA DBC
//    private static final String SERVER = "vemser-hml.dbccompany.com.br";
//    private static final String PORT = "25000"; // Porta TCP padrão do Oracle
//    private static final String DATABASE = "xe";
//
//    // Configuração dos parâmetros de autenticação
//    private static final String USER = "EQUIPE_3";
//    private static final String PASS = "oracle";
//    private static final String SCHEMA = "EQUIPE_3";

    //CONEXÃO COM DATABASE LOCAL

    private static final String SERVER = "localhost";
    private static final String PORT = "1521"; // Porta TCP padrão do Oracle
    private static final String DATABASE = "xe";

    // Configuração dos parâmetros de autenticação
    private static final String USER = "system";
    private static final String PASS = "oracle";
    private static final String SCHEMA = "VEM_SER_CAMISARIA";

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@" + SERVER + ":" + PORT + ":" + DATABASE;
        // jdbc:oracle:thin:@localhost:1521:xe

        // Abre-se a conexão com o Banco de Dados
        Connection con = DriverManager.getConnection(url, USER, PASS);

        // sempre usar o schema vem_ser
        con.createStatement().execute("alter session set current_schema=" + SCHEMA);

        return con;
    }
    public static void main(String[] args) {
        try {
            // Obtém a conexão do método getConnection()
            Connection con = getConnection();

            // Se chegarmos até aqui, a conexão foi estabelecida com sucesso
            System.out.println("Conexão bem-sucedida!");

            // Não se esqueça de fechar a conexão quando terminar de usá-la
            con.close();
        } catch (SQLException e) {
            // Trata a exceção, caso ocorra algum problema na conexão
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}
