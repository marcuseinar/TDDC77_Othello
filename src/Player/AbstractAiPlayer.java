package Player;

import Controllers.GameController;
import Models.Board;
import Models.Coordinate;
import Models.Marker;

import java.util.Observable;
import java.util.Observer;

/**
 * Author: Einar
 *
 * The Abstract base class for all AI players
 */
public abstract class AbstractAiPlayer extends AbstractPlayer implements Observer {
    Board board = null;
    Marker marker;

    AbstractAiPlayer(GameController gameController){
        this.board = gameController.getBoard();
        gameController.addObserver(this);
    }

    /**
     * Sets the board that the AI player uses to calculate it's moves.
     * @param board the current game board.
     */
    public void setBoard(Board board){
        this.board = board;
    }

    /**
     * Calls the AI Player's
     */
    abstract protected Coordinate getMove();

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void wakePlayer() {
        if(board == null){
            System.out.println("Board can't be null. Please set board variable");
            return;
        }
        Coordinate move = getMove();
        setChanged();
        notifyObservers(move);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof GameController && arg instanceof Board){
            setBoard((Board) arg);
        }
    }
}
