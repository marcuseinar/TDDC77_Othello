package UserInterface.GUI;

import Models.Coordinate;
import Models.Marker;

import javax.swing.*;
import java.awt.*;

/**
 * Created by einar on 2014-07-14.
 */
public class SquarePanel extends JPanel{
    private static Color SQUARE_COLOR = new Color(52, 186, 35);
    Coordinate coordinate;
    Marker marker = Marker.EMPTY;
    public SquarePanel(Coordinate coordinate){
        this.coordinate = coordinate;
        setBackground(SQUARE_COLOR);
    }

    public void setMarker(Marker marker){
        this.marker = marker;
        repaint();
    }

    public Coordinate getCoordinate(){
        return this.coordinate;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color c = null;
        switch (marker){
            case BLACK:
                c = Color.BLACK;
                break;
            case WHITE:
                c = Color.WHITE;
                break;
            case EMPTY:
                return;
        }
        g.setColor(c);
        g.fillOval(2, 2, getHeight() - 4, getWidth() - 4);
    }



    class MarkerPanel extends JPanel{
        int x, y, height, width;
        public MarkerPanel(int height, int width){
            this.x = 1;
            this.y = 1;
            this.height = height - 2;
            this.width = width - 2;
            repaint();
        }

        public void paintComponent(Graphics g) {
            g.setColor(Color.BLACK);
            g.fillOval(0, 0, 40, 40);
        }
    }
}