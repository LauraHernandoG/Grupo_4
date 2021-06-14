/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.AncianoDAO;
import Modelo.Entidades.Anciano;
import Vista.InterfazBuscar;
import Vista.InterfazTabla;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author ngarc
 */
public class ControladorBuscar implements ActionListener{
    
    public InterfazBuscar vistaBuscar;
    public AncianoDAO modelo;

    public ControladorBuscar() {
    }

    public ControladorBuscar(InterfazBuscar vistaBuscar, AncianoDAO modelo) {
        this.vistaBuscar = vistaBuscar;
        this.modelo = modelo;
        
        this.vistaBuscar.setTitle("Borrar anciano");
        this.vistaBuscar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.vistaBuscar.setLocationRelativeTo(null);
        this.setImagen();
        this.vistaBuscar.setVisible(true);

        this.vistaBuscar.getBotonBuscar().addActionListener(this);
        
    }
    
    private void setImagen() {

        ImageIcon imagen = new ImageIcon("src/main/java/Resources/lupa.png");

        Image imagenEscalada = imagen.getImage().getScaledInstance(this.vistaBuscar.getBotonBuscar().getWidth(), this.vistaBuscar.getBotonBuscar().getHeight(), Image.SCALE_SMOOTH);

        Icon iconoEscalado = new ImageIcon(imagenEscalada);

        this.vistaBuscar.getBotonBuscar().setIcon(iconoEscalado);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == this.vistaBuscar.getBotonBuscar()) {
            
            Anciano a = new Anciano("",0,0,vistaBuscar.getCajaNombreBuscar().getText(), vistaBuscar.getCajaApellidosBuscar().getText());
//            AncianoDAO.list(a);
            JOptionPane.showMessageDialog(vistaBuscar, "Se ha borrado el anciano correctamente");
            
        }
        
        
    }
    
    
    
}
