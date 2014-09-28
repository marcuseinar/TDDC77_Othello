package Player;

import Controllers.GameController;
import Models.Board;
import Models.Coordinate;

/**
 * Author: Einar
 *
 * The Abstract base class for all AI players
 */
public abstract class AbstractAiPlayer extends AbstractPlayer{
    Board board = null;

    public AbstractAiPlayer(GameController gameController) {
        super(gameController);
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
        System.out.println(2);
        if(board == null){
            System.out.println("Board can't be null. Please set board variable");
            return;
        }
        Coordinate move = getMove();
        makeMove(move);
    }

}
