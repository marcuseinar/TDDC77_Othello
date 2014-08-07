package Player;

import Models.Marker;
import UserInterface.AbstractUserInterface;

/**
 * Author: Einar
 *
 * A player class that uses human interaction to control moves.
 */
public class HumanPlayer implements IPlayer {
    Marker marker;
    AbstractUserInterface userInterface;

    /**
     * Creates a player that takes a human input through an user interface.
     * @param userInterface the user interface through which the player interacts with the game.
     */
    public HumanPlayer(AbstractUserInterface userInterface){
        this.userInterface = userInterface;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Marker getMarker() {
        return this.marker;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMarker(Marker marker) {
        this.marker = marker;
        wakePlayer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void wakePlayer() {
        this.userInterface.getMove(this.marker);
    }
}
