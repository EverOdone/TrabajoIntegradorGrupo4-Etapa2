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

        String nombreModificado = nombre; // Inicializa nombreModificado con el valor original

        boolean nombreExiste = pronosticos.containsKey(nPartido) && pronosticos.get(nPartido).containsKey(nombre);//Si ambas condiciones son verdaderas, significa que ya existe un pronóstico para el partido y el nombre.
        if (nombreExiste) { // Si el nombre original ya existe, agregar sufijo para hacerlo diferente
            nombreModificado = nombre + " (2)";
            int i = 2;
            while (pronosticos.containsKey(nPartido) && pronosticos.get(nPartido).containsKey(nombreModificado)) { //Si el nombre del jugador ya está registrado se le agrega un número para crear un nombre modificado.
                nombreModificado = nombre + " (" + i + ")" ;
                i++;
            }
        }
        if (pronosticos.containsKey(nPartido)) {
            pronosticos.get(nPartido).put(nombreModificado, resultado);
        } else {
            HashMap<String, String> jugadores = new HashMap<>();
            jugadores.put(nombreModificado, resultado);
            pronosticos.put(nPartido, jugadores);
        }
    }

}
