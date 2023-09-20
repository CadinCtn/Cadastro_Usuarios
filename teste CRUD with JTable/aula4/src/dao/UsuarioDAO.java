package dao;

import factory.ConnectionFactory;
import modelo.Usuario;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class UsuarioDAO {
    
    private Connection connection;
    Long id;
    String nome;
    String cpf;
    String email;
    String telefone;

    public UsuarioDAO(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public void adiciona(Usuario usuario){
        String sql = "INSERT INTO usuario(nome,cpf,email,telefone)VALUES(?,?,?,?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getTelefone());
            stmt.execute();
            stmt.close();
        }

        catch (SQLException u) {
            throw new RuntimeException(u);
        }
        
    }

    
    public List<Usuario> visualizar() throws SQLException {
        String sql = "SELECT * FROM projetojava.usuario;";
        List<Usuario> usersList = new ArrayList<>();
        
        
        try(PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            
            
            while(rs.next()){
                Usuario usuario = new Usuario();
                usuario.setId(rs.getLong("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefone(rs.getString("telefone"));
                usersList.add(usuario);
                
            }
            
            stmt.close();
            rs.close();
        }
        
        catch (SQLException u) {
        throw new RuntimeException(u);
        }
        
        return usersList;
    }

    
    public void delete(long id){
        
        String sql = "DELETE from usuario  WHERE id = ?";        
        connection = null;
        try{
            connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setLong(1,id);
            
            stmt.execute();
            ConnectionFactory.closeConnection(connection,stmt);
            stmt.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Não foi possivel Deletar o usuário" + e.getMessage(),"ERRO",JOptionPane.WARNING_MESSAGE);
        }
        
        
    }
    
    public void update(Usuario usuario){
        if(usuario != null){
            String sql = "UPDATE usuario set nome = (?), cpf = (?), email = (?), telefone = (?) where id = (?)";
            
            try{
                PreparedStatement stmt = connection.prepareStatement(sql);
                
                stmt.setString(1,usuario.getNome());
                stmt.setString(2,usuario.getCpf());
                stmt.setString(3,usuario.getEmail());
                stmt.setString(4, usuario.getTelefone());
                stmt.setLong(5, usuario.getId());
                stmt.execute();
                stmt.close();
                
                
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,"Não foi possivel atualizar o contato no banco de dados " + e.getMessage(),"ERRO",JOptionPane.WARNING_MESSAGE);
            }
            
            
        } else {
            JOptionPane.showMessageDialog(null, "O contato enviado por parâmetro está vazio","AVISO!",JOptionPane.WARNING_MESSAGE);
        }
            
    }
    
    
    public long retorna(long id){
        System.out.println(id);
    return id;
}
    
    
    
    
    public Usuario selectById(long id){
        Usuario usuario = new Usuario();
        
        String sql = "SELECT * FROM projetojava.usuario WHERE id = (?)";
        
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                usuario.setId(rs.getLong("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefone(rs.getString("telefone"));
                
            }
            
            stmt.close();
            rs.close();
          
                
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Não foi possivel atualizar o contato no banco de dados " + e.getMessage(),"ERRO",JOptionPane.WARNING_MESSAGE);
        }
        
        return usuario;
    }
    
}