import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class SimuladorColoniaHormigas {
    private static final  int  INTERVALO_ACTUALIZACION = 2000;
    private static final int NUMERO_OBRERAS = 5;
    private static final int NUMERO_ITERACIONES = 5;
    private final Mapa mapa;
    private final HashMap<String, Hormiga> hormigas ;
    private volatile boolean simulacionActiva;
    private final Random random;
    protected static final int[][] DIRECCIONES = {
            {1, 0},   // abajo
            {-1, 0},  // arriba
            {0, 1},   // derecha
            {0, -1}   // izquierda
    };

    public SimuladorColoniaHormigas(){
        this.mapa=new Mapa();
        this.random=new Random();
        this.simulacionActiva=false;
        this.hormigas = new HashMap<>();

    }


    /**
     * Metodo encargado de generar las hormigas por primera vez con una posicion válida aleatoria
     * Big O(∞)
     */
    public void generarHormigaObrera(){
        Posicion posicionHormiguero = this.mapa.getHormiguero();

        for (int i =0; i<NUMERO_OBRERAS ;i++){
            Posicion  posicionAleatoria= new Posicion(this.random.nextInt(Mapa.ANCHO), this.random.nextInt(Mapa.ALTO));
            if (!(posicionHormiguero.getX()==posicionAleatoria.getX() && posicionHormiguero.getY() == posicionAleatoria.getY())){
                String id = "H"+i;
                HormigaObrera  nuevaHormiga =  new HormigaObrera(id,posicionAleatoria,this);
                this.hormigas.put(id,nuevaHormiga);
                nuevaHormiga.start();
            }else {
                i--;
            }

        }
    }



    /**
     * Método encargado de mover la hormiga recibida por parámetro en una de las cuatro direcciones aleatoriamente.
     * También comprueba si la posición es válida. Big O(∞)
     * @param hormiga la hormiga a mover
     */
    public synchronized void moverHormigaAleatoriamente(Hormiga hormiga) {
      boolean bandera = true;
      while (bandera){
      int direccion = this.random.nextInt(4);
      int deltaX = DIRECCIONES[direccion][0];
      int deltaY = DIRECCIONES[direccion][1];

      Posicion posicionActual = hormiga.getPosicion();
      Posicion nuevaPosicion = posicionActual.mover(deltaX,deltaY);

        if (this.mapa.dentroLimites(nuevaPosicion) &&(nuevaPosicion.getX()!= this.mapa.getHormiguero().getX() || nuevaPosicion.getY() != this.mapa.getHormiguero().getY())) {
        hormiga.setPosicion(nuevaPosicion);
        bandera=false;
        }
      }
    }


    /**
     * Metodo encargado de simular limpieza de consola imprimiendo 20 saltos de línea vacios.
     * Big O(1)
     */
    public void limpiarConsola(){
        System.out.print("\n".repeat(20));
    }

    /**
     * Metodo encargado de detener toda la simulacion deteniendo todos los hilos de las hormigas de forma segura.
     * Big O(n) donde n es el número de hormigas
     */
    public void detenerSimulacion(){
        this.simulacionActiva=false;

        for (Hormiga hormigaActual : this.hormigas.values()){
            hormigaActual.detener();
        }
        System.out.println("\nEmpezando a detener la simulación!");
        for (Hormiga hormiga : this.hormigas.values()) {
            try {
                hormiga.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Error al esperar que termine el hilo: " + hormiga.getIdHormiga());
            }
        }
        System.out.println("\nSimulación detenida correctamente!");

    }

    /**
     * Metodo encargado de actualizar el mapa y mostrarlo cada x tiempo con un sleep, también muestra las estadísticas
     * Big O(n), n es el número de hormigas
     */
    private void actualizarVisualizacion() {
                limpiarConsola();
                this.mapa.prepararMapa(this.hormigas);
                this.mapa.mostrarMapa();
                mostrarEstadisticas();
        }



    /**
     * Metodo encargado de iniciar la simulacion y ejecutarla 50 veces antes de preguntar si debe seguir
     * Big O(∞) porque en el pero de los casos no se detiene nunca
     */
    public void ejecutar() {
        int  contadorIteraciones = 0;
        Scanner sc = new Scanner(System.in);
        generarHormigaObrera();
        this.simulacionActiva = true;


        while (this.simulacionActiva){
            actualizarVisualizacion();

            if (contadorIteraciones==NUMERO_ITERACIONES){
                System.out.print("Desea parar la simulación? Introduzca 1 para detenerla 2 para seguir => ");
                String respuesta = sc.nextLine();
                if (respuesta.equals("1")){
                    detenerSimulacion();

                }else {
                    contadorIteraciones=0;
                }
            }
            contadorIteraciones++;
            try {
                Thread.sleep(INTERVALO_ACTUALIZACION);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        sc.close();

    }

    /**
     * Método encargado de imprimir las estadísticas de la colonia: número de obreras activas, posición del hormiguero e intervalo de actualización.
     * Big O(n) donde n es el número de hormigas
     */
    private void mostrarEstadisticas() {
        System.out.println("\n===== ESTADÍSTICAS DE LA COLONIA =====");

        int hormigasActivas = 0;
        for (Hormiga hormiga : this.hormigas.values()) {
            if (hormiga.isActiva()) {
                hormigasActivas++;
            }
        }

        System.out.println("Hormigas activas: " + hormigasActivas + "/" + this.hormigas.size());
        System.out.println("Intervalo de actualización: " + INTERVALO_ACTUALIZACION + " ms");

        Posicion posHormiguero = this.mapa.getHormiguero();
        System.out.println("Posición del hormiguero: (" + (posHormiguero.getX()+1) + ", " + (posHormiguero.getY()+1)+ ")");

        int obreras = 0;
        for (Hormiga hormiga : this.hormigas.values()) {
            if (hormiga.getTipo() == TipoHormiga.OBRERA) {
                obreras++;
            }
        }
        System.out.println("Hormigas obreras: " + obreras);
        System.out.println("====================================");
    }


}
