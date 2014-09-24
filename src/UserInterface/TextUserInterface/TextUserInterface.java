package UserInterface.TextUserInterface;

import Models.Board;
import Models.Coordinate;
import Models.Marker;
import Player.AbstractPlayer;
import UserInterface.AbstractUserInterface;

import java.util.Scanner;

/**
 * Created by einar on 2014-07-05.
 */
public class TextUserInterface extends AbstractUserInterface {
    Scanner in;

    public TextUserInterface(Board board) {
        this.in = new Scanner(System.in);
    }

    @Override
    public void drawBoard(Marker[][] board) {
        System.out.println("    0   1   2   3   4   5   6   7");

        for(int y = 0; y < board.length; y++){
            System.out.println("  +---+---+---+---+---+---+---+---+");
            System.out.print(y + " ");
            for(int x = 0; x < board.length; x++){
                System.out.print("| " + getMarkerAsChar(board[x][y]) + " ");
            }
            System.out.println("|");
        }
        System.out.println("  +---+---+---+---+---+---+---+---+");
    }

    @Override
    public void getMove() {
        int x,y;
        System.out.print("! Input x-coordinate:");
        x = in.nextInt();
        System.out.print("! Input y-coordinate:");
        y = in.nextInt();
        setChanged();
        notifyObservers(new Coordinate(x,y));
        //return new Coordinate(x,y);
    }

    @Override
    public void displayWinner(Marker marker) {
        String winningMessage = "";
        switch (marker) {
            case EMPTY:
                winningMessage = "Draw!";
                break;
            case BLACK:
                winningMessage = "Black wins!";
                break;
            case WHITE:
                winningMessage = "White wins!";
                break;
        }
        System.out.println("Controllers.Game over!");
        System.out.println(winningMessage);
    }

    @Override
    protected void setCurrentPlayer(AbstractPlayer player) {

    }

    private char getMarkerAsChar(Marker marker){
        switch (marker){
            case BLACK:
                return 'B';
            case WHITE:
                return 'W';
            default:
                return ' ';
        }
    }

    private void drawMainMenu(){
        System.out.println("--- MAIN MENU --------------------");
        System.out.println("  1. New Controllers.Game");
        System.out.println("  2. Quit");
    }
}
