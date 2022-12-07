package cegepst.roomClearingGame;

import cegepst.engine.controls.Direction;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.graphics.Buffer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Zombie extends MovableEntity {
    private static final int MOVING_ANIMATION_SPEED = 3;
    private static final int IDLE_ANIMATION_SPEED = 7;

    private BufferedImage[] movingFrames;
    private BufferedImage[] idleFrames;
    private int currentIdleFrame = 0;
    private int nextIdleFrame = IDLE_ANIMATION_SPEED;
    private int currentMovingFrame = 0;
    private int nextMovingFrame = MOVING_ANIMATION_SPEED;
    private int rotation = 90;
    private int viewportSizeX = 800;
    private int viewportSizeY = 600;
    private int worldSizeX = 2816;
    private int worldSizeY = 5120;

    public Zombie() {
        setDimension(128, 128);
        setSpeed(1);
        setDirection(Direction.DOWN);
        loadIdleFrames();
        loadMovingFrames();
    }

    @Override
    public void update() {
        super.update();
        move(Direction.DOWN);
        findZombieRotation();
        if (hasMoved()) {
            cycleMovingFrames();
        } else {
            cycleIdleFrames();
        }
    }

    @Override
    public void draw(Buffer buffer) {
        if (hasMoved()) {
            drawAnimationFrame(buffer, movingFrames[currentMovingFrame], rotation);
        } else {
            drawAnimationFrame(buffer, idleFrames[currentIdleFrame], rotation);
        }
    }

    private void findZombieRotation() {
        if (getDirection() == Direction.UP) {
            rotation = 270;
        } else if (getDirection() == Direction.DOWN) {
            rotation = 90;
        } else if (getDirection() == Direction.LEFT) {
            rotation = 180;
        } else if (getDirection() == Direction.RIGHT) {
            rotation = 0;
        }
    }

    private void cycleMovingFrames() {
        --nextMovingFrame;
        if (nextMovingFrame == 0) {
            ++currentMovingFrame;
            if (currentMovingFrame >= movingFrames.length) {
                currentMovingFrame = 0;
            }
            nextMovingFrame = MOVING_ANIMATION_SPEED;
        }
    }

    private void cycleIdleFrames() {
        --nextIdleFrame;
        if (nextIdleFrame == 0) {
            ++currentIdleFrame;
            if (currentIdleFrame >= idleFrames.length) {
                currentIdleFrame = 0;
            }
            nextIdleFrame = IDLE_ANIMATION_SPEED;
        }
    }

    private void loadMovingFrames() {
        movingFrames = new BufferedImage[16];
        for (int i = 0; i < movingFrames.length; i++) {
            try {
                movingFrames[i] = scaleBufferedImage(ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("images/Zombie/move/skeleton-move_" + i + ".png")), 128, 128);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void loadIdleFrames() {
        idleFrames = new BufferedImage[16];
        try {
            for (int i = 0; i < idleFrames.length; i++) {
                idleFrames[i] = scaleBufferedImage(ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("images/Zombie/idle/skeleton-idle_" + i + ".png")), 128, 128);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private BufferedImage scaleBufferedImage(BufferedImage originalImage, int width, int height) {
        Image image = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = scaledImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return scaledImage;
    }

    private void drawAnimationFrame(Buffer buffer, BufferedImage image, int rotation) {
        buffer.drawImage(buffer.rotateImage(image, rotation), x, y);
        //System.out.println("x :" + x + " y : " + y);
    }
}
