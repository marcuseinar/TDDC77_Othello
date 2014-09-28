package UserInterface;

import Models.Board;
import Models.Marker;
import Player.AbstractPlayer;
import Player.HumanPlayer;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by einar on 2014-07-04.
 * This is the interface that all user interfaces must implement
 */
public abstract class AbstractUserInterface implements Observer{
    protected AbstractPlayer currentPlayer;

    abstract public void drawBoard(Marker[][] board);
    abstract public void getMove(HumanPlayer player);
    abstract public void displayWinner(Marker m);
    abstract public void setCurrentPlayer(Marker marker);


    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Board){
            drawBoard(((Board) o).getBoard());
        }
    }
}
