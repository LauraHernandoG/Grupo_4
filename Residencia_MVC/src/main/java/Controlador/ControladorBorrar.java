/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.AncianoDAO;
import Modelo.Entidades.Anciano;
import Vista.InterfazBorrar;
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
public class ControladorBorrar implements ActionListener {

    public InterfazBorrar vistaBorrar;
    public AncianoDAO modelo;

    public ControladorBorrar() {
    }

    public ControladorBorrar(InterfazBorrar vistaBorrar, AncianoDAO modelo) {
        this.vistaBorrar = vistaBorrar;
        this.modelo = modelo;

        this.vistaBorrar.setTitle("Borrar anciano");
        this.vistaBorrar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.vistaBorrar.setLocationRelativeTo(null);
        this.setImagen();
        this.vistaBorrar.setVisible(true);

        this.vistaBorrar.getBotonBorrar().addActionListener(this);

    }

    private void setImagen() {

        ImageIcon imagen = new ImageIcon("src/main/java/Resources/borrar.png");

        Image imagenEscalada = imagen.getImage().getScaledInstance(this.vistaBorrar.getBotonBorrar().getWidth(), this.vistaBorrar.getBotonBorrar().getHeight(), Image.SCALE_SMOOTH);

        Icon iconoEscalado = new ImageIcon(imagenEscalada);

        this.vistaBorrar.getBotonBorrar().setIcon(iconoEscalado);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("...." + vistaBorrar.getCajaDniBorrar().getText());
        
        if (e.getSource() == this.vistaBorrar.getBotonBorrar()) {

            System.out.println("...." + vistaBorrar.getCajaDniBorrar().getText());
            modelo.delete(vistaBorrar.getCajaDniBorrar().getText());
            JOptionPane.showMessageDialog(vistaBorrar, "Se ha borrado el anciano correctamente");

        }

    }

}
