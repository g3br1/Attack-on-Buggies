package ui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Buttons {
    private int x, y, width, height;
    private Rectangle bounds;
    private BufferedImage texture;
    private boolean mouseOver;
    private BufferedImage mouseOverTexture;

    public Buttons(int x, int y, int width, int height, String texturePath) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        loadTexture(texturePath);
        initBounds();
    }

    private void initBounds() {
        this.bounds = new Rectangle(x, y, width, height);
    }

    public void updatePosition(int newX, int newY) {
        this.x = newX;
        this.y = newY;
        this.bounds = new Rectangle(x, y, width, height); // Оновлюємо межі
    }

    private void loadTexture(String path) {
        try {
            texture = ImageIO.read(getClass().getResourceAsStream(path));
            mouseOverTexture = ImageIO.read(getClass().getResource("/resources/textures/ui/sprites/button_mouse_over.png"));
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Failed to load button texture: " + path);
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        if (texture != null) {
            g.drawImage(texture, x, y, width, height, null);
            if (mouseOver) {
                g.drawImage(mouseOverTexture, x, y, width, height, null);
            }
        }
    }

    public void setMouseOver(boolean mouseOver)
    {
        this.mouseOver = mouseOver;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
