/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.Entidades.Anciano;
import Resources.Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author danip
 */
public class AncianoDAO {

    private String rutaDB = "//localhost:3306/residencia";
    private String login = "root";
    private String passwd = "";
    private javax.swing.JTable jTable1;
    private DefaultTableModel modeloTabla;
    private ArrayList<Integer> filaUpdated = new ArrayList<Integer>();

    public int insert(Anciano anciano) {

        String sql = "INSERT INTO anciano (NOMBRE_ANCIANO,APELLIDOS_ANCIANOS,DNI_ANCIANO,NUM_HABITACION,PLANTA_HABITACION) VALUES (?,?,?,?,?)";

        try (PreparedStatement ps = Conexion.abrirConexion().prepareStatement(sql)) {

            ps.setString(1, anciano.getNombre());
            ps.setString(2, anciano.getApellidos());
            ps.setString(3, anciano.getDni_anciano());
            ps.setInt(4, anciano.getNum_habitacion());
            ps.setInt(5, anciano.getPlanta_habitacion());

            return ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return -1;

    }

    public int delete(String dniAnciano) {

        System.out.println("........");
        String sql = "DELETE FROM anciano WHERE DNI_ANCIANO=?";

        try (PreparedStatement ps = Conexion.abrirConexion().prepareStatement(sql)) {

            ps.setString(1, dniAnciano);

            return ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return -1;

    }

    public int update(Anciano anciano) {

        String sql = "update anciano set NOMBRE_ANCIANO=?,APELLIDOS_ANCIANOS=?,NUM_HABITACION=?,PLANTA_HABITACION=? where DNI_ANCIANO=?";

        try (PreparedStatement ps = Conexion.abrirConexion().prepareStatement(sql);) {

            ps.setString(1, anciano.getNombre());
            ps.setString(2, anciano.getApellidos());
            ps.setDouble(3, anciano.getNum_habitacion());
            ps.setInt(4, anciano.getPlanta_habitacion());
            ps.setString(5, anciano.getDni_anciano());

            return ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return -1;

    }
    
    public List<Anciano> list() {
        
        List<Anciano> lista = new ArrayList<>();
        String sql = "select * from anciano";
        
        try (PreparedStatement ps = Conexion.abrirConexion().prepareStatement(sql);) {

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                
                Anciano anciano = new Anciano();
                anciano.setDni_anciano(rs.getString(1));
                anciano.setNum_habitacion(rs.getInt(2));
                anciano.setPlanta_habitacion(rs.getInt(3));
                anciano.setNombre(rs.getString(4));
                anciano.setApellidos(rs.getString(5));
                lista.add(anciano);
                
            }
            
            return lista;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return lista;
        
    }
    
    

}
