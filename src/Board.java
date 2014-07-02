import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by einar on 2014-06-29.
 */

public class Board {
    private Marker[][] board;
    private int blackCounter, whiteCounter;


    public Board() {
        this.board = new Marker[8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                this.board[i][j] = Marker.EMPTY;
            }
        }
        this.board[3][3] = this.board[4][4] = Marker.BLACK;
        this.board[3][4] = this.board[4][3] = Marker.WHITE;
        this.blackCounter = whiteCounter = 2;
    }

    public Marker[][] getBoard(){
        return this.board;
    }

    /**
     * Places the specified marker at the coordinates if the move is valid.
     * Returns true if the move was successful and false if not.
     * @param x
     * @param y
     * @param m
     * @return
     */
    public boolean makeMove(int x, int y, Marker m){
        if(this.board[x][y] == Marker.EMPTY){
            return checkMove(x, y, m, true);
        }
        return false;
    }

    /**
     * Checks if the coordinate is a valid place to put a marker of the specified color.
     * Returns true if the move is valid and false if not.
     * @param x
     * @param y
     * @param m
     * @return
     */
    public boolean checkMove(int x, int y, Marker m){
        return checkMove(x, y, m, false);
    }

    /**
     *
     * @param x
     * @param y
     * @param m
     * @param makeMove
     * @return
     */
    private boolean checkMove(int x, int y, Marker m, boolean makeMove) {
        boolean validMove = false;
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if (checkDirection(x, y, i, j, m, makeMove)){
                    if(makeMove){ //if makeMove, Carry on searching or else return true.
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

    private boolean checkDirection(int x, int y, int dx, int dy, Marker m, boolean makeMove) {
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
                else if(this.board[x][y] != m.getOppostie()){
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
                this.board[x][y] = m;
                x-=dx;
                y-=dy;
            }
            this.board[x][y] = m;
        }
        return true;
    }


    public static void main(String args[]){
        Board board = new Board();
        Scanner in = new Scanner(System.in);
        int x, y;
        Marker m = Marker.BLACK;
        while(true) {
            System.out.println("-------------------------");
            System.out.println("   0  1  2  3  4  5  6  7");
            for (int i = 0; i < 8; i++) {
                System.out.println(i + " " + Arrays.toString(board.getBoard()[i]));
            }
            System.out.println("Player turn: " + m.toString());
            System.out.print("Input x coord: ");
            x = in.nextInt();
            System.out.print("Input y coord: ");
            y = in.nextInt();
            if(board.makeMove(x,y,m)){
                System.out.println("Move valid!");
                m = m.getOppostie();
            }
            else{
                System.out.println("Invalid move, try again!");
            }
        }
    }


}

