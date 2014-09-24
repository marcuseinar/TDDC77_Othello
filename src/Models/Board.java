package Models;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by einar on 2014-06-29.
 * This class represents the model for an othello board.
 * It contains the basic rules needed to place markers.
 */

public class Board extends Observable{
    private Marker[][] board;
    private int blackCounter, whiteCounter;
    private int boardSize = 8; // Variablie for making it easy to retrieve board size and making versions of the game
    public boolean printErrors = false; //Set true if error printings are needed. WARNING! This might not work well with text based user interfaces.

    /**
     * Othello board
     */
    public Board() {
        this.board = new Marker[boardSize][boardSize];
        for(int i = 0; i < boardSize; i++){
            for(int j = 0; j < boardSize; j++){
                this.board[i][j] = Marker.EMPTY;
            }
        }
        this.board[3][3] = this.board[4][4] = Marker.BLACK;
        this.board[3][4] = this.board[4][3] = Marker.WHITE;
        this.blackCounter = whiteCounter = 2;
    }

    /**
     * Othello board
     * @param board the board that should be initiated
     * @throws Exception
     */
    public Board(Marker[][] board) throws Exception{
        if(board.length != this.boardSize || board[0].length != this.boardSize){
            throw new Exception("Models.Board must be sized " + this.boardSize + " x " + this.boardSize);
        }
        this.board = board;
        for(int i = 0; i < this.boardSize; i++){
            for(int j = 0; j < this.boardSize; j++) {
                switch (this.board[i][j]){
                    case BLACK:
                        blackCounter++; break;
                    case WHITE:
                        whiteCounter++;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * Returns the board as a Models.Marker[][] array.
     * @return the game board
     */
    public Marker[][] getBoard(){
        Marker[][] boardCopy = new Marker[boardSize][boardSize];
        for(int i = 0; i < boardSize; i++){
            for(int j = 0; j < boardSize; j++){
                boardCopy[i][j] = this.board[i][j];
            }
        }
        return boardCopy;
    }

    /**
     * Returns the boards size (height/width).
     * This method is not necessary but might be useful if different versions of the game is made.
     * @return the height/width of the board
     */
    public int getBoardSize() {
        return boardSize;
    }

    /**
     * Returns the amount of Markers on the board for a specific player.
     * @param m the type of marker that is counted on the board
     * @return  the amount of markers for the specific player, -1 if m is Models.Marker.EMPTY
     */
    public int getPlayerCounter(Marker m){
        if(m == Marker.EMPTY){
            return -1;
        }
        return (m == Marker.BLACK) ? blackCounter : whiteCounter;
    }

    /**
     * Places the specified marker at the coordinates if the move is valid.
     * Returns true if the move was successful and false if not.
     * @param coordinate the coordinate where the marker is being placed
     * @param m the type of marker that is being placed
     * @return true if the move is valid, otherwise false
     */
    public boolean makeMove(Coordinate coordinate, Marker m) { //TODO change do coordinate everywhere
        boolean returnValue = false;
        try {
            if(checkMove(coordinate, m, true)){
                setChanged();
                returnValue = true;
            }
        } catch (Exception e) {
            if(printErrors) {
                e.printStackTrace();
            }
        }
        notifyObservers();
        return returnValue;
    }

    /**
     * Checks if the coordinate is a valid place to put a marker of the specified color.
     * Returns true if the move is valid and false if not.
     * @param coordinate the coordinate for where to check
     * @param marker the type of marker that is being placed
     * @return  true if the move would be valid, otherwise false
     */
    public boolean checkMove(Coordinate coordinate, Marker marker){
        try {
            return checkMove(coordinate, marker, false);
        } catch (Exception e) {
            if(printErrors) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Checks if the coordinate is a valid place to put a marker of the specified color.
     * Returns true if the move is valid and false if not.
     * If the getMove parameter is true the method checks all directions around the placed marker and flips the pieces,
     * otherwise it returns true directly after a valid direction is found.
     * @param coordinate the coordinate for where to check
     * @param marker         the type of marker that is being placed
     * @param makeMove  set to true if a marker should be placed and opposite markers flipped, otherwise set false
     * @return          true if the move is valid, otherwise false
     */
    private boolean checkMove(Coordinate coordinate, Marker marker, boolean makeMove) throws Exception{
        if(marker == Marker.EMPTY || board[coordinate.getX()][coordinate.getY()] != Marker.EMPTY){
            return false;
        }
        boolean validMove = false;
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if (checkDirection(coordinate.getX(), coordinate.getY(), i, j, marker, makeMove)){
                    if(makeMove){ //if getMove, Carry on searching or else return true.
                        validMove = true;
                    }
                    else{
                        return true;
                    }
                }
            }
        }
        return validMove;
    }

    /**
     * This method checks if a direction is valid.
     * If the placed marker puts an uninterrupted line of one or more opponent pieces between two friendly markers
     * the move is considered valid.
     * If the variable getMove is set to true it also flips the opponents pieces between the friendly ones.
     * @param x         x-coordinate where the marker is being placed
     * @param y         y-coordinate where the marker is being placed
     * @param dx        defines  in what direction to move on hte x-axis each step, -1 = left, 0 = none, 1 = right
     * @param dy        defines  in what direction to move on hte y-axis each step, -1 = up, 0 = none, 1 = down
     * @param m         the type of marker that is being placed
     * @param makeMove  set to true if a marker should be placed and opposite markers flipped, otherwise set false
     * @return          true if move is valid, otherwise false
     * @throws Exception
     */
    private boolean checkDirection(int x, int y, int dx, int dy, Marker m, boolean makeMove) throws Exception{
        if(dx == 0 && dy == 0){
            return false;
        }
        int steps = 0;
        while(true){
            try{
                x += dx;
                y += dy;
                ++steps;
                if(steps > 1 && this.board[x][y] == m){
                     break;
                }
                else if(this.board[x][y] != m.getOpposite()){
                    return false;
                }
            }
            catch (ArrayIndexOutOfBoundsException e){
                 return false;
            }
        }
        if(makeMove) { //Flip pieces
            steps--;
            x -= dx;
            y -= dy;
            for (int i = steps; i > 0; i--) {
                flip(x,y);
                x-=dx;
                y-=dy;
            }
            this.board[x][y] = m; //Place the marker
            if(m == Marker.BLACK){
                blackCounter++;
            }
            else{
                whiteCounter++;
            }
            return true;
        }
        return true;
    }

    /**
     * Flips a piece to the opponents color
     * @param x x-coordinate where a marker is flipped
     * @param y y-coordinate where a marker is flipped
     * @return  true if flip was successful, otherwise false
     */
    private boolean flip(int x, int y) {
        Marker m = this.board[x][y] = this.board[x][y].getOpposite();
        if(m == Marker.EMPTY){
            return false;
        }
        if(m == Marker.BLACK){
            blackCounter++;
            whiteCounter--;
        }
        else{
            whiteCounter++;
            blackCounter--;
        }
        return true;
    }

    public ArrayList<Coordinate> getValidMoves(Marker marker) {
        ArrayList<Coordinate> validMoves = new ArrayList<Coordinate>();
        for(int x = 0; x < boardSize; x++){
            for(int y = 0; y < boardSize; y++){
                try {
                    if(checkMove(new Coordinate(x,y),marker,false))
                    validMoves.add(new Coordinate(x,y));
                } catch (Exception e) {
                    if(printErrors) {
                        e.printStackTrace();
                    }
                    return null;
                }
            }
        }
        return validMoves;
    }
}

