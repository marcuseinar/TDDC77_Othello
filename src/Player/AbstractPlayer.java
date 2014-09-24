package Player;
import Models.Coordinate;
import Models.Marker;

import java.util.Observable;

/**
 * Author: einar
 *
 * The base interface for all player classes.
 */
public abstract class AbstractPlayer extends Observable{
    protected Marker marker;

    /**
     * Get the players marker.
     * @return marker
     */
    public Marker getMarker(){
        return marker;
    };

    /**
     * Sets the marker of the player.
     * @param marker the player's marker
     */
    public void setMarker(Marker marker){
        this.marker = marker;
    }

    /**
     * "Wakes up" the player to make a move
     */
    abstract public void wakePlayer();

    /**
     * Makes a move
     */
    public void makeMove(Coordinate coordinate){
        setChanged();
        notifyObservers(coordinate);
        System.out.println("player made move " + marker.toString());
    }
}
