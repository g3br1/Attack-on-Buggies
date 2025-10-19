    package Scenes;

    import Game.Game;
    import ui.Buttons;

    import javax.swing.*;
    import java.awt.*;

    import static Game.GameStates.PLAYING;
    import static Game.GameStates.SetGameState;

    public class Menu extends GameScene implements SceneMethods{

        private ImageIcon backgroundGif;

        private Buttons bPlaying, bSettings, bQuit, logo;

        public Menu(Game game) {
            super(game);
            loadMenuBackground();
            initButtons();
        }

        private void initButtons()
        {
            updateButtonsPosition();
        }

        private void updateButtonsPosition()
        {
            int width = getGame().getWidth();
            int height = getGame().getHeight();

            int x = width/2;
            int y = height/2;

            if (bPlaying == null){
            bPlaying = new Buttons(x - 120, y-20, 240, 120, "/resources/textures/ui/menubuttons/menu_button.png");
            bSettings = new Buttons(x - 120, y - 20+80+50, 240, 120, "/resources/textures/ui/menubuttons/menu_button.png");
            bQuit = new Buttons(x - 120, y - 20+160+100, 240, 120, "/resources/textures/ui/menubuttons/menu_button.png");
            logo = new Buttons(x-256, y-464, 512, 512, "/resources/textures/ui/menubuttons/logo.png");
            } else {
                bPlaying.updatePosition(x - 120, y-20);
                bSettings.updatePosition(x - 120, y - 20+80+50);
                bQuit.updatePosition(x - 120, y - 20+160+100);
                logo.updatePosition(x-256, y-464);
            }
        }

        @Override
        public void render(Graphics g) {
            updateButtonsPosition();
            if (backgroundGif != null) {
                int width = getGame().getWidth();
                int height = getGame().getHeight();
                Image img = backgroundGif.getImage();
                g.drawImage(img, 0, 0, width, height, null); // фон на весь екран
            }

            drawButtons(g);
        }

        @Override
        public void mouseClicked(int x, int y) {
            if(bPlaying.getBounds().contains(x, y)) {
                SetGameState(PLAYING);
            }
            if(bQuit.getBounds().contains(x, y)) {
                System.exit(0);
            }
        }

        @Override
        public void mouseMoved(int x, int y) {

            if (bPlaying != null) bPlaying.setMouseOver(false);
            if (bSettings != null) bSettings.setMouseOver(false);
            if (bQuit != null) bQuit.setMouseOver(false);

            if(bPlaying.getBounds().contains(x, y)) {bPlaying.setMouseOver(true);}
            if(bSettings.getBounds().contains(x, y)) {bSettings.setMouseOver(true);}
            if(bQuit.getBounds().contains(x, y)) {bQuit.setMouseOver(true);}
        }


        private void drawButtons(Graphics g) {
            bPlaying.draw(g);
            bSettings.draw(g);
            bQuit.draw(g);
            logo.draw(g);
        }

        private void loadMenuBackground() {
            try {
                java.net.URL gifURL = getClass().getResource("/resources/background.gif");
                if (gifURL != null) {
                    backgroundGif = new ImageIcon(gifURL);
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

