/**
 * Created by einar on 2014-06-29.
 */
public enum Marker {
    EMPTY("e"),BLACK("b"),WHITE("w");

    String marker;
    Marker(String marker){
        this.marker = marker;
    }

    public String toString(){
        return marker;
    }

    /**
     * Returns the opposite color of the marker. If The marker is empty it returns empty.
     * @return
     */
    public Marker getOppostie(){
        if(this == EMPTY){
            return EMPTY;
        }
        return (this == BLACK) ? WHITE : BLACK;
    }
}
