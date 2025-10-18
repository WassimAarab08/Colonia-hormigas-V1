public class Posicion {
    private int x,y;


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

    public boolean dentroLimites(int maxX, int maxY){
        return this.x >= 0 && this.x < maxX && this.y >= 0 && this.y < maxY;
    }


    public Posicion mover(int deltaX, int deltaY){
        return new Posicion(this.y+deltaY, this.x+deltaX);
    }



}
