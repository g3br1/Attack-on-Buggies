package levels;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadAtlas {

    public static BufferedImage getSpriteAtlas() {
        BufferedImage image = null;
        InputStream is = LoadAtlas.class.getClassLoader().getResourceAsStream("resources/textures/Tileset/Ground/ground_tileset.png");

        try {
            image = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }
}
