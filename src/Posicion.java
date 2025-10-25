public class Posicion {
    private int x;
    private int y;


    public Posicion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return this.y;
    }

    public int getX() {
        return this.x;
    }

    /**
     * Este metodo comprueba si la this.x y this.y están dentro del mapa
     * Complejidad de Big O(1)
     * @param maxX el ancho del mapa
     * @param maxY el alto del mapa
     * @return si la posicion es valida
     */
    public boolean dentroLimites(int maxX, int maxY){
        return this.x >= 0 && this.x < maxX && this.y >= 0 && this.y < maxY;
    }


    /**
     * Añade los desplazamientos `deltaX` y `deltaY` a la posición actual y devuelve
     * una nueva instancia de `Posicion` con las coordenadas resultantes.
     * Big O(1)
     * @param deltaX es cuanto se le suma a x
     * @param deltaY es cuanto se le suma a y
     * @return
     */
    public Posicion mover(int deltaX, int deltaY){
        return new Posicion( this.x+deltaX,this.y+deltaY);
    }



}
