import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("");
        System.out.println("GRUPO C - FASE DE GRUPOS");
        System.out.println("");

        Ronda lista = new Ronda();//HashMap partidos
        Ronda nuevoPar = lista.cargarPartidos("partidos.txt");

        ResultadosPro listaPronostico = new ResultadosPro();
        ResultadosPro nuevoPro = listaPronostico.cargarPronostico("pronostico.txt");

        System.out.println("");
        String equipoGanador = "";
        HashMap<String, Integer> puntajesJugadores = new HashMap<>();//Almacena puntaje jugadores

       for (int i = 1; i < nuevoPar.cantidadPartido()+1 ; i++) {//Recorre todos los partidos
            Partido partido = nuevoPar.partidos.get(i);
            int resEquipo1 = partido.getGoles1();
            int resEquipo2 = partido.getGoles2();

            if (resEquipo1 > resEquipo2) { //Calcula equipos ganadores
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
            HashMap<String, String> jugadores = nuevoPro.pronosticos.get(i);//jugadores contendrá un mapeo de todos los jugadores que hicieron un pronóstico para el partido i.
            for (String jugador : jugadores.keySet()) { //Recorre pronosticos por jugador. keySet()devuelve un conjunto que contiene todas las claves mapeadas en el mapa.
                String resultadoJugador = jugadores.get(jugador);//Se obtiene el resultado del jugador jugador para un partido determinado.

                if (resultadoJugador.trim().equals(equipoGanador.trim())) { //El jugador acerto  y se agrega un punto a su puntaje actual en el HashMap "puntajesJugadores".
                    int puntajeActual = puntajesJugadores.getOrDefault(jugador, 0); //Utilizando el método "getOrDefault", que busca en el HashMap el valor asociado con la clave "jugador" y devuelve el valor si existe, o un valor predeterminado (0 en este caso) si no existe.
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
        int puntajeMaximo = 0;
        String jugadorMaximo = "";

        for (String jugador : puntajesJugadores.keySet()) {//Mustra puntaje por jugador
            int puntaje = puntajesJugadores.get(jugador);
            System.out.println("Puntaje de "+jugador+": "+puntaje);

            if (puntaje > puntajeMaximo) {
                puntajeMaximo = puntaje;
                jugadorMaximo = jugador;
            }
        }
        System.out.println("");
        System.out.println("El jugador con el mayor puntaje es :  " + jugadorMaximo + " ( " + puntajeMaximo + " ) puntos.");

    }
}
