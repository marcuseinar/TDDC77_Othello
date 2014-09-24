package UserInterface;

import Controllers.GameController;
import Models.Board;
import Models.Marker;
import Player.AbstractPlayer;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by einar on 2014-07-04.
 * This is the interface that all user interfaces must implement
 */
public abstract class AbstractUserInterface extends Observable implements Observer{
    protected AbstractPlayer currentPlayer;

    abstract public void drawBoard(Marker[][] board);
    abstract public void getMove();
    abstract public void displayWinner(Marker m);
    abstract protected void setCurrentPlayer(AbstractPlayer player);

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Board){
            drawBoard(((Board) arg).getBoard());
        }
        else if(o instanceof GameController && arg instanceof AbstractPlayer){
                setCurrentPlayer((AbstractPlayer) arg);
        }
        else if(o instanceof AbstractPlayer){
            getMove();
        }
    }


}
