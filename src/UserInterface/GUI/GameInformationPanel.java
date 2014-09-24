package UserInterface.GUI;

import Models.Marker;

import javax.swing.*;
import java.awt.*;

/**
 * Created by einar on 2014-07-14.
 */
public class GameInformationPanel extends JPanel{
    JLabel playerTurnLabel = new JLabel("Player Turn:");
    SquarePanel playerTurnSquarePanel;
    JLabel blackCountLabel;
    JLabel whiteCountLabel;

    public GameInformationPanel(){
        setPreferredSize(new Dimension(200, 500));

        setBackground(Color.lightGray);

        playerTurnSquarePanel = new SquarePanel(null);
        playerTurnSquarePanel.setPreferredSize(new Dimension(80,80));
        blackCountLabel = new JLabel();
        whiteCountLabel = new JLabel();

        add(playerTurnLabel);
        add(playerTurnSquarePanel);
        add(blackCountLabel);
        add(whiteCountLabel);
    }

    public void setPlayerTurn(Marker marker){
        playerTurnSquarePanel.setMarker(marker);
    }

    public void setMarkerCounters(int blackCount, int whiteCount){
        blackCountLabel.setText("Black marker counter: " + blackCount);
        whiteCountLabel.setText("White marker counter: " + whiteCount);
    }

    public void displayWinner(Marker m) {

    }
}
