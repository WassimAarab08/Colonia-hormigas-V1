import java.util.Random;

public class Hormiga  extends Thread{

    protected final String id;
    protected final TipoHormiga tipo;
    protected volatile Posicion posicion;
    protected volatile boolean activa;
    protected final Random random = new Random();
    protected static final int[][] DIRECCIONES = new int[4][4];


    public Hormiga(String id, TipoHormiga tipo, Posicion posicion) {
        this.id = id;
        this.tipo = tipo;
        this.posicion = posicion;
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
