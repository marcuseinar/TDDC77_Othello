
/**
 * Created by einar on 2014-07-04.
 * This class is the base interface that all player classes must implement.
 */
public class HumanPlayer extends AbstractPlayer{
    Marker marker;
    IUserInterface userInterface;
    public HumanPlayer(Marker marker, IUserInterface userInterface){
        super(marker);
        this.marker = marker;
        this.userInterface = userInterface;
    }

    public Marker getMarker(){
        return this.marker;
    }

    @Override
    public Coordinate getMove(){
        return this.userInterface.getMove(this);
    }


}
