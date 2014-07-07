/**
 * Created by einar on 2014-07-05.
 */
public abstract class AbstractPlayer {
    Marker marker;

    public AbstractPlayer(Marker marker){ //TODO: Make better solution for choosing color
        this.marker = marker;
    }

    public Marker getMarker() {
        return marker;
    }

    public abstract Coordinate getMove();

}
