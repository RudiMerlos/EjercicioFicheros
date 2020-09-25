package org.rmc.ejercicioficheros;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "equipo")
@XmlType(propOrder = {"nombre", "lugar", "lista"})
public class ListaJugadores {

    private String nombre;
    private String lugar;

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

    @XmlElement(name = "nombre")
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement(name = "localizacion")
    public String getLugar() {
        return this.lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    @XmlElementWrapper(name = "jugadores")
    @XmlElement(name = "jugador")
    public List<Jugador> getLista() {
        return this.lista;
    }

    public void setLista(List<Jugador> lista) {
        this.lista = lista;
    }
}
