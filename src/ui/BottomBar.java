package ui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static Game.GameStates.MENU;
import static Game.GameStates.SetGameState;

public class BottomBar {
    private int x, y, width, height;
    private BufferedImage texture;
    private Buttons bMenu;

    public BottomBar(int x, int y, int width, int height, String texturePath)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        loadTexture(texturePath);
    }

    public void updatePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void updateSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g) {
        if (texture != null) {
            g.drawImage(texture, x, y, width, height, null);
        }
    }


    private void loadTexture(String path) {
        try {
            texture = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Failed to load button texture: " + path);
            e.printStackTrace();

        }
    }
}
