package UserInterface.GUI;

import Models.Coordinate;
import Models.Marker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * Created by einar on 2014-07-14.
 */
public class BoardPanel extends JPanel{
    private final SquarePanel[][] squares;

    public BoardPanel() {
        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension(500,500));
        setLayout(new GridLayout(8,8,5,5));
        squares =  new SquarePanel[8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                squares[i][j] = new SquarePanel(new Coordinate(i,j));
                add(squares[i][j]);
            }
        }
    }

    public void drawBoard(Marker[][] board){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                squares[i][j].setMarker(board[i][j]);
            }
        }
    }

    @Override
    public synchronized void addMouseListener(MouseListener mouseListener) {
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                squares[j][i].addMouseListener(mouseListener);
            }
        }
    }
}
