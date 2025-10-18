import java.util.Random;

public  abstract class   Hormiga  extends Thread{

    protected final String id;
    protected final TipoHormiga tipo;
    protected volatile Posicion posicion;
    protected volatile boolean activa =true;
    protected final Random random = new Random();
    protected static final int[][] DIRECCIONES = {
            {1, 0},   // abajo
            {-1, 0},  // arriba
            {0, 1},   // derecha
            {0, -1}   // izquierda
    };



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
        return this.activa;
    }


    public void detener(){
        activa = false;
    }
}
