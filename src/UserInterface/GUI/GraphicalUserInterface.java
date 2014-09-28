package UserInterface.GUI;

import Controllers.GameController;
import Models.Marker;
import Player.AbstractPlayer;
import Player.HumanPlayer;
import Player.MinMaxAIPlayer;
import Player.RandomAIPlayer;
import UserInterface.AbstractUserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by einar on 2014-07-13.
 */
public class GraphicalUserInterface extends AbstractUserInterface implements ActionListener{
    private JFrame frame;
    private JPanel mainPanel;
    private JMenuBar menuBar;
    private BoardPanel boardPanel;
    private GameInformationPanel gameInformationPanel;
    private boolean clickable = false;
    private AbstractPlayer blackPlayerSelection;
    private AbstractPlayer whitePlayerSelection;

    private GameController gameController;


    public GraphicalUserInterface() {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    createAndShowGUI();
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        gameController = new GameController(this);
        blackPlayerSelection = new HumanPlayer(this, gameController);
        whitePlayerSelection = new HumanPlayer(this, gameController);
        gameController.setPlayers(blackPlayerSelection,whitePlayerSelection);
    }

    /**
     * Creates the GUI
     */
    private void createAndShowGUI(){
        //Frame
        frame = new JFrame("Othello - by einar");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        //Menu bar
        menuBar = new JMenuBar();
        //Game menu item
        final JMenu gameMenu = new JMenu("Game");
        JMenuItem newGameMenuItem = new JMenuItem("New Game");
        newGameMenuItem.setAccelerator(KeyStroke.getKeyStroke('N', ActionEvent.CTRL_MASK));
        newGameMenuItem.setActionCommand("NEW_GAME");
        newGameMenuItem.addActionListener(this);

        gameMenu.add(newGameMenuItem);

        //Options
        JMenu optionsMenu = new JMenu("Options");

        // ----- Black player settings -----
        JRadioButtonMenuItem humanPlayerRadioButton = new JRadioButtonMenuItem("Human Player");
        humanPlayerRadioButton.setActionCommand("BP_HUMAN");
        humanPlayerRadioButton.addActionListener(this);
        JRadioButtonMenuItem randomAiPlayerRadioButton = new JRadioButtonMenuItem("Random AI Player");
        randomAiPlayerRadioButton.setActionCommand("BP_RANDOM");
        randomAiPlayerRadioButton.addActionListener(this);
        JRadioButtonMenuItem minmaxAiPlayerRadioButton = new JRadioButtonMenuItem("Min-Max AI PLayer");
        minmaxAiPlayerRadioButton.setActionCommand("BP_MIN_MAX");
        minmaxAiPlayerRadioButton.addActionListener(this);

        ButtonGroup blackPlayerSelectionGroup = new ButtonGroup();
        blackPlayerSelectionGroup.add(humanPlayerRadioButton);
        blackPlayerSelectionGroup.add(randomAiPlayerRadioButton);
        blackPlayerSelectionGroup.add(minmaxAiPlayerRadioButton);

        humanPlayerRadioButton.setSelected(true);

        JMenu blackPlayerMenu = new JMenu("Black Player");
        optionsMenu.add(blackPlayerMenu);
        blackPlayerMenu.add(humanPlayerRadioButton);
        blackPlayerMenu.add(randomAiPlayerRadioButton);
        blackPlayerMenu.add(minmaxAiPlayerRadioButton);


        // ----- White player settings -----
        humanPlayerRadioButton = new JRadioButtonMenuItem("Human Player");
        humanPlayerRadioButton.setActionCommand("WP_HUMAN");
        humanPlayerRadioButton.addActionListener(this);
        randomAiPlayerRadioButton = new JRadioButtonMenuItem("Random AI Player");
        randomAiPlayerRadioButton.setActionCommand("WP_RANDOM");
        randomAiPlayerRadioButton.addActionListener(this);
        minmaxAiPlayerRadioButton = new JRadioButtonMenuItem("Min-Max AI PLayer");
        minmaxAiPlayerRadioButton.setActionCommand("WP_MIN_MAX");
        minmaxAiPlayerRadioButton.addActionListener(this);

        ButtonGroup whitePlayerSelectionGroup = new ButtonGroup();
        whitePlayerSelectionGroup.add(humanPlayerRadioButton);
        whitePlayerSelectionGroup.add(randomAiPlayerRadioButton);
        whitePlayerSelectionGroup.add(minmaxAiPlayerRadioButton);

        humanPlayerRadioButton.setSelected(true);

        JMenu whitePlayerMenu = new JMenu("White Player");
        optionsMenu.add(whitePlayerMenu);
        whitePlayerMenu.add(humanPlayerRadioButton);
        whitePlayerMenu.add(randomAiPlayerRadioButton);
        whitePlayerMenu.add(minmaxAiPlayerRadioButton);

        //Add menus to menu bar
        menuBar.add(gameMenu);
        menuBar.add(optionsMenu);


        //Main panel
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(700,500));
        mainPanel.setLayout(new BorderLayout());

        //Models.Board panel
        boardPanel = new BoardPanel();
        boardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(clickable){
                    clickable = false;
                    System.out.println("clickable = " + clickable);
                    SquarePanel caller = (SquarePanel) e.getSource();
                    gameController.makeMove(caller.getCoordinate());
                }
            }
        });

        //Game information panel
        gameInformationPanel  = new GameInformationPanel();
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
    public void drawBoard(final Marker[][] board) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                boardPanel.drawBoard(board);
            }
        });
    }

    @Override
    public void getMove(HumanPlayer player) {
        clickable = true;
        System.out.println("clickable = " + clickable);
    }

    @Override
    public void displayWinner(final Marker marker) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gameInformationPanel.displayWinner(marker);
            }
        });
    }

    @Override
    public void setCurrentPlayer(final Marker marker) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gameInformationPanel.setPlayerTurn(marker);
            }
        });
    }

    public static void main(String args[]){
        AbstractUserInterface ui = new GraphicalUserInterface();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        switch (s) {
            case "NEW_GAME": gameController.newGame(); break;
            case "BP_HUMAN": gameController.setPlayers(new HumanPlayer(this, gameController), null); break;
            case "BP_RANDOM": gameController.setPlayers(new RandomAIPlayer(gameController), null); break;
            case "BP_MIN_MAX": gameController.setPlayers(new MinMaxAIPlayer(gameController, 10), null); break;
            case "WP_HUMAN": gameController.setPlayers(null, new HumanPlayer(this, gameController)); break;
            case "WP_RANDOM": gameController.setPlayers(null, new RandomAIPlayer(gameController)); break;
            case "WP_MIN_MAX": gameController.setPlayers(null, new MinMaxAIPlayer(gameController, 10)); break;
        }
    }
}
