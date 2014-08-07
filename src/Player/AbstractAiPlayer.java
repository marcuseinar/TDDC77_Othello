package Player;

import Models.Board;
import Models.Marker;

/**
 * Author: Einar
 *
 * The Abstract base class for all AI players
 */
public abstract class AbstractAiPlayer implements IPlayer{
    Board board = null;
    Marker marker;

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
    abstract void makeMove();

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public Marker getMarker() {
        return this.marker;
    }

    /**
     * {@inheritDoc}
     * @param marker the player's marker
     */
    @Override
    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void wakePlayer() {
        if(board == null){
            System.out.println("Board can't be null. Please set board variable");
            return;
        }
        makeMove();
    }
}
