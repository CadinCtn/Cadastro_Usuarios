
package factory;

import java.sql.*;
import javax.swing.*;
public class TestaConexao {
       
    public static void main(String args[]) throws SQLException{
        Connection connection;
        connection = new ConnectionFactory().getConnection();
        System.out.println("Conexão estabelecida");
        JOptionPane.showMessageDialog(null,"Conexão estabelecida");
        connection.close();
    }      
}