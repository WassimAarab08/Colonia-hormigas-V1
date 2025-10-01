import java.util.Random;

public class Hormiga  extends Thread{

    protected final String id;
    protected final TipoHormiga tipo;
    protected volatile Posicion posicion;
    protected volatile boolean activa;
    protected final Random random;
    protected static final int[][] DIRECCIONES = new int[4][4];


    public Hormiga(String id, TipoHormiga tipo, Random random) {
        this.id = id;
        this.tipo = tipo;
        this.random = random;
    }

    public String getIdHormiga(){
        return id;
    }

    public TipoHormiga getTipo(){
        return tipo;
    }

    public Posicion getPosicion(){
        return posicion;
    }

    public void setPosicion(Posicion nuevaPosicion){
        posicion = nuevaPosicion;
    }

    public boolean isActiva(){
        return activa;
    }


    public void detener(){
        activa = false;
    }
}
