import Player.IPlayer;
import UserInterface.IUserInterface;
import com.sun.tools.javac.util.Pair;

/**
 * Created by einar on 2014-06-29.
 * This class represents a game of othello and is responsible for controlling if which players turn it is,
 * if the game is over, updating the user interface etc.
 */
public class Game {
    private Board board;
    private IPlayer player1;
    private IPlayer player2;
    private IUserInterface userInterface;


    public Game(IPlayer player1, IPlayer player2, IUserInterface userInterface){
        this.board = new Board();
        this.player1 = player1;
        this.player2 = player2;
        this.userInterface = userInterface;
    }

    public void run(){
        while(!gameOver()){
            getPlayerTurn().getMove(this.userInterface); //TODO: decide return type
            //make move
            //if next player has > 1 valid moves, change turn
            //else if same player has > 1 moves restart loop
            //else gameOver()
        }
        //Display winner:
        //Wait for ui input
    }

    private IPlayer getPlayerTurn() {
        return player1;
    }

    private boolean gameOver() { //TODO: Make this a boolean field instead?
        return false;
    }
}
