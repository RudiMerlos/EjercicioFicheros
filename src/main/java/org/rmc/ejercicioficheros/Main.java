package org.rmc.ejercicioficheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

    // Directorio raíz donde se almacenarán los ficheros generados
    private static final String ROOT = "src/main/resources/";

    // Nombre del fichero de acceso aleatorio que contendrá los jugadores
    private static final String FILE = "jugadores.dat";

    // Fichero
    private static final Path PATH = Paths.get(ROOT, FILE);

    // Tamaña en bytes de cada línea en el fichero de acceso aleatorio
    private static final int SIZE = 112;

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int option = 0;
        int dorsal;
        String file;

        do {
            menu();
            option = sc.nextInt();
            switch (option) {
                case 1: // INSERTAR JUGADOR
                    System.out.println("\n  NUEVO JUGADOR");
                    System.out.println("-----------------");
                    insertarJugador(introducirDatosJugador());
                    break;
                case 2: // MOSTRAR DATOS DE JUGADOR
                    System.out.println("\n  DATOS DEL JUGADOR");
                    System.out.println("---------------------");
                    System.out.print("\nDorsal: ");
                    dorsal = sc.nextInt();
                    Jugador datosJugador = datosJugador(dorsal);
                    if (datosJugador != null) {
                        System.out.println("\n" + datosJugador);
                    } else {
                        System.out.println("\nNo existe el jugador en la base de datos");
                    }
                    break;
                case 3: // IMPORTAR DESDE CSV
                    System.out.println("\n  IMPORTAR JUGADORES DESDE CSV");
                    System.out.println("--------------------------------");
                    System.out.print("Fichero CSV: ");
                    sc.nextLine(); // flush buffer
                    file = sc.nextLine();
                    importarCSV(Paths.get(ROOT, file));
                    break;
                case 4: // MODIFICAR JUGADOR
                    System.out.println("\n  MODIFICAR JUGADOR");
                    System.out.println("---------------------");
                    System.out.print("\nDorsal: ");
                    dorsal = sc.nextInt();
                    Jugador modificaJugador = datosJugador(dorsal);
                    if (modificaJugador != null) {
                        modificarJugador(modificaJugador);
                    } else {
                        System.out.println("\nNo existe el jugador en la base de datos");
                    }
                    break;
                case 5: // BORRAR JUGADOR
                    System.out.println("\n  BORRAR JUGADOR");
                    System.out.println("------------------");
                    System.out.print("\nDorsal: ");
                    dorsal = sc.nextInt();
                    if (borrarJugador(dorsal)) {
                        System.out.println("\nJugador eliminado");
                    } else {
                        System.out.println("\nNo se ha podido eliminar el jugador");
                    }
                    break;
                case 6: // LISTAR JUGADORES
                    System.out.println("\n  LISTA DE JUGADORES");
                    System.out.println("----------------------");
                    List<Jugador> list = listarJugadores();
                    if (!list.isEmpty()) {
                        list.forEach(System.out::println);
                    }
                    break;
                case 7: // EXPORTAR A XML
                    System.out.println("\n  EXPORTAR A XML");
                    System.out.println("------------------");
                    System.out.print("Fichero XML: ");
                    sc.nextLine(); // flush buffer
                    file = sc.nextLine();
                    exportarXML(Paths.get(ROOT, file));
                    break;
                case 8: // EXPORTAR A JSON
                    System.out.println("\n  EXPORTAR A JSON");
                    System.out.println("-------------------");
                    System.out.print("Fichero JSON: ");
                    sc.nextLine(); // flush buffer
                    file = sc.nextLine();
                    exportarJSON(Paths.get(ROOT, file));
                    break;
                case 0: // SALIR
                    System.out.println("\n¡¡¡HASTA PRONTO!!!");
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
     * Permite introducir los datos de un jugador.
     *
     * @return El objeto Jugador
     */
    private static Jugador introducirDatosJugador() {
        int dorsal;
        StringBuffer nombre;
        StringBuffer apellidos;
        int demarcacion;
        double salario;

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

        return new Jugador(dorsal, new String(nombre), new String(apellidos), demarcacion, salario);
    }


    /**
     * Solicita los datos de un jugador y lo inserta en el fichero.
     */
    private static void insertarJugador(Jugador jugador) {
        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile(PATH.toFile(), "rw");

            int pos = (jugador.getDorsal() - 1) * SIZE;
            file.seek(pos);

            if (file.length() > 0) {
                if (pos > file.length()) { // Si es el dorsal más alto
                    file.setLength(pos + SIZE); // se dimensiona el fichero
                } else {
                    if (file.readInt() == jugador.getDorsal()) {
                        System.out.println(
                                "\nNo se ha podido insertar el jugador porque ya existe el dorsal "
                                        + jugador.getDorsal());
                        file.close();
                        return;
                    }
                    file.seek(pos); // Si no está ocupado el dorsal, se vuelve a la posición
                }
            }

            file.writeInt(jugador.getDorsal());
            file.writeChars(jugador.getNombre());
            file.writeChars(jugador.getApellidos());
            file.writeInt(jugador.getDemarcacion());
            file.writeDouble(jugador.getSalario());
            System.out.println("\nJugador insertado con éxito!");

        } catch (FileNotFoundException e) {
            System.err.println("\nError al abrir el fichero: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("\nError de I/O: " + e.getMessage());
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    System.err.println("\nError al cerrar el fichero: " + e.getMessage());
                }
            }
        }
    }


    /**
     * Muestra los datos del jugador con el dorsal pasado como parámetro.
     *
     * @param dorsal El dorsal del jugador del que se quieren obtener los datos.
     * @return Jugador null si no existe.
     */
    private static Jugador datosJugador(int id) {
        if (!Files.exists(PATH)) {
            return null;
        }

        Jugador jugador = null;
        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile(PATH.toFile(), "r");
            int pos = (id - 1) * SIZE;
            if (pos < file.length() && pos > 0) {
                file.seek(pos);
                int dorsal;
                if ((dorsal = file.readInt()) == id) {
                    char[] nombre = new char[16];
                    for (int i = 0; i < nombre.length; ++i) {
                        nombre[i] = file.readChar();
                    }
                    char[] apellidos = new char[32];
                    for (int i = 0; i < apellidos.length; ++i) {
                        apellidos[i] = file.readChar();
                    }
                    int demarcacion = file.readInt();
                    double salario = file.readDouble();
                    jugador = new Jugador(dorsal, new String(nombre), new String(apellidos),
                            demarcacion, salario);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("\nFichero no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("\nError de I/O: " + e.getMessage());
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    System.err.println("\nError al cerrar el fichero: " + e.getMessage());
                }
            }
        }
        return jugador;
    }


    /**
     * Inserta jugadores obtenidos desde un fichero CSV. Al final indica cuantos registros han sido
     * insertados y cuantos no han podido insertarse.
     *
     * @param file Fichero CSV
     */
    private static void importarCSV(Path file) {
        if (!Files.exists(file)) {
            System.err.println("\nNo existe el fichero " + file.toString());
            return;
        }

        try {
            BufferedReader reader = Files.newBufferedReader(file);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] jug = line.split(",");
                StringBuffer nombre = new StringBuffer(jug[1]);
                nombre.setLength(16);
                StringBuffer apellidos = new StringBuffer(jug[2]);
                apellidos.setLength(32);
                insertarJugador(new Jugador(Integer.parseInt(jug[0]), nombre.toString(),
                        apellidos.toString(), Integer.parseInt(jug[3]),
                        Double.parseDouble(jug[4])));
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("\nError de I/O: " + e.getMessage());
        }
    }


    /**
     * Modifica los datos de un jugador a partir de su dorsal y devuelve true si ha tenido éxito o
     * false si el dorsal del jugador no existe.
     *
     * @param dorsal El dorsal del jugador que se quiere modificar.
     * @return boolean
     */
    private static void modificarJugador(Jugador jugador) {
        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile(PATH.toFile(), "rw");
            int pos = (jugador.getDorsal() - 1) * SIZE;
            file.seek(pos + 4); // Posición del nombre

            StringBuffer nombre;
            StringBuffer apellidos;
            int demarcacion;
            double salario;

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

            file.writeChars(nombre.toString());
            file.writeChars(apellidos.toString());
            file.writeInt(demarcacion);
            file.writeDouble(salario);
            System.out.println("\nJugador modificado con éxito!");
        } catch (FileNotFoundException e) {
            System.err.println("\nFichero no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("\nError de I/O: " + e.getMessage());
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    System.err.println("\nError al cerrar el fichero: " + e.getMessage());
                }
            }
        }
    }


    /**
     * Borra un jugador y devuelve true si ha tenido éxito o false si el dorsal no existe.
     *
     * @param dorsal El dorsal del jugador que se quiere borrar.
     * @return boolean
     */
    private static boolean borrarJugador(int id) {
        if (!Files.exists(PATH)) {
            return false;
        }

        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile(PATH.toFile(), "rw");
            int pos = (id - 1) * SIZE;
            if (pos < file.length() && pos > 0) {
                file.seek(pos);
                if (file.readInt() == id) {
                    file.seek(pos);
                    file.write(new byte[SIZE]);
                    file.close();
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("\nFichero no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("\nError de I/O: " + e.getMessage());
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    System.err.println("\nError al cerrar el fichero: " + e.getMessage());
                }
            }
        }
        return false;
    }


    /**
     * Devuelve una lista con todos los jugadores.
     *
     * @return List<Jugador>
     */
    private static List<Jugador> listarJugadores() {
        List<Jugador> lista = new ArrayList<>();

        if (!Files.exists(PATH)) {
            System.err.println("\nNo existen jugadores en la base de datos.");
        } else {
            RandomAccessFile file = null;
            try {
                file = new RandomAccessFile(PATH.toFile(), "r");

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
                        lista.add(new Jugador(dorsal, new String(nombre).trim(),
                                new String(apellidos).trim(), demarcacion, salario));
                    }
                    pos += SIZE;
                }
            } catch (FileNotFoundException e) {
                System.err.println("\nFichero no encontrado: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("\nError de I/O: " + e.getMessage());
            } finally {
                if (file != null) {
                    try {
                        file.close();
                    } catch (IOException e) {
                        System.err.println("\nError al cerrar el fichero: " + e.getMessage());
                    }
                }
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
        System.out.print("Nombre del equipo: ");
        String equipo = sc.nextLine();
        System.out.print("Localización: ");
        String lugar = sc.nextLine();
        ListaJugadores jugadores = new ListaJugadores(equipo, lugar, listarJugadores());

        try {
            JAXBContext context = JAXBContext.newInstance(ListaJugadores.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(jugadores, Files.newOutputStream(file));
        } catch (JAXBException e) {
            System.err.println("\nError al parsear: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("\nError de escritura: " + e.getMessage());
        }
    }


    /**
     * Exporta todos los jugadores a un fichero JSON mediante Gson.
     *
     * @param file El fichero JSON al cual se quieren exportar los jugadores.
     */
    private static void exportarJSON(Path file) {
        System.out.print("Nombre del equipo: ");
        String equipo = sc.nextLine();
        System.out.print("Localización: ");
        String lugar = sc.nextLine();
        ListaJugadores jugadores = new ListaJugadores(equipo, lugar, listarJugadores());

        try {
            BufferedWriter writer = Files.newBufferedWriter(file, StandardOpenOption.CREATE);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(jugadores);
            writer.write(json, 0, json.length());
            writer.close();
        } catch (IOException e) {
            System.err.println("\nError de escritura: " + e.getMessage());
        }
    }
}
