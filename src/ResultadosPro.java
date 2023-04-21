import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

public class ResultadosPro {
    public HashMap<Integer, HashMap<String, String>> pronosticos;//pronosticos es un HashMap que tiene un conjunto de pronósticos para diferentes partidos, donde cada pronóstico está representado por un HashMap (nombre, resultado)

    public ResultadosPro(){
        pronosticos = new HashMap<>();
    }

    public ResultadosPro cargarPronostico(String nombreArchivo) throws IOException {
        Path listaPro = Paths.get(nombreArchivo);
        Scanner lectorPro = new Scanner(listaPro);

        lectorPro.useDelimiter("[,;\\n]");

        ResultadosPro listaPronostico = new ResultadosPro();

        while (lectorPro.hasNextLine()) {
            String line = lectorPro.nextLine();//La cadena de caracteres leída se almacena en una variable llamada line, para que pueda ser procesada posteriormente.
            String[] parts = line.split(";");//El resultado es un arreglo de Strings llamado parts que contiene cada una de las partes separadas por el separador.
            int parPro = Integer.parseInt(parts[0]);//Se convierte el primer elemento del array de parts a un entero
            String nomPro = parts[2];
            String resPro = parts[1];

            Pronostico nuevo2 = new Pronostico(parPro, resPro, nomPro);//Genera nuevos Pronosticos
            listaPronostico.agregarPronostico(nuevo2);
        }

        return listaPronostico;

    }
    public void agregarPronostico(Pronostico pronostico) {
        int nPartido = pronostico.getnPartido();
        String nombre = pronostico.getNombre();
        String resultado = pronostico.getResultado();

        if (pronosticos.containsKey(nPartido)) {
            pronosticos.get(nPartido).put(nombre, resultado);//Si ya existe un partido con ese número, se añade el pronóstico del jugador (nombre y resultado) en el mapa interno correspondiente al partido
        } else {
            HashMap<String, String> jugadores = new HashMap<>();//Si no existe un partido con ese número, se crea un nuevo mapa "jugadores" con el nombre del jugador y su resultado y se añade al mapa "pronosticos" con la clave del número de partido.
            jugadores.put(nombre, resultado);
            pronosticos.put(nPartido, jugadores);
        }
    }

  }
