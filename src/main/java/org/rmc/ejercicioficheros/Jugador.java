package org.rmc.ejercicioficheros;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"dorsal", "nombre", "apellidos", "demarcacion", "salario"})
public class Jugador implements Serializable {

    private static final long serialVersionUID = 1L;

    private int dorsal;
    private String nombre;
    private String apellidos;
    private int demarcacion;
    private double salario;

    public Jugador() {
        dorsal = 0;
        nombre = null;
        apellidos = null;
        demarcacion = 0;
        salario = 0.0;
    }

    public Jugador(int dorsal, String nombre, String apellidos, int demarcacion, double salario) {
        this.dorsal = dorsal;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.demarcacion = demarcacion;
        this.salario = salario;
    }

    public int getDorsal() {
        return this.dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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

    @Override
    public String toString() {
        String[] demarc = {"base", "escolta", "alero", "ala pivot", "pivot"};
        return "Dorsal: " + this.dorsal + "\nNombre: " + this.nombre + "\nApellidos: "
                + this.apellidos + "\nDemarcación: " + demarc[this.demarcacion - 1] + "\nSalario: "
                + this.salario + "\n\n";
    }
}
