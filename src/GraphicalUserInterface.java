import javax.swing.*;
import java.awt.*;

/**
 * Created by einar on 2014-07-13.
 */
public class GraphicalUserInterface implements IUserInterface{
    private JFrame frame;
    private JPanel mainPanel;
    private JMenuBar menuBar;


    public GraphicalUserInterface() {
        createAndShowGUI();
    }

    private void createAndShowGUI(){
        //Frame
        frame = new JFrame("Othello - by einar");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        //Menu bar
        menuBar = new JMenuBar();
        //Game menu item
        JMenu gameMenu = new JMenu("Game");
        JMenuItem newGameItem = new JMenuItem("New Game");
        JMenuItem saveGameItem = new JMenuItem("Save game");
        JMenuItem loadGameItem = new JMenuItem("Load Game");
        JMenuItem quitItem = new JMenuItem("Quit");
        gameMenu.add(newGameItem);
        gameMenu.add(saveGameItem);
        gameMenu.add(loadGameItem);
        gameMenu.add(quitItem);
        //Options
        JMenu optionsMenu = new JMenu("Options");
        JMenuItem setAIDificultyItem = new JMenuItem("Set AI difficulty");
        optionsMenu.add(setAIDificultyItem);
        //Add menus to menu bar
        menuBar.add(gameMenu);
        menuBar.add(optionsMenu);


        //Main panel
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(700,500));
        mainPanel.setLayout(new BorderLayout());
        //Board panel
        JPanel boardPanel = new JPanel();
        boardPanel.setBackground(Color.GREEN);
        //Game information panel
        JPanel gameInformationPanel = new JPanel();
        gameInformationPanel.setBackground(Color.lightGray);
        gameInformationPanel.setPreferredSize(new Dimension(200,500));
        //Add sub panels to main panel
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        mainPanel.add(gameInformationPanel, BorderLayout.LINE_END);

        //Add components to frame
        frame.setJMenuBar(menuBar);
        frame.add(mainPanel);

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void drawBoard(Marker[][] board) {

    }

    @Override
    public Coordinate getMove(HumanPlayer player) {
        return null;
    }

    @Override
    public void displayWinner(Marker m) {

    }

    public static void main(String args[]){
        new GraphicalUserInterface();
    }
}
