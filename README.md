# Ejercicio de manejo de ficheros

Ejercicio de menejo de ficheros para el tema 1 de la asignatura de Acceso a Datos de 2º de DAM.

### Instrucciones

Para el tema de manejo de ficheros se va a implementar la gestión basada en ficheros de los jugadores de un equipo de balonces a través de una aplicación en modo consola.
De los jugadores de un equipo de baloncesto se guardarán los siguientes datos:

    * Dorsal del jugador – Entero • Nombre del jugador – Array de 16 caracteres
    * Apellido del jugador - Array de 32 caracteres
    * Demarcación – Entero (1 – base, 2 – escolta, 3 – alero, 4 – ala ivot, 5 – pivot)
    * Salario – Número fraccionario de doble precisión

Teniendo en cuenta estos datos se necesita implementar las siguientes funcionalidades:

#### Inserción de un jugador en un fichero aleatorio.

    1. Consulta de los datos de un jugador en un fichero aleatorio.
    2. Inserción de los datos de jugador a partir de un fichero de texto (CSV)
    3. Modificación de los datos de un jugador.
    4. Borrado de un jugador.
    5. Imprimir por pantalla un listado de los jugadores insertados.
    6. Crear una Clase jugador y serializar todos los jugadores a XML utilizando JAXB.
    7. Exportar todos los jugadores a JSON.

A continuación, se detallan las particularidades de las funciones anteriormente mencionadas.

    1. Inserción de jugadores Para insertar un jugador se debe calcular la posición donde irá guardado el jugador dentro del fichero a partir del dorsal del jugador. Hay que tener en cuenta que mientras que los jugadores comenzarán a partir del ID 1 el fichero tiene el puntero al inicio en la posición 0. También hay que considerar que el jugador ya esté introducido dentro del fichero. Para calcular el tamaño de los datos de un jugadores hay que notar que en Java, el tipo entero ocupa 4 bytes, cada caracter ocupa 2 bytes y el tipo flotante de doble precisión ocupa 8 bytes. A partir de estos datos será posible realizar el cálculo de la posición donde el jugador va insertado dentro del fichero.  Los datos para insertar se solicitarán al usuario de la aplicación pidiéndolos uno por uno por línea de comandos.
    2. Consulta de un jugador Para consultar los datos de un jugador tenemos que recibir por pantalla el identificador del jugador del que queremos mostrar su información y acudir a la posición donde se guardan sus datos y mostrarlos.  En esta función tendremos que comprobar que el usuario existe y si no es así deberemos mostrar un mensaje por pantalla indicándolo.

    3. Insertar jugadores desde CSV Un fichero CSV es un fichero de texto plano donde los valores van separados por comas. A través de un fichero que se puede crear fácilmente con una hoja de cálculo se leerán los valores y se insertarán de forma masiva en el fichero aleatorio.

    4. Modificar datos de jugadores En la modificación de datos ofreceremos la posibilidad al usuario de modificar los datos de un jugador excepto el identificador que será permanente e indicará la posición del usuario dentro del fichero.

    5. Borrado de un jugador Para borrar un jugador se establecerán en el fichero una serie de caracteres que indiquen que el jugador ha sido borrado. Así, podemos establecer el ID a -1, rellenar con 0 el nombre, establecer a 0 tanto el departamento como el salario del jugador.

    6. Imprimir listado de jugadores En esta funcionalidad se recorrerá secuencialmente el fichero y se mostrarán todos aquellos jugadores insertados en el mismo. Se ha de considerar, que puedan existir jugadores que han sido borrados.

    7. Serializar la clase Jugador a XML utilizando JAXB Para serializar una clase mediante JAXB a XML tenemos que definir la Clase jugador en un nuevo fichero con los datos anteriormente mencionados. A partir de esta clase se creará otra clase que podemos llamar Listajugadores que representará mediante un ArrayList el conjunto de jugadores que tenemos guardado en el fichero aleatorio, además de un nombre de la empresa y la localización. La serialización de un objeto Listajugadores convertirá el nombre de la empresa junto con el conjunto de sus jugador a una representación XML.

    8. Exportar todos los jugadores a JSON Utilizar las librerías vistas en clase para exportar los jugadores a un fichero JSON.
