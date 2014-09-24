package Models;

/**
 * Created by einar on 2014-06-29.
 */
public enum Marker {
    EMPTY,BLACK,WHITE;

    /**
     * Help Method.
     * Returns the opposite color of the marker. If The marker is empty it returns empty.
     * @return
     */
    public  Marker getOpposite(){
        if(this == Marker.EMPTY){
            return Marker.EMPTY;
        }
        return (this == Marker.BLACK) ? Marker.WHITE : Marker.BLACK;
    }
}
