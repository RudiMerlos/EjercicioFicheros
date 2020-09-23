package org.rmc.ejercicioficheros;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    // Directorio raíz donde se almacenarán los ficheros generados
    private static final String ROOT = "src/main/resources/";

    // Nombre del fichero de acceso aleatorio que contendrá los jugadores
    private static final String FILE = "jugadores.dat";

    // Tamaña en bytes de cada línea en el fichero de acceso aleatorio
    private static final int SIZE = 112;

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int option = 0;

        do {
            menu();
            option = sc.nextInt();
            switch (option) {
                case 1:
                    insertarJugador();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    List<Jugador> list = listarJugadores();
                    if (!list.isEmpty()) {
                        list.forEach(System.out::println);
                    }
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 0:
                    System.out.println("\nHASTA PRONTO!!!");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        } while (option != 0);
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
     * Solicita los datos de un jugador y lo inserta en el fichero.
     */
    private static void insertarJugador() {
        int dorsal;
        StringBuffer nombre;
        StringBuffer apellidos;
        int demarcacion;
        double salario;

        System.out.println("\n  NUEVO JUGADOR");
        System.out.println("-----------------");
        System.out.print("Dorsal (1 - 99): ");
        dorsal = sc.nextInt();
        while (dorsal < 1 || dorsal > 99) {
            System.out.print("Introduzca un dorsal correcto: ");
            dorsal = sc.nextInt();
        }

        System.out.print("Nombre: ");
        sc.nextLine(); // flush buffer
        String str = sc.nextLine();
        nombre = new StringBuffer(str);
        nombre.setLength(16);

        System.out.print("Apellidos: ");
        str = sc.nextLine();
        apellidos = new StringBuffer(str);
        apellidos.setLength(32);

        System.out.print(
                "Demarcación (1 – base, 2 – escolta, 3 – alero, 4 – ala pivot, 5 – pivot): ");
        demarcacion = sc.nextInt();
        while (demarcacion < 1 || demarcacion > 5) {
            System.out.print("Introduzca una posición correcta: ");
            demarcacion = sc.nextInt();
        }

        System.out.print("Sueldo: ");
        salario = sc.nextDouble();
        while (salario < 0) {
            System.out.print("Introduzca un valor positivo: ");
            salario = sc.nextDouble();
        }

        try {
            RandomAccessFile file = new RandomAccessFile(ROOT + FILE, "rw");

            // TODO write file

            file.close();
        } catch (FileNotFoundException e) {
            System.err.println("\nError al abrir el fichero: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("\nError de I/O: " + e.getMessage());
        }
    }


    /**
     * Muestra los datos del jugador con el dorsal pasado como parámetro.
     *
     * @param dorsal El dorsal del jugador del que se quieren obtener los datos.
     * @return Jugador null si no existe.
     */
    private static Jugador datosJugador(int dorsal) {
        return null;
    }


    /**
     * Inserta jugadores obtenidos desde un fichero CSV. Al final indica cuantos registros han sido
     * insertados y cuantos no han podido insertarse.
     *
     * @param file Fichero CSV
     */
    private static void importarCSV(Path file) {

    }


    /**
     * Modifica los datos de un jugador a partir de su dorsal y devuelve true si ha tenido éxito o
     * false si el dorsal del jugador no existe.
     *
     * @param dorsal El dorsal del jugador que se quiere modificar.
     * @return boolean
     */
    private static boolean modificarJugador(int dorsal) {
        return false;
    }


    /**
     * Borra un jugador y devuelve true si ha tenido éxito o false si el dorsal no existe.
     *
     * @param dorsal El dorsal del jugador que se quiere borrar.
     * @return boolean
     */
    private static boolean borrarJugador(int dorsal) {
        return false;
    }


    /**
     * Devuelve una lista con todos los jugadores.
     *
     * @return List<Jugador>
     */
    private static List<Jugador> listarJugadores() {
        Path path = Paths.get(ROOT, FILE);
        List<Jugador> lista = new ArrayList<>();

        if (!Files.exists(path)) {
            System.err.println("\nNo existen jugadores en la base de datos.");
        } else {
            try {
                RandomAccessFile file = new RandomAccessFile(path.toFile(), "r");

                int pos = 0;
                int dorsal, demarcacion;
                char[] nombre = new char[16];
                char[] apellidos = new char[32];
                double salario;
                while (pos < file.length()) {
                    file.seek(pos);
                    dorsal = file.readInt();
                    if (dorsal > 0) {
                        for (int i = 0; i < nombre.length; ++i) {
                            nombre[i] = file.readChar();
                        }
                        for (int i = 0; i < apellidos.length; ++i) {
                            apellidos[i] = file.readChar();
                        }
                        demarcacion = file.readInt();
                        salario = file.readDouble();
                        lista.add(new Jugador(dorsal, new String(nombre), new String(apellidos),
                                demarcacion, salario));
                    }
                    pos += SIZE;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }


    /**
     * Exporta todos los jugadores a un fichero XML mediante JAXB.
     *
     * @param file El fichero XML al cual se quieren exportar los jugadores.
     */
    private static void exportarXML(Path file) {

    }


    /**
     * Exporta todos los jugadores a un fichero JSON mediante Gson.
     *
     * @param file El fichero JSON al cual se quieren exportar los jugadores.
     */
    private static void exportarJSON(Path file) {

    }
}
