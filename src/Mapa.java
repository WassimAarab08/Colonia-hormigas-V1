import java.util.HashMap;

public class Mapa {
    public static final int ANCHO= 10;
    public static final int ALTO =5;
    private final Posicion hormiguero = new Posicion(2,5)  ;
    private final char[][] mapa = new char[ANCHO][ALTO];
    public static final char VACIO = '*';
    public static final char HORMIGUERO = 'o';


    public Mapa() {
    }

    public Posicion getHormiguero(){
        return hormiguero;
    }
    public boolean dentroLimites(Posicion posicion){
       return posicion.dentroLimites(ANCHO, ALTO);
    }
    public synchronized void mostrarMapa(){

    }


    public void prepararMapa(HashMap<String, Hormiga> hormigas){

    }
}
