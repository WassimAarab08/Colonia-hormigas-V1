import java.util.HashMap;
import java.util.Random;

public class SimuladorColoniaHormigas {
    private static final int  INTERVALO_ACTUALIZACION = 2000;
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
        this.hormigas = new HashMap<>();

    }

    public HashMap<String,Hormiga> getHormigasMap(){
        return  hormigas;
    }

    public void generarHormigaObrera(){
        Posicion pocionHormiguero = this.mapa.getHormiguero();
        for (int i =0; i<NUMERO_OBRERAS ;i++){
            Posicion  posicionAleatoria= new Posicion(random.nextInt(Mapa.ANCHO), random.nextInt(Mapa.ALTO));
            if (!(pocionHormiguero.getX()==posicionAleatoria.getX() && pocionHormiguero.getY() == posicionAleatoria.getY())){
                String id = "H"+i;
                HormigaObrera  nuevaHormiga =  new HormigaObrera(id,posicionAleatoria);
                this.hormigas.put(id,nuevaHormiga);
                nuevaHormiga.start();
            }else {
                i--;
            }

        }
    }


    private synchronized void moverTodasLasHormigas(){
        for(Hormiga hormigaActual : this.hormigas.values()){
         moverHormigaAleatoriamente(hormigaActual);
        }
    }

    public synchronized void moverHormigaAleatoriamente(Hormiga hormiga) {

    if (hormiga.isActiva()){
      int direccion = random.nextInt(4);
      int deltaX = DIRECCIONES[direccion][0];
      int deltaY = DIRECCIONES[direccion][1];

      Posicion posicionActual = hormiga.getPosicion();
      Posicion nuevaPoscion = new Posicion(posicionActual.getX()+deltaX, posicionActual.getY()+deltaY);


    if (mapa.dentroLimites(nuevaPoscion) &&(nuevaPoscion.getX()!= mapa.getHormiguero().getX() || nuevaPoscion.getY() != mapa.getHormiguero().getY())) {
        hormiga.setPosicion(nuevaPoscion);
    }
    }}



    public void limpiarConsola(){
        System.out.print("\n".repeat(20));
    }

    public void detenerSimulacion(){
        this.simulacionActiva=false;

        for (Hormiga hormigaActual : this.hormigas.values()){
            hormigaActual.detener();
        }

        for (Hormiga hormiga : hormigas.values()) {
            try {
                hormiga.join();
                System.out.println("\nSimulación detenida correctamente!");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Error al esperar que termine el hilo: " + hormiga.getIdHormiga());
            }
        }

    }

    private void actualizarVisualizacion() {

        while (simulacionActiva) {
            try {
                limpiarConsola();
                moverTodasLasHormigas();
                mapa.prepararMapa(hormigas);
                mapa.mostrarMapa();
                mostrarEstadisticas();
                Thread.sleep(INTERVALO_ACTUALIZACION);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }



    public void ejecutar() {

        generarHormigaObrera();
        simulacionActiva = true;
        actualizarVisualizacion();
        detenerSimulacion();
    }

    private void mostrarEstadisticas() {
        System.out.println("\n===== ESTADÍSTICAS DE LA COLONIA =====");

        int hormigasActivas = 0;
        for (Hormiga hormiga : hormigas.values()) {
            if (hormiga.isActiva()) {
                hormigasActivas++;
            }
        }

        System.out.println("Hormigas activas: " + hormigasActivas + "/" + hormigas.size());
        System.out.println("Intervalo de actualización: " + INTERVALO_ACTUALIZACION + " ms");

        Posicion posHormiguero = mapa.getHormiguero();
        System.out.println("Posición del hormiguero: (" + posHormiguero.getX() + ", " + posHormiguero.getY() + ")");

        int obreras = 0;
        for (Hormiga hormiga : hormigas.values()) {
            if (hormiga.getTipo() == TipoHormiga.OBRERA) {
                obreras++;
            }
        }
        System.out.println("Hormigas obreras: " + obreras);
        System.out.println("====================================");
    }


}
