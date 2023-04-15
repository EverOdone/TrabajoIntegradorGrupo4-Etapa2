import java.util.HashMap;

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

}