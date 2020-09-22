package org.rmc.ejercicioficheros;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ListaJugadores {

    private String nombre;
    private String lugar;

    @XmlElementWrapper(name = "equipo")
    @XmlElement(name = "jugador")
    private List<Jugador> lista;

    public ListaJugadores() {
        nombre = null;
        lugar = null;
        lista = new ArrayList<>();
    }

    public ListaJugadores(String nombre, String lugar, List<Jugador> lista) {
        this.nombre = nombre;
        this.lugar = lugar;
        this.lista = lista;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugar() {
        return this.lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public List<Jugador> getLista() {
        return this.lista;
    }

    public void setLista(List<Jugador> lista) {
        this.lista = lista;
    }
}
