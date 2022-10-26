package cegepst.roomClearingGame;

import cegepst.engine.graphics.Buffer;
import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.controls.MovementController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends ControllableEntity {
    private static final String SPRITE_PATH = "images/Officer128.png";

    private BufferedImage spriteSheet;
    private Mouse mouse;

    public Player(MovementController controller, Mouse mouse) {
        super(controller);
        this.mouse = mouse;
        setDimension(128, 128);
        setSpeed(5);
    }

    public void load() {
        loadSpriteSheet();
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(buffer.rotateImage(spriteSheet, findSpriteRotationAngle()), x - 52, y - 69);
    }

    @Override
    public void update() {
        super.update();
        moveWithController();
    }

    private void loadSpriteSheet() {
        try {
            spriteSheet = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(SPRITE_PATH));
        } catch (IOException e) {
            System.out.println("Sprite sheet couldn't load");
        }
    }

    private double findSpriteRotationAngle() {
        int deltaX = (x) - mouse.getX();
        int deltaY = (y) - mouse.getY();
        return -Math.atan2(deltaX, deltaY);
    }
}
