/**
 * Created by einar on 2014-06-29.
 * This class represents a game of othello and is responsible for controlling if which players turn it is,
 * if the game is over, updating the user interface etc.
 */
public class Game {
    private Board board;
    private AbstractPlayer[] players = new AbstractPlayer[2];

    private IUserInterface userInterface;


    public Game(AbstractPlayer blackPlayer, AbstractPlayer whitePlayer, IUserInterface userInterface) throws Exception{
        if(blackPlayer.getMarker() != Marker.BLACK || whitePlayer.getMarker() != Marker.WHITE){
            throw new Exception("Error: Black player must have black marker and white player must have white marker.");
        }
        this.board = new Board();
        this.players[0] = blackPlayer;
        this.players[1] = whitePlayer;
        this.userInterface = userInterface;
    }

    public void run(){
        Coordinate move;
        boolean validMove = false;
        int currentPlayerIndex = 0;
        boolean gameOver = false;
        while(!gameOver){
            userInterface.drawBoard(this.board.getBoard());
            move = players[currentPlayerIndex].getMove();
            validMove = board.makeMove(move.getX(), move.getY(), players[currentPlayerIndex].getMarker());
            if(validMove){
                if(board.getValidMoves(players[currentPlayerIndex].getMarker().getOppostie()).size() > 0){
                    currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
                }
                else if(board.getValidMoves(players[currentPlayerIndex].getMarker()).size() > 0){
                    continue;
                }
                else{
                    gameOver = true;
                }
            }
        }
        userInterface.displayWinner(calculateWinner());
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
}
