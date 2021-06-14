/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainApp;

import Controlador.ControladorBorrar;
import Controlador.ControladorBuscar;
import Controlador.ControladorInterfaz1;
import Modelo.DAO.AncianoDAO;
import Vista.Interfaz1;
import Vista.InterfazBorrar;
import Vista.InterfazBuscar;
import Vista.InterfazTabla;

/**
 *
 * @author ngarc
 */
public class AppResidencia {

    public static void main(String[] args) {

        AncianoDAO anciano = new AncianoDAO();

        Interfaz1 interfazmain = new Interfaz1();
        InterfazBorrar vistaBorrar = new InterfazBorrar(interfazmain, true);
        InterfazBuscar vistaBuscar = new InterfazBuscar(interfazmain, true);
        InterfazTabla tablaBuscar = new InterfazTabla(interfazmain, true);

        ControladorInterfaz1 controladorMain = new ControladorInterfaz1(anciano, interfazmain, vistaBorrar, vistaBuscar, tablaBuscar);

    }

}
