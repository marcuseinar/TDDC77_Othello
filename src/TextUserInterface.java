import java.util.Scanner;

/**
 * Created by einar on 2014-07-05.
 */
public class TextUserInterface implements IUserInterface {
    Scanner in;

    public TextUserInterface() {
        this.in = new Scanner(System.in);
    }

    @Override
    public void drawBoard(Marker[][] board) {
        System.out.println("    0  1  2  3  4  5  6  7");
        System.out.println("----------------------------");
        for(int y = 0; y < board.length; y++){
            System.out.print(y + " |");
            for(int x = 0; x < board.length; x++){
                System.out.print(" " + board[x][y].toString() + " ");
            }
            System.out.println("|");
        }
        System.out.println("--------------------------");
    }

    @Override
    public Coordinate getMove(HumanPlayer player) {
        int x,y;
        System.out.print(player.getMarker().toString() + "! Input x-coordinate:");
        x = in.nextInt();
        System.out.print(player.getMarker().toString() + "! Input y-coordinate:");
        y = in.nextInt();
        return new Coordinate(x,y);
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
        System.out.println("Game over!");
        System.out.println(winningMessage);
    }
}
