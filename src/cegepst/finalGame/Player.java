package cegepst.finalGame;

import cegepst.engine.graphics.Buffer;
import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.controls.Direction;
import cegepst.engine.controls.MovementController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends ControllableEntity {

    private static final String SPRITE_PATH = "images/player.png";
    private static final int ANIMATION_SPEED = 8;

    private BufferedImage spriteSheet;
    private Image[] rightFrames;
    private Image[] leftFrames;
    private Image[] upFrames;
    private Image[] downFrames;
    private int currentAnimationFrame = 1; // idle frame (middle)
    private int nextFrame = ANIMATION_SPEED;

    public Player(MovementController controller) {
        super(controller);
        setDimension(32, 32);
        setSpeed(3);
    }

    public void load() {
        loadSpriteSheet();
        loadAnimationFrames();
    }

    @Override
    public void draw(Buffer buffer) {
        if (getDirection() == Direction.RIGHT) {
            buffer.drawImage(rightFrames[currentAnimationFrame], x, y);
        } else if (getDirection() == Direction.LEFT) {
            buffer.drawImage(leftFrames[currentAnimationFrame], x, y);
        } else if (getDirection() == Direction.UP) {
            buffer.drawImage(upFrames[currentAnimationFrame], x, y);
        }  else if (getDirection() == Direction.DOWN) {
            buffer.drawImage(downFrames[currentAnimationFrame], x, y);
        }
    }

    @Override
    public void update() {
        super.update();
        moveWithController();
        if (hasMoved()) {
            --nextFrame;
            if (nextFrame == 0) {
                ++currentAnimationFrame;
                if (currentAnimationFrame >= leftFrames.length) {
                    currentAnimationFrame = 0;
                }
                nextFrame = ANIMATION_SPEED;
            }
        } else {
            currentAnimationFrame = 1; // Idle frame (middle)
        }
    }

    private void loadSpriteSheet() {
        try {
            spriteSheet = ImageIO.read(this.getClass().getClassLoader()
                    .getResourceAsStream(SPRITE_PATH));
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void loadAnimationFrames() {
        downFrames = new Image[3];
        downFrames[0] = spriteSheet.getSubimage(0, 128, width, height);
        downFrames[1] = spriteSheet.getSubimage(32, 128, width, height);
        downFrames[2] = spriteSheet.getSubimage(64, 128, width, height);

        leftFrames = new Image[3];
        leftFrames[0] = spriteSheet.getSubimage(0, 160, width, height);
        leftFrames[1] = spriteSheet.getSubimage(32, 160, width, height);
        leftFrames[2] = spriteSheet.getSubimage(64, 160, width, height);

        rightFrames = new Image[3];
        rightFrames[0] = spriteSheet.getSubimage(0, 192, width, height);
        rightFrames[1] = spriteSheet.getSubimage(32, 192, width, height);
        rightFrames[2] = spriteSheet.getSubimage(64, 192, width, height);

        upFrames = new Image[3];
        upFrames[0] = spriteSheet.getSubimage(0, 224, width, height);
        upFrames[1] = spriteSheet.getSubimage(32, 224, width, height);
        upFrames[2] = spriteSheet.getSubimage(64, 224, width, height);
    }
}
