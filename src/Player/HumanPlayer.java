package Player;

import Controllers.GameController;
import UserInterface.AbstractUserInterface;

/**
 * Author: Einar
 *
 * A player class that uses human interaction to control moves.
 */
public class HumanPlayer extends AbstractPlayer{
    AbstractUserInterface userInterface;

    /**
     * Creates a player that takes a human input through an user interface.
     * @param userInterface the user interface through which the player interacts with the game.
     */
    public HumanPlayer(AbstractUserInterface userInterface, GameController gameController){
        super(gameController);
        this.userInterface = userInterface;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void wakePlayer() {
        System.out.println("this is player: " + marker.toString());
        userInterface.getMove(this);
    }
}
