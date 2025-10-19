package Game;

import Inputs.KeyboardListener;
import Inputs.MyMouseListener;

import javax.swing.JPanel;
import java.awt.*;

public class GameScreen extends JPanel
{
    private final Game game;
    private Dimension size;
    private MyMouseListener myMouseListener;
    private KeyboardListener keyboardListener;

    public GameScreen (Game game) {
        this.game = game;

        setPanelSize();
    }

    private void setPanelSize(){
        size = new Dimension(1920, 1080);

        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void initInputs() {
        myMouseListener = new MyMouseListener(game);
        keyboardListener = new KeyboardListener();

        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        addKeyListener(keyboardListener);

        requestFocus();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);              // графіка гри

        game.getRender().render(g);
    }

}

