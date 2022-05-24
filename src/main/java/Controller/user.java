/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.userModel;
import View.FrmUser;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class user {
    public void insertUser(userModel user_model) throws SQLException{
        
    Connection conn = conexion.getConexion();
        
    String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
    String date = formatter.format(user_model.getFechaNacimiento());
    String queryInsert = "INSERT INTO users (USER_NAME, USER_LASTNAME, USER_BIRTHDATE, INFO, USER_TYPE, PASSWORD) VALUES ('"+user_model.getName()+"', '"+user_model.getApellido()+"', '"+date+"', '"+user_model.getInfoExtra()+"', '"+user_model.getTipoDeUsuario()+"', '"+user_model.getPassword()+"');";
    PreparedStatement ps1 = null;    
        try{
            ps1 = conn.prepareStatement(queryInsert);
            ps1.executeUpdate();
            System.out.println("Se ha agregado el usuario");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public userModel login(userModel user_model) throws SQLException{
        
        userModel datosBD = new userModel(); 
        Connection conn = conexion.getConexion();
        
        Statement st1 = conn.createStatement();
        String query = "SELECT * FROM USERS WHERE USER_NAME = '"+user_model.getName()+"' AND PASSWORD = '"+user_model.getPassword()+"';";
        ResultSet datos = st1.executeQuery(query);
        //datos.last();
        if(datos.next()){
            String name = datos.getString("USER_NAME");
            datosBD.setName(name);
            String pass = datos.getString("PASSWORD");
            datosBD.setPassword(pass);
        }else{
            datosBD = null;
        }
        return datosBD;
    }
    public ArrayList<userModel> usersData () throws SQLException{
        
        Connection conn = conexion.getConexion();
        ArrayList<userModel> list = new ArrayList<userModel>();
        userModel user;
        String query = "SELECT * FROM users";
        
        try{
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(query);
            
            while(res.next()){
                user = new userModel();
                user.setId(Integer.parseInt(res.getString("ID")));
                user.setName(res.getString("USER_NAME"));
                user.setApellido(res.getString("USER_LASTNAME"));
                user.setFechaNacimiento(res.getDate("USER_BIRTHDATE"));
                user.setInfoExtra(res.getString("INFO"));
                user.setTipoDeUsuario(Integer.parseInt(res.getString("USER_TYPE")));
                user.setPassword(res.getString("PASSWORD"));
                list.add(user);
            }
            res.close();
            stm.close();
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al consultar", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return list;
    }
    public void update(userModel user_Model) throws SQLException{
        Connection conn = conexion.getConexion();
        
        PreparedStatement stm = null;
        String query = "UPDATE USERS SET INFO = '"+user_Model.getInfoExtra()+"' WHERE ID = '"+user_Model.getId()+"'";
        
        try{
            stm = conn.prepareStatement(query);
            stm.executeUpdate();
            System.out.println("Se ha actualizado la información del usuario");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
