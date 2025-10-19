package Game;

import Scenes.Menu;
import Scenes.Playing;
import Scenes.Settings;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Game extends JFrame implements Runnable {

    private GameScreen gameScreen;
    private BufferedImage image;

    private Thread gameThread;

    private final double FPS_SET = 120.0;
    private final double UPS_SET = 60.0;

    private Dimension size;
    private Render render;
    private Menu menu;
    private Playing playing;
    private Settings settings;

    public Game() {
        initClasses();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        size = new Dimension(1920, 1080);
        setMinimumSize(size);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        add(gameScreen);
        pack();
        setVisible(true);                         // видимість вікна
    }

    private void initClasses() {
        render = new Render(this);
        gameScreen = new GameScreen(this);
        menu = new Menu(this);
        playing = new Playing(this);
        settings = new Settings(this);
    }

    private void start() {
        gameThread = new Thread(this) {};

        gameThread.start();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.gameScreen.initInputs();
        game.start();
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long lastFrame = System.nanoTime();
        long lastUpdate = System.nanoTime();
        long lastTimeCheck = System.currentTimeMillis();

        int updates = 0;
        int frames = 0;

        long now;

        while (true) {

            now = System.nanoTime();
            // Рендер

            if (System.nanoTime() - lastFrame >= timePerFrame) {
                lastFrame = now;
                repaint();
                frames++;
            }

            // Оновлення

            if (System.nanoTime() - lastUpdate >= timePerUpdate) {
                lastUpdate = now;
                updates++;
            }

            if(System.currentTimeMillis() - lastTimeCheck >= 1000) {
                // System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }
        }

    }

    public Render getRender(){
        return render;
    }

    public Menu getMenu(){
        return menu;
    }

    public Playing getPlaying(){
        return playing;
    }

    public Settings getSettings(){
        return settings;
    }

}
