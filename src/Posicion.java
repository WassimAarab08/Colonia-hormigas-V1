public class Posicion {
    private int x,y;


    public Posicion(int y, int x) {
        this.x = x;
        this.y = y;

    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public boolean dentroLimites(int maxX, int maxY){
        return x <= maxX && y <= maxY && x - maxX == 1 && y - maxX == 1;
    }


    public Posicion mover(int deltaX, int deltaY){
      return new Posicion(x+deltaX, y+deltaY);
    }



}
