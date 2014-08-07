package Player;
import Models.Marker;

/**
 * Author: einar
 *
 * The base interface for all player classes.
 */
interface IPlayer{
    /**
     * Get the players marker.
     * * @return marker
     */
    public Marker getMarker();

    /**
     * Sets the marker of the player.
     * @param marker the player's marker
     */
    public void setMarker(Marker marker);

    /**
     * "Wakes up" the player to make a move
     */
    public void wakePlayer();
}
