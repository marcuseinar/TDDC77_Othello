package Player;

import Models.Coordinate;
import UserInterface.AbstractUserInterface;

import java.util.Observable;
import java.util.Observer;

/**
 * Author: Einar
 *
 * A player class that uses human interaction to control moves.
 */
public class HumanPlayer extends AbstractPlayer implements Observer{
    AbstractUserInterface userInterface;

    /**
     * Creates a player that takes a human input through an user interface.
     * @param userInterface the user interface through which the player interacts with the game.
     */
    public HumanPlayer(AbstractUserInterface userInterface){
        this.userInterface = userInterface;
        addObserver(userInterface);
        userInterface.addObserver(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void wakePlayer() {
        System.out.println("player woken: " + marker.toString());
        userInterface.getMove();
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof AbstractUserInterface && arg instanceof Coordinate){
            makeMove((Coordinate) arg);
        }
    }
}
