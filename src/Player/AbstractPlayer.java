package Player;
import Controllers.GameController;
import Models.Coordinate;
import Models.Marker;

/**
 * Author: einar
 *
 * The base interface for all player classes.
 */
public abstract class AbstractPlayer{
    protected Marker marker;
    protected GameController gameController;

    public AbstractPlayer(GameController gameController){
        this.gameController = gameController;
    }

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
        System.out.println(coordinate);
        System.out.println(gameController);
        this.gameController.makeMove(coordinate);
    }
}
