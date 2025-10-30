import java.util.Random;

public  abstract class   Hormiga  extends Thread{
    private static final  int  INTERVALO_MAX_MOVIMIENTO = 5000;
    protected final String id;
    protected final TipoHormiga tipo;
    protected volatile Posicion posicion;
    protected volatile boolean activa ;
    private final SimuladorColoniaHormigas simuladorColoniaHormigas;
    protected final Random random ;


    public Hormiga(String id, TipoHormiga tipo, Posicion posicion ,SimuladorColoniaHormigas simuladorColoniaHormigas) {
        this.simuladorColoniaHormigas = simuladorColoniaHormigas;
        this.id = id;
        this.tipo = tipo;
        this.activa= true;
        this.posicion = posicion;
        this.random=new Random();
    }

    public String getIdHormiga(){
        return this.id;
    }

    public TipoHormiga getTipo(){
        return this.tipo;
    }

    public Posicion getPosicion(){
        return this.posicion;
    }

    /**
     * Metodo run de las hormigas que hace que las hormigas se muevan cada x tiempo donde x es él random dentro del rango
     * Big O(∞) porque es un bucle infinito hasta que activa = false
     */
    @Override
    public void run() {

        while (activa) {
            try {
               this.simuladorColoniaHormigas.moverHormigaAleatoriamente(this);
                Thread.sleep(random.nextInt(INTERVALO_MAX_MOVIMIENTO));
            } catch (InterruptedException e) {
                System.out.println("Una hormiga murió debido a un error");
                break;
            }
        }
    }


    public void setPosicion(Posicion nuevaPosicion){
        this.posicion = nuevaPosicion;
    }

    /**
     * Devuelve si la hormiga  esta activa
     * Big O(1)
     * @return esta activa
     */
    public boolean isActiva(){
        return this.activa;
    }

    /**
     * Pone el estado de la hormiga en inactiva
     */
    public void detener(){
        this.activa = false;
    }
}
