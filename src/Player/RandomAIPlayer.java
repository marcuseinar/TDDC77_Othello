package Player;

import Controllers.GameController;
import Models.Coordinate;

import java.util.ArrayList;
import java.util.Random;

/**
 * Author: Einar
 *
 * A "dumb" AI player that places valid moves at random.
 */
public class RandomAIPlayer extends AbstractAiPlayer {
    Random random;

    /**
     * Creates a player that places moves at random.
     */
    public RandomAIPlayer(GameController gameController) {
        super(gameController);
        this.random = new Random();
    }

    /**
     * Makes valid move at random.
     */
    @Override
    protected Coordinate getMove() {
        ArrayList<Coordinate> validMoves = board.getValidMoves(this.marker);
        System.out.println(board.getPlayerCounter(marker.getOpposite()));
        int i = random.nextInt(validMoves.size());
        return validMoves.get(i);
    }
}
