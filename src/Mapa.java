import java.util.HashMap;

public class Mapa {
    public static final int ANCHO= 9;
    public static final int ALTO =5;
    private final Posicion hormiguero  ;
    private final char[][] mapa ;
    public static final char VACIO = '*';
    public static final char HORMIGUERO = 'H';


    public Mapa() {
        mapa = new char[ALTO][ANCHO];
        for (int i = 0; i < ALTO; i++) {
            for (int j = 0; j < ANCHO; j++) {
                mapa[i][j] = VACIO;
            }
        }

        hormiguero = new Posicion(ALTO / 2, ANCHO / 2);
        mapa[hormiguero.getY()][hormiguero.getX()] = HORMIGUERO;

    }

    public Posicion getHormiguero(){
        return hormiguero;
    }
    public boolean dentroLimites(Posicion posicion){
       return posicion.dentroLimites(ANCHO, ALTO);
    }
    public synchronized void mostrarMapa(){
        for (int i = 0; i < ALTO; i++) {
            for (int j = 0; j < ANCHO; j++) {
                System.out.printf(mapa[i][j]+" ");
            }
            System.out.println();
        }

    }


    public void prepararMapa(HashMap<String, Hormiga> hormigas){
        for (int i = 0; i < ALTO; i++) {
            for (int j = 0; j < ANCHO; j++) {
                if (i!= hormiguero.getY() && j!=hormiguero.getX()){
                mapa[i][j] = VACIO;
            }}}
        for (Hormiga hormiga : hormigas.values()){
           int x=hormiga.getPosicion().getX();
           int y=hormiga.getPosicion().getY();
           mapa[y][x]=hormiga.getTipo().getSimbolo().charAt(0);
        }

    }
}
