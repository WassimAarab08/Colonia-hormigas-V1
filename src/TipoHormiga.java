public enum TipoHormiga {
    OBRERA("O", "Hormiga obrera");
   // GUERRERA("G", "Hormiga guerrera"),
   // REINA("R", "Hormiga reina");


    private final String simbolo;
    private final String nombre;


    TipoHormiga(String simbolo, String nombre) {
        this.simbolo = simbolo;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSimbolo() {
        return simbolo;
    }
}



