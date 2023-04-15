import java.util.HashMap;

public class ResultadosPro {
    public HashMap<Integer, HashMap<String, String>> pronosticos;

    public ResultadosPro(){
        pronosticos = new HashMap<>();
    }

    public void agregarPronostico(Pronostico pronostico) {
        int nPartido = pronostico.getnPartido();
        String nombre = pronostico.getNombre();
        String resultado = pronostico.getResultado();

        if (pronosticos.containsKey(nPartido)) {
            pronosticos.get(nPartido).put(nombre, resultado);
        } else {
            HashMap<String, String> jugadores = new HashMap<>();
            jugadores.put(nombre, resultado);
            pronosticos.put(nPartido, jugadores);
        }
    }
}