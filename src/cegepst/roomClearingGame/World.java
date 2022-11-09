package cegepst.roomClearingGame;

import cegepst.engine.graphics.Buffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class World {
    private final String MAP_PATH = "images/Gas Station.png";
    private BufferedImage background;

    public void load() {
        try {
            background = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(MAP_PATH));
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void draw(Buffer buffer, int cameraX, int cameraY) {
        buffer.drawImage(background, cameraX, cameraY);
    }

    public int getWidth() {
        return background.getWidth();
    }

    public int getHeight() {
        return background.getHeight();
    }
}
