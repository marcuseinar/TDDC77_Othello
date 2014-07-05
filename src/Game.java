/**
 * Created by einar on 2014-06-29.
 * This class represents a game of othello and is responsible for controlling if which players turn it is,
 * if the game is over, updating the user interface etc.
 */
public class Game {
    private Board board;
    private AbstractPlayer player1;
    private AbstractPlayer player2;
    private IUserInterface userInterface;


    public Game(HumanPlayer player1, HumanPlayer player2, IUserInterface userInterface){
        this.board = new Board();
        this.player1 = player1;
        this.player2 = player2;
        this.userInterface = userInterface;
    }

    public void run(){
        Coordinate move;
        boolean validMove = false;
        AbstractPlayer currentPlayer = this.player1;
        boolean gameOver = false;
        while(!gameOver){
            move = currentPlayer.getMove();
            validMove = board.makeMove(move.getX(), move.getY(), currentPlayer.getMarker());
            if(validMove){
                if(board.getValidMoves(currentPlayer.getMarker().getOppostie()).size() > 0){

                }
                else if(board.getValidMoves(currentPlayer.getMarker()).size() > 0){

                }
                else{
                    gameOver = true;
                }

            }
        }
        userInterface.displayWinner();
    }
}
