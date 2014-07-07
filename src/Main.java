/**
 * Created by einar on 2014-07-07.
 */
public class Main {
    public static void main(String args[]){
        IUserInterface ui = new TextUserInterface();
        AbstractPlayer p1 = new HumanPlayer(Marker.BLACK, ui);
        AbstractPlayer p2 = new HumanPlayer(Marker.WHITE, ui);
        Game g = null;
        try {
            g = new Game(p1,p2,ui);
        } catch (Exception e) {
            e.printStackTrace();
        }
        g.run();
    }
}
