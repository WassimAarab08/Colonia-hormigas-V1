import java.util.HashMap;

public class Mapa {
    public static final int ANCHO= 9;
    public static final int ALTO =5;
    private final Posicion hormiguero  ;
    private final char[][] mapa ;
    public static final char VACIO = '*';
    public static final char HORMIGUERO = 'H';


    /**
     * El constructor es el encargado de inicializar el mapa vació con el hormiguero en medio para ello se
     * recorre el array de mapa con un for anidado para filas y columnas del array y se rellena del símbolo Vació.
     * También se calcula el medio del mapa dividiendo alto y ancho entre 2 para posicionar el hormiguero
     * Complejidad Big O(n·m)
     */
    public Mapa() {
        mapa = new char[ALTO][ANCHO];
        for (int i = 0; i < ALTO; i++) {
            for (int j = 0; j < ANCHO; j++) {
                mapa[i][j] = VACIO;
            }
        }
        hormiguero = new Posicion(ANCHO / 2, ALTO / 2);
        mapa[hormiguero.getY()][hormiguero.getX()] = HORMIGUERO;

    }

    public Posicion getHormiguero(){
        return hormiguero;
    }

    /**
     * Método que comprueba si la posición nueva va a estar dentro de los límites, llamando al método de dentroLimites de posición
     * Complejidad Big 0(1)
     * @param posicion
     * @return
     */

    public boolean dentroLimites(Posicion posicion){
       return posicion.dentroLimites(ANCHO, ALTO);
    }

    /**
     * Método que recorre el mapa actual y lo imprime en consola
     * Complejidad de Big 0(n·m)
     */
    public synchronized void mostrarMapa(){
        for (int i = 0; i < ALTO; i++) {
            for (int j = 0; j < ANCHO; j++) {
                System.out.printf(mapa[i][j]+" ");
            }
            System.out.println();
        }

    }


    /**
     *  Resetea el mapa y coloca las hormigas en su posición correspondiente según su tipo.
     * Complejidad de Big O(n·m+K)
     * @param hormigas
     */
    public void prepararMapa(HashMap<String, Hormiga> hormigas){
        for (int i = 0; i < ALTO; i++) {
            for (int j = 0; j < ANCHO; j++) {
                mapa[i][j] = VACIO;
            }}
        mapa[hormiguero.getY()][hormiguero.getX()] = HORMIGUERO;
        for (Hormiga hormiga : hormigas.values()){
           int x=hormiga.getPosicion().getX();
           int y=hormiga.getPosicion().getY();
           mapa[y][x]=hormiga.getTipo().getSimbolo().charAt(0);
        }

    }

}
