/**
 * Created by einar on 2014-07-05.
 */
public class Coordinate{
    private int x;
    private int y;
    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Coordinate)|| o == null){
            return false;
        }
        if(((Coordinate) o).getX() == this.x || ((Coordinate) o).getY() == this.y){
            return true;
        }
        return false;
    }
}
