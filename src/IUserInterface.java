/**
 * Created by einar on 2014-07-04.
 * This is the interface that all user interfaces must implement
 */
public interface IUserInterface {
    public void drawBoard(Marker[][] board);
    public Coordinate getMove(HumanPlayer player);
    public void displayWinner(Marker m);
}
