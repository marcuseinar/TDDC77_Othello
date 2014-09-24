package Controllers;

import Models.Board;
import Models.Coordinate;
import Models.Marker;
import Player.AbstractPlayer;
import UserInterface.AbstractUserInterface;

import java.util.Observable;
import java.util.Observer;

/**
 * Author: Einar
 *
 * The GameController controls the game (hence the name).
 * It is responsible for placing moves on the board, keeping track of the current player and deciding whether the game
 * is over.
 */
public class GameController extends Observable implements Observer{
    private Board board;
    private AbstractPlayer[] players = new AbstractPlayer[2];
    private int currentPlayerIndex;
    private AbstractUserInterface userInterface;

    public GameController(AbstractUserInterface userInterface){
        userInterface.addObserver(this);
        this.userInterface = userInterface;
        addObserver(userInterface);
    }

    /**
     * Sets the player class for the black and white players if the parameter is not null.
     * @param blackPlayer the black player type
     * @param whitePlayer the white player type
     */
    public void setPlayers(AbstractPlayer blackPlayer, AbstractPlayer whitePlayer){
        if(blackPlayer != null){
            this.players[0] = blackPlayer;
            this.players[0].setMarker(Marker.BLACK);
            System.out.println("Black player has been set to player class: " + this.players[0].getClass().getName());
        }
        if(whitePlayer != null){
            this.players[1] = whitePlayer;
            this.players[1].setMarker(Marker.WHITE);
            System.out.println("White player has been set to player class: " + this.players[1].getClass().getName());
        }
    }

    /**
     * Returns the current board used in game
     * @return the current Board
     */
    public Board getBoard(){
        return board;
    }

    /**
     * Starts a new game with a new Board and black as the staring player
     */
    public void newGame(){
        System.out.println("Creating new game");
        this.board = new Board();
        startGame(this.board, 0);
    }

    /**
     * Starts a new game initiated with the specified board and starting player
     * @param board the Board that the game should be initiated with
     * @param currentPlayerIndex the Starting player - 0 for black, 1 for white
     */
    private void startGame(Board board, int currentPlayerIndex) {
        System.out.println("Starting game");
        setBoard(board);
        this.currentPlayerIndex = currentPlayerIndex;
        getMove(this.players[this.currentPlayerIndex]);
    }

    private void setBoard(Board board){
        System.out.println("Setting board");
        this.board = board;
        this.board.addObserver(this.userInterface);
        setChanged();
        notifyObservers(board);
    }

    private void getMove(AbstractPlayer player){
        setChanged();
        notifyObservers(player.getMarker());
        player.wakePlayer();
    }

    private void makeMove(Coordinate coordinate, Marker marker){
        if(marker != players[currentPlayerIndex].getMarker()){
            return;
        }
        if(board.makeMove(coordinate, marker)){
            if(board.getValidMoves(marker.getOpposite()).size() > 0){
                this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.players.length;
                setChanged();
                notifyObservers(players[currentPlayerIndex].getMarker());
            }
            else if(this.board.getValidMoves(this.players[this.currentPlayerIndex].getMarker()).size() == 0){
                gameOver();
                return;
            }
        }
        getMove(this.players[this.currentPlayerIndex]);
    }

    private void gameOver(){
        this.userInterface.displayWinner(calculateWinner());
    }

    private Marker calculateWinner() {
        int blackMarkers = board.getPlayerCounter(Marker.BLACK);
        int whiteMarkers = board.getPlayerCounter(Marker.WHITE);
        if(blackMarkers == whiteMarkers){
            return Marker.EMPTY;
        }
        else{
            return (blackMarkers > whiteMarkers) ? Marker.BLACK : Marker.WHITE;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof AbstractPlayer && arg instanceof Coordinate){
            System.out.println("move made by player");
            makeMove((Coordinate) arg, ((AbstractPlayer) o).getMarker());
        }
    }
}

