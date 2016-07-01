package musa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class conectaBanco {

    public Connection connection = null;

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/bdmusa", "root", "");
            System.out.println("Conectado com sucesso.");
            return this.connection;

        } catch (ClassNotFoundException ex) {
            return this.connection;
        }

    }

    
    /*public static String getObras(){
        Connection hue = new conectaBanco().getConnection();
        String sql = "";
    
        PRECISO COLOCAR A ID DO USUARIO AUTENTICADO NA OBRA;
        PRECISO RETORNAR TODAS OBRAS DO USUARIO AUTENTICADO;
    
        PreparedStatement ps = hue.prepareStatement(sql);
        
        try{
            
        }catch{
        
        }
        return 
    
    }*/
    
    
    public boolean consulta(String usuario, String senha) throws SQLException {
        Connection hue = new conectaBanco().getConnection();

        String sql = "SELECT * FROM usuarios_musa";
        PreparedStatement ps = hue.prepareStatement(sql);
        boolean autenticado = false;

        ResultSet rs;

        String login_usuario = usuario;
        String senha_usuario = senha;

        String nome_db;
        String senha_db;

        try {

            rs = ps.executeQuery();

            while (rs.next()) {
                nome_db = rs.getString("login_usuarios");
                senha_db = rs.getString("senha_usuarios");
                if (login_usuario.equals(nome_db) && senha_usuario.equals(senha_db)) {
                    
                    autenticado = true;
                    
                    break;
                }

            }

            ps.close();
            hue.close();
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }

        return autenticado;

    }
}
