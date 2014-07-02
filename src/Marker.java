/**
 * Created by einar on 2014-06-29.
 */
public enum Marker {
    EMPTY("E"),BLACK("B"),WHITE("W");

    String marker;
    Marker(String marker){
        this.marker = marker;
    }

    public String toString(){
        return marker;
    }

    /**
     * Help Method.
     * Returns the opposite color of the marker. If The marker is empty it returns empty.
     * @return
     */
    public  Marker getOppostie(){
        if(this == Marker.EMPTY){
            return Marker.EMPTY;
        }
        return (this == Marker.BLACK) ? Marker.WHITE : Marker.BLACK;
    }
}
