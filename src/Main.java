
public class Main {
    /**
     * @author wassim aarab
     * Metodo main donde se prueba todo la simulacion
     */
    public static void main(String[] args) {
        try {
            SimuladorColoniaHormigas simuladorColoniaHormigas = new SimuladorColoniaHormigas();
            simuladorColoniaHormigas.ejecutar();
        } catch (Exception e) {
            System.err.println("Error durante la ejecución: " + e.getMessage());
        }
    }
}
