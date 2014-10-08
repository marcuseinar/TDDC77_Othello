import UserInterface.GUI.GraphicalUserInterface;

import javax.swing.*;

/**
/ * Created by einar on 2014-07-07.
/ */
public class Main {
    public static void main(String args[]){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GraphicalUserInterface();
            }
        });
    }
}
