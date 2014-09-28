package Player;

import Controllers.GameController;
import Models.Board;
import Models.Coordinate;
import Models.Marker;

import java.util.ArrayList;

/**
 * Author: Einar
 *
 * An AI player that uses min max and the amount of moves available as a strength measurement for moves.
 */
public class MinMaxAIPlayer extends AbstractAiPlayer {
    int searchDepth;

    /**
     * Creates an AI player that uses a min max algorithm to decide what move to make.
     * Uses a mobility based approach to choosing move. The more moves available for oneself the better.
     * @param searchDepth the depth of the search in min max. Higher number = smarter AI.
     */
    public MinMaxAIPlayer(GameController gameController, int searchDepth) {
        super(gameController);
        this.searchDepth = searchDepth;
    }


    /**
     * Starts the min max search.
     * This method is recursive. It creates temporary boards that are evaluated
     * @param board the temporary board that is to evaluated
     * @param depth the depth to search
     * @param marker the type of marker that will make the next move.
     * @return the best <code>Coordinate</code> found for the next move.
     * @throws Exception
     */
    private int[] getMinMaxScore(Board board, int depth, Marker marker) throws Exception{
        if(marker == Marker.EMPTY){
            throw new Exception("minMax cannot take Models.Marker.EMPTY as argument in marker field");
        }
        boolean max  = (marker == getMarker());

        ArrayList<Coordinate> validMoves = board.getValidMoves(getMarker());
        int move[] = {validMoves.size(),-1,-1};
        if(depth == 0 || move[0] == 0){
            return move;
        }
        int tempScore;
        int bestScore = max ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        Coordinate bestMove = null;
        Board tempBoard;
        for (Coordinate tempMove: validMoves) {
            try {
                tempBoard = new Board(board.getBoard());
                tempBoard.makeMove(tempMove, getMarker());
                tempScore = getMinMaxScore(tempBoard, depth - 1, marker.getOpposite())[0];
                if (max && tempScore > bestScore) {
                    bestScore = tempScore;
                    bestMove = tempMove;
                } else if (!max && tempScore < bestScore) {
                    bestScore = tempScore;
                    bestMove = tempMove;

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        move = new int[]{bestScore, bestMove.getX(), bestMove.getY()};
        return move;
    }


    /**
     * Looks for the best move in a min max search.
     */
    @Override
    protected Coordinate getMove() {
        Coordinate move = null;
        try {
            int[] bestMove = getMinMaxScore(new Board(this.board.getBoard()), this.searchDepth, getMarker());
            move = new Coordinate(bestMove[1], bestMove[2]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return move;
    }
}
