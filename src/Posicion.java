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
        return x >= 0 && x < maxX && y >= 0 && y < maxY;
    }


    public Posicion mover(int deltaX, int deltaY){
      return new Posicion(x+deltaX, y+deltaY);
    }



}
