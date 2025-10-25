public class HormigaObrera extends Hormiga{

    /**
     * Constructor de HormigaObrera que inicializa una hormiga con tipo OBRERA.
     * @param id identificador único de la hormiga
     * @param posicionInicial posición donde inicia la hormiga en el mapa
     * @param simuladorColoniaHormigas referencia al simulador
     */
    public HormigaObrera(String id, Posicion posicionInicial ,SimuladorColoniaHormigas simuladorColoniaHormigas) {
        super(id, TipoHormiga.OBRERA, posicionInicial,simuladorColoniaHormigas);
    }



}