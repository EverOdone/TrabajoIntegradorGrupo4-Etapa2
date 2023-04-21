import java.util.HashMap;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Ronda {
    public HashMap<Integer,Partido> partidos;

    public Ronda(){
        partidos = new HashMap<>();
    }

    public void agregarPartido(Partido nuevo){
        partidos.put(nuevo.getnRonda(),nuevo);
    }

    public int cantidadPartido(){
        return partidos.size();
    }

    public Ronda cargarPartidos(String nombreArchivo) throws IOException {
        Path listaP = Paths.get(nombreArchivo);
        Scanner lector = new Scanner(listaP);

        lector.useDelimiter("[,;\\n]");

        Ronda lista = new Ronda();

        while (lector.hasNextInt()) {
            int nPar = lector.nextInt();
            String e1 = lector.next();
            int r1 = lector.nextInt();
            int r2 = lector.nextInt();
            String e2 = lector.next();

            Partido nuevo = new Partido(nPar, e1, r1, e2, r2);
            lista.agregarPartido(nuevo);
            System.out.println("Partido nº " + nPar + " : " + nuevo);
        }

        return lista;
    }

}