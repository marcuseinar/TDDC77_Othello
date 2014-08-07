package Player;

import Models.Coordinate;

import java.util.ArrayList;
import java.util.Random;

/**
 * Author: EInar
 *
 * A "dumb" AI player that places valid moves at random.
 */
public class RandomAIPlayer extends AbstractAiPlayer {
    Random random;

    /**
     * Creates a player that places moves at random.
     */
    public RandomAIPlayer() {
        this.random = new Random();
    }

    /**
     * Makes valid move at random.
     */
    @Override
    void makeMove() {
        ArrayList<Coordinate> validMoves = board.getValidMoves(this.marker);
        int i = random.nextInt(validMoves.size());
        board.makeMove(validMoves.get(i), this.marker);
    }
}
