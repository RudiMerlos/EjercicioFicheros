package org.rmc.ejercicioficheros;

import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
    }

    /**
     * Muestra el menú por pantalla.
     */
    private static void menu() {
        System.out.println("\n\n************************************************");
        System.out.println("************************************************");
        System.out.println("**                                            **");
        System.out.println("**              BBDD de jugadores             **");
        System.out.println("**                                            **");
        System.out.println("************************************************");
        System.out.println("************************************************\n");
        System.out.println("  1. Insertar un jugador nuevo");
        System.out.println("  2. Consultar datos de un jugador");
        System.out.println("  3. Importar jugadores desde CSV");
        System.out.println("  4. Modificar los datos de un jugador");
        System.out.println("  5. Borrar un jugador");
        System.out.println("  6. Listar jugadores");
        System.out.println("  7. Exportar a XML");
        System.out.println("  8. Exportar a JSON");
        System.out.println("  0. Salir");
        System.out.print("  ? ");
    }


    /**
     * Inserta un jugador y devuleve true si ha tenido éxito o false si el dorsal del jugador ya
     * está ocupado.
     * @param jugador El jugador que se quiere insertar.
     * @return boolean
     */
    private static boolean insertarJugador(Jugador jugador) {
        return false;
    }


    /**
     * Muestra los datos del jugador con el dorsal pasado como parámetro.
     * @param dorsal El dorsal del jugador del que se quieren obtener los datos.
     * @return Jugador null si no existe.
     */
    private static Jugador datosJugador(int dorsal) {
        return null;
    }


    /**
     * Inserta jugadores obtenidos desde un fichero CSV. Al final indica cuantos registros han sido
     * insertados y cuantos no han podido insertarse.
     * @param file Fichero CSV
     */
    private static void importarCSV(Path file) {

    }


    /**
     * Modifica los datos de un jugador a partir de su dorsal y devuelve true si ha tenido éxito o
     * false si el dorsal del jugador no existe.
     * @param dorsal El dorsal del jugador que se quiere modificar.
     * @return boolean
     */
    private static boolean modificarJugador(int dorsal) {
        return false;
    }


    /**
     * Borra un jugador y devuelve true si ha tenido éxito o false si el dorsal no existe.
     * @param dorsal El dorsal del jugador que se quiere borrar.
     * @return boolean
     */
    private static boolean borrarJugador(int dorsal) {
        return false;
    }


    /**
     * Devuelve una lista con todos los jugadores.
     * @return List<Jugador>
     */
    private static List<Jugador> listarJugadores() {
        return null;
    }


    /**
     * Exporta todos los jugadores a un fichero XML mediante JAXB.
     * @param file El fichero XML al cual se quieren exportar los jugadores.
     */
    private static void exportarXML(Path file) {

    }


    /**
     * Exporta todos los jugadores a un fichero JSON mediante Gson.
     * @param file El fichero JSON al cual se quieren exportar los jugadores.
     */
    private static void exportarJSON(Path file) {

    }
}
