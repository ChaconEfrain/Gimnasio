/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.userTypeModel;
import java.sql.*;

/**
 *
 * @author Dell
 */
public class user_type {
    
    public void insert(userTypeModel user_data) throws SQLException{
        
    Connection conn = conexion.getConexion();
        
    
    String queryInsert = "INSERT INTO USER_TYPE (USER_TYPE) VALUES ('"+user_data.getUser_type()+"');";
    PreparedStatement ps = null;
        
        try{
            ps = conn.prepareStatement(queryInsert);
            ps.executeUpdate();
            System.out.println("Se ha agregado el usuario");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

