package factory;

// faz as importações de classes necessárias para o funcionamento do programa
import java.sql.Connection;
// conexão SQL para Java
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
// driver de conexão SQL para Java
import java.sql.SQLException;

// classe para tratamento de exceções
public class ConnectionFactory {

    public static Connection getConnection() {

        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/projetojava", "root", "root");

        } catch (SQLException excecao) {
            throw new RuntimeException(excecao);
        }
    }

// metodo para fechamento da conexão   
    public static void closeConnection(Connection conn, PreparedStatement stmt) {

        try {
            if (conn != null) {
                conn.close();
                System.out.println("Conexão com o banco de dados fechada.");
            }
            if (stmt != null) {
                stmt.close();
                System.out.println("Statement fechado com sucesso");
            }

        } catch (Exception e) {
            System.out.println("Não foi possível fechar o statement " + e.getMessage());
        }
    }

}
