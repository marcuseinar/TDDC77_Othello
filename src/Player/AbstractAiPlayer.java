package Player;

import Models.Board;

/**
 * Author: Einar
 *
 * The Abstract base class for all AI players
 */
public abstract class AbstractAiPlayer implements IPlayer{
    Board board = null;

    /**
     * Sets the board that the AI player uses to calculate it's moves.
     * @param board
     */
    public void setBoard(Board board){
        this.board = board;
    };

    /**
     * Calls the AI Player's
     */
    abstract void makeMove();

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
