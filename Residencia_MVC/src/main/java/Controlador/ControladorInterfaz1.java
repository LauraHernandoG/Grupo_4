/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.AncianoDAO;
import Modelo.Entidades.Anciano;
import Resources.Conexion;
import Vista.Interfaz1;
import Vista.InterfazBorrar;
import Vista.InterfazBuscar;
import Vista.InterfazTabla;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ngarc
 */
public class ControladorInterfaz1 implements ActionListener {

    public Interfaz1 vista;
    public InterfazBorrar vistaBorrar;
    public InterfazBuscar vistaBuscar;
    public InterfazTabla tablaBuscar;

    public AncianoDAO modelo;

    public List<Anciano> lista = new ArrayList<>();

    public ControladorInterfaz1() {
    }

    public ControladorInterfaz1(AncianoDAO modelo, Interfaz1 vista, InterfazBorrar vistaBorrar, InterfazBuscar vistaBuscar, InterfazTabla tablaBuscar) {
        this.modelo = modelo;
        this.vista = vista;
        this.vistaBorrar = vistaBorrar;
        this.vistaBuscar = vistaBuscar;
        this.tablaBuscar = tablaBuscar;
        this.lista = modelo.list();

        this.vista.setTitle("RESIDENCIA");
        this.vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);

        this.vista.getBotonAñadir().addActionListener(this);
        this.vista.getBotonModificar().addActionListener(this);
        this.vista.getBotonVentanaBorrar().addActionListener(this);
        this.vista.getBotonVentanaBuscar().addActionListener(this);
        this.vistaBorrar.getBotonBorrar().addActionListener(this);

    }

    private void setImagenBorrar() {

        ImageIcon imagen = new ImageIcon("src/main/java/Resources/borrar.png");

        Image imagenEscalada = imagen.getImage().getScaledInstance(this.vistaBorrar.getBotonBorrar().getWidth(), this.vistaBorrar.getBotonBorrar().getHeight(), Image.SCALE_SMOOTH);

        Icon iconoEscalado = new ImageIcon(imagenEscalada);

        this.vistaBorrar.getBotonBorrar().setIcon(iconoEscalado);

    }

    private void setImagenBuscar() {

        ImageIcon imagen = new ImageIcon("src/main/java/Resources/lupa.png");

        Image imagenEscalada = imagen.getImage().getScaledInstance(this.vistaBuscar.getBotonBuscar().getWidth(), this.vistaBuscar.getBotonBuscar().getHeight(), Image.SCALE_SMOOTH);

        Icon iconoEscalado = new ImageIcon(imagenEscalada);

        this.vistaBuscar.getBotonBuscar().setIcon(iconoEscalado);

    }

    private int buscarBD(Anciano anciano) {

        int filas = modelo.list().size();
        int cont = 0;

        for (int i = 0; i < filas; i++) {

            String nombre = this.lista.get(i).getNombre();
            String apellidos = this.lista.get(i).getApellidos();

            if (!anciano.getNombre().isEmpty() && !anciano.getApellidos().isEmpty()) {

                if (nombre.toLowerCase().contains(anciano.getNombre().toLowerCase()) && apellidos.toLowerCase().contains(anciano.getApellidos().toLowerCase())) {

                    this.lista.add(anciano);
                    cont++;

                }

            } else if ((!anciano.getNombre().isEmpty() && nombre.toLowerCase().contains(anciano.getNombre().toLowerCase()))
                    || (!anciano.getApellidos().isEmpty() && apellidos.toLowerCase().contains(anciano.getApellidos().toLowerCase()))) {

                this.lista.add(anciano);
                cont++;

            }
        }

        return cont;

    }

    private Object[][] getDatos() {
        Object[][] obj = null;
        try ( Connection con = Conexion.abrirConexion()) {
            String sql = "SELECT * FROM anciano";
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery(sql);

            ResultSetMetaData rsmd = rs.getMetaData();
            rs.last();
            int numCols = rsmd.getColumnCount();
            int numFils = rs.getRow();

            obj = new Object[numFils][numCols];
            int j = 0;
            rs.beforeFirst();
            while (rs.next()) {
                for (int i = 0; i < numCols; i++) {
                    obj[j][i] = rs.getObject(i + 1);
                }
                j++;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return obj;
    }

    private String[] getColumnas() {
        return new String[]{"DNI", "Num_habitacion", "Planta_habitacion", "Fecha estado", "Nombre", "Apellidos", "Telefono"};
    }

    private void setTabla() {

        JTable tabla = this.tablaBuscar.getTablaAncianos();

        tabla.setSelectionBackground(java.awt.Color.yellow); //cambio color de selección
        //creo tabla con modelo
        tabla.setModel(new DefaultTableModel(getDatos(), getColumnas()));
        this.tablaBuscar.modeloTable = (DefaultTableModel) tabla.getModel();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.vista.getBotonAñadir()) {

            Anciano a = new Anciano(vista.getCajaDni().getText(), Integer.parseInt(vista.getCajaNumHab().getText()), Integer.parseInt(vista.getCajaPlantaHab().getText()), vista.getCajaNombre().getText(), vista.getCajaApellidos().getText());
            modelo.insert(a);
            JOptionPane.showMessageDialog(vista, "Insercion exitosa");

        }

        if (e.getSource() == this.vista.getBotonModificar()) {

            Anciano a = new Anciano(vista.getCajaDni().getText(), Integer.parseInt(vista.getCajaNumHab().getText()), Integer.parseInt(vista.getCajaPlantaHab().getText()), vista.getCajaNombre().getText(), vista.getCajaApellidos().getText());
            modelo.update(a);
            JOptionPane.showMessageDialog(vista, "Modificacion exitosa");

        }

        if (e.getSource() == this.vista.getBotonVentanaBorrar()) {

            this.vistaBorrar.setTitle("Borrar anciano");
            this.vistaBorrar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.vistaBorrar.setLocationRelativeTo(null);
            setImagenBorrar();
            this.vistaBorrar.setVisible(true);

        }

        if (e.getSource() == this.vista.getBotonVentanaBuscar()) {

            ControladorBuscar conBuscar = new ControladorBuscar(new InterfazBuscar(vista, true), modelo);

        }

        if (e.getSource() == this.vistaBorrar.getBotonBorrar()) {

            modelo.delete(vistaBorrar.getCajaDniBorrar().getText());
            JOptionPane.showMessageDialog(vistaBorrar, "Se ha borrado el anciano correctamente");

        }

        if (e.getSource() == this.vistaBuscar.getBotonBuscar()) {

            String nombre = this.vistaBuscar.getCajaNombreBuscar().getText();
            String apellidos = this.vistaBuscar.getCajaApellidosBuscar().getText();
            Anciano anciano = new Anciano(nombre, apellidos);

            if (nombre.isEmpty() && apellidos.isEmpty()) {
                JOptionPane.showMessageDialog(vistaBuscar, "Debe especificar criterios de búsqueda");
            } else {
                int coincidencias = buscarBD(anciano);
                if (coincidencias > 0) {

                    this.tablaBuscar.setTitle("Resultado busqueda");
                    this.tablaBuscar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    this.tablaBuscar.setLocationRelativeTo(null);
                    
                    int cu=0;
                    for(Anciano a : lista)  {
                        

                        
                    }
                    
                    this.tablaBuscar.setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(vistaBuscar, "Ningún coincidente");
                }
            }

        }

    }

}
