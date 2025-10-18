public class HormigaObrera extends Hormiga{
    private SimuladorColoniaHormigas simulador = new SimuladorColoniaHormigas();
    public HormigaObrera(String id, Posicion posicionInicial) {
        super(id, TipoHormiga.OBRERA, posicionInicial);
    }

    @Override
    public void run() {

        while (activa) {
            try {

                simulador.moverHormigaAleatoriamente(this);
                Thread.sleep(1900);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

}