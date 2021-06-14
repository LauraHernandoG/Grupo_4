/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ngarc
 */
public class Conexion {
    
    private static Connection con;
    
    private static final String url = "jdbc:mysql://localhost:3306/residencia";
    private static final String login = "root";
    private static final String passwd = "";
    
    public static Connection abrirConexion() {
        
        try {
            con = DriverManager.getConnection(url, login, passwd);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return con;
        
    }
    
}
