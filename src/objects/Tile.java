package objects;

import java.awt.image.BufferedImage;

public class Tile {

    private BufferedImage sprite;
    private int id;

    public Tile(BufferedImage sprite) {
        this.sprite = sprite;
        this.id = id;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public int getId() {
        return id;
    }
}
