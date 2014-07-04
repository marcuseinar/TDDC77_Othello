import Player.IPlayer;
import UserInterface.IUserInterface;

/**
 * Created by einar on 2014-06-29.
 * This class represents a game of othello and is responsible for controlling if which players turn it is,
 * if the game is over, updating the user interface etc.
 */
public class Game {
    private Board board;
    private IPlayer IPlayer1;
    private IPlayer IPlayer2;
    private IUserInterface ui;

    public Game(){

    }
}
