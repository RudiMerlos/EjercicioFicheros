package org.rmc.ejercicioficheros;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"dorsal", "nombre", "apellidos", "demarcacion", "salario"})
public class Jugador implements Serializable {

    private static final long serialVersionUID = 1L;

    private int dorsal;
    private char[] nombre;
    private char[] apellidos;
    private int demarcacion;
    private double salario;

    public Jugador() {
        dorsal = 0;
        nombre = new char[16];
        apellidos = new char[32];
        demarcacion = 0;
        salario = 0.0;
    }

    public Jugador(int dorsal, char[] nombre, char[] apellido, int demarcacion, double salario) {
        this.dorsal = dorsal;
        this.nombre = nombre;
        this.apellidos = apellido;
        this.demarcacion = demarcacion;
        this.salario = salario;
    }

    public int getDorsal() {
        return this.dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public char[] getNombre() {
        return this.nombre;
    }

    public void setNombre(char[] nombre) {
        this.nombre = nombre;
    }

    public char[] getApellido() {
        return this.apellidos;
    }

    public void setApellido(char[] apellido) {
        this.apellidos = apellido;
    }

    public int getDemarcacion() {
        return this.demarcacion;
    }

    public void setDemarcacion(int demarcacion) {
        this.demarcacion = demarcacion;
    }

    public double getSalario() {
        return this.salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
