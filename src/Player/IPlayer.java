package Player;

import UserInterface.IUserInterface;

/**
 * Created by einar on 2014-07-04.
 * This class is the base interface that all player classes must implement.
 */
public interface IPlayer {
    void getMove(IUserInterface userInterface);
}
