/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import java.sql.*;

/**
 *
 * @author Dell
 */
public class conexion {
    
    public static Connection getConexion(){
        
    final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    Connection conn = null;
    Statement stmt = null;
    final String DB_URL = "jdbc:mysql://localhost:3306/gym?useSSL=false&serverTimezone=UTC";
    
    // El nombre de usuario y la contraseña de la base de datos, deben configurarse de acuerdo con los suyos.
    final String USER = "root";
    final String PASS = "Efrain21";
    
	try {
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		System.out.println("Conectarse al controlador de la base de datos correctamente");
                return conn;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
                return null;
	}
}
}

