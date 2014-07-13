import javax.swing.*;
import java.awt.*;



@Deprecated
public class GraphicalUserInterfaceFrame extends JFrame{
    public GraphicalUserInterfaceFrame(){

        init();
        pack();
        setVisible(true);
        setResizable(false);
    }

    private void init(){
        setTitle("Othello");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Menu Bar
        JMenuBar menuBar = initMenuBar();


        //Main panel
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(new BorderLayout());
        setSize(900, 500);

        //Board panel
        panel.add(new BoardPanel(), BorderLayout.CENTER);
        panel.add(new InfoPanel(), BorderLayout.LINE_END);

        //Info panel
    }

    private JMenuBar initMenuBar() {
        JMenuBar menuBar= new JMenuBar();

        JMenu gameMenu = new JMenu("Game");
        menuBar.add(gameMenu);




        setJMenuBar(menuBar);
        return menuBar;
    }

    public static void main(String args[]){
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        GraphicalUserInterfaceFrame gui = new GraphicalUserInterfaceFrame();
                    }
                }
        );
    }
}

class Square extends JPanel{
    boolean mouseDown;
    boolean inside;

    public Square(){
       init();
    }

    private void init(){
        setBackground(new Color(52, 186, 35, 200));
    }
}

class BoardPanel extends JPanel{
    public BoardPanel() {
        init();
    }

    private void init(){
        setPreferredSize(new Dimension(500, 500));
        setLayout(new GridLayout(8,8,5,5));
        setBackground(Color.BLACK);
        Square s;
        for(int i = 0; i < 64; i++){
            s = new Square();
            add(s);
        }
    }

    public void test(){
        System.out.println("funkar, osnyggt?");
    }
}

class InfoPanel extends JPanel{
    public InfoPanel(){
        init();
    }

    private void init() {
        setPreferredSize(new Dimension(200,500));
        //setLayout(new SpringLayout());
        setBackground(Color.GRAY);
        setSize(300,500);

        add(new JLabel("HELLO WORLD!"));
    }
}