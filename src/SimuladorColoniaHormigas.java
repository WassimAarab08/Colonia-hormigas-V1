import java.util.HashMap;
import java.util.Random;

public class SimuladorColoniaHormigas {
    private static final int  INTERVALO_ACTUALIZACION = 200;
    private static final int NUMERO_OBRERAS=3;
    protected static final int[][] DIRECCIONES = {
            {1, 0},   // abajo
            {-1, 0},  // arriba
            {0, 1},   // derecha
            {0, -1}   // izquierda
    };
    private Mapa mapa;
    private HashMap<String, Hormiga> hormigas ;
    private volatile boolean simulacionActiva;
    private final Random random;


    public SimuladorColoniaHormigas(){
        this.mapa=new Mapa();
        this.random=new Random();
        this.simulacionActiva=false;

    }

    public HashMap<String,Hormiga> getHormigasMap(){
        return  hormigas;
    }

    public void generarHormigaObrera(){
        Posicion pocionHormiguero = mapa.getHormiguero();
        for (int i =0; i<NUMERO_OBRERAS ;i++){
            Posicion  sessionAleatoria= new Posicion(random.nextInt(Mapa.ALTO), random.nextInt(Mapa.ANCHO) );
            if (pocionHormiguero.getX()!=sessionAleatoria.getX() || pocionHormiguero.getY() != sessionAleatoria.getY()){
                String id = "H"+i;
                HormigaObrera  nuevaHormiga =  new HormigaObrera(id,sessionAleatoria);
                hormigas.put(id,nuevaHormiga);
            }else {
                i--;
            }

        }
    }
}
