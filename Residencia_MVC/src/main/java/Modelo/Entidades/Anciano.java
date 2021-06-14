/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Entidades;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author danip
 */
public class Anciano {

    public String dni_anciano;
    public int num_habitacion;
    public int planta_habitacion;
    public String nombre;
    public String apellidos;

    public Anciano() {
    }

    public Anciano(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Anciano(String dni_anciano, int num_habitacion, int planta_habitacion, String nombre, String apellidos) {
        this.dni_anciano = dni_anciano;
        this.num_habitacion = num_habitacion;
        this.planta_habitacion = planta_habitacion;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public String getDni_anciano() {
        return dni_anciano;
    }

    public void setDni_anciano(String dni_anciano) {
        this.dni_anciano = dni_anciano;
    }

    public int getNum_habitacion() {
        return num_habitacion;
    }

    public void setNum_habitacion(int num_habitacion) {
        this.num_habitacion = num_habitacion;
    }

    public int getPlanta_habitacion() {
        return planta_habitacion;
    }

    public void setPlanta_habitacion(int planta_habitacion) {
        this.planta_habitacion = planta_habitacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

}
