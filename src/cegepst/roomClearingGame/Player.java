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
    private int xDelta;
    private int yDelta;

    public Player(MovementController controller, Mouse mouse, World world) {
        super(controller);
        this.mouse = mouse;
        setDimension(128, 128);
        setSpeed(3);
    }

    public void load() {
        loadSpriteSheet();
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(buffer.rotateImage(spriteSheet, findSpriteRotationAngle()), (800 / 2) - 52, (600 / 2) - 69);
    }

    @Override
    public void update() {
        xDelta = getX();
        yDelta = getY();
        super.update();
        moveWithController();
        xDelta -= getX();
        yDelta -= getY();
    }

    public int getXDelta() {
        return xDelta;
    }

    public int getYDelta() {
        return yDelta;
    }

    private void loadSpriteSheet() {
        try {
            spriteSheet = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(SPRITE_PATH));
        } catch (IOException e) {
            System.out.println("Sprite sheet couldn't load");
        }
    }

    private double findSpriteRotationAngle() {
        int deltaX = (800 / 2) - mouse.getX();
        int deltaY = (600 / 2) - mouse.getY();
        return -Math.atan2(deltaX, deltaY);
    }
}
