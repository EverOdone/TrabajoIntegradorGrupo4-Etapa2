import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {

        Path listaP = Paths.get("partidos.txt");//Conecta con Archivos
        Scanner lector = new Scanner(listaP);

        lector.useDelimiter("[,;\\n]");

        Ronda lista = new Ronda();

        System.out.println("");
        System.out.println("GRUPO C - FASE DE GRUPOS");
        System.out.println("");

        while (lector.hasNextInt()) {
            int nPar = lector.nextInt();
            String e1 = lector.next();
            int r1 = lector.nextInt();
            int r2 = lector.nextInt();
            String e2 = lector.next();

            Partido nuevo = new Partido(nPar,e1,r1,e2,r2);//Genera un nuevo Partido
            lista.agregarPartido(nuevo);
            System.out.println("Partido nº " + nPar + " : " +  nuevo);
        }

        //Carga Pronosticos
        Path listaPro = Paths.get("pronostico.txt");
        Scanner lectorPro = new Scanner(listaPro);

        lectorPro.useDelimiter("[,;\\n]");

        ResultadosPro listaPronostico = new ResultadosPro();

        while (lectorPro.hasNextLine()) {
            String line = lectorPro.nextLine();
            String[] parts = line.split(";");
            int parPro = Integer.parseInt(parts[0]);
            String nomPro = parts[2];
            String resPro = parts[1];

            Pronostico nuevo2 = new Pronostico(parPro,resPro,nomPro);
            listaPronostico.agregarPronostico(nuevo2);
        }

        System.out.println("");

        int conPuntos = 0;
        String equipoGanador = "";
        HashMap<String, Integer> puntajesJugadores = new HashMap<>();

        for (int i = 1; i < lista.cantidadPartido()+1 ; i++) {
            Partido partido = lista.partidos.get(i);
            int resEquipo1 = partido.getGoles1();
            int resEquipo2 = partido.getGoles2();

            if (resEquipo1 > resEquipo2) {
                equipoGanador = partido.getEquipo1();
                System.out.println("Resultado nº " + i + " : " +  equipoGanador);
            } else {
                if (resEquipo1 == resEquipo2) {
                    equipoGanador = "Empate";
                    System.out.println("Resultado nº " + i + " : " +  equipoGanador);
                } else {
                    equipoGanador = partido.getEquipo2();
                    System.out.println("Resultado nº " + i + " : " +  equipoGanador);
                }
            }
            HashMap<String, String> jugadores = listaPronostico.pronosticos.get(i);
            for (String jugador : jugadores.keySet()) {
                String resultadoJugador = jugadores.get(jugador);
                if (resultadoJugador.trim().equals(equipoGanador.trim())) {
                    conPuntos++;
                    int puntajeActual = puntajesJugadores.getOrDefault(jugador, 0);
                    puntajesJugadores.put(jugador, puntajeActual + 1);
                    System.out.println("Jugador : "+ jugador + " +1");
                }else {
                    int puntajeActual = puntajesJugadores.getOrDefault(jugador, 0);
                    puntajesJugadores.put(jugador, puntajeActual);
                    System.out.println("Jugador : "+ jugador + " (" + resultadoJugador + ")");
                }
            }
            System.out.println("");

        }
        for (String jugador : puntajesJugadores.keySet()) {
            int puntaje = puntajesJugadores.get(jugador);
            System.out.println("Puntaje de "+jugador+": "+puntaje);
        }
    }
}
