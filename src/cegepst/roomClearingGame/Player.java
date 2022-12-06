package cegepst.roomClearingGame;

import cegepst.engine.graphics.Buffer;
import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.controls.MovementController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends ControllableEntity {
    private static final int MOVING_ANIMATION_SPEED = 3;
    private static final int IDLE_ANIMATION_SPEED = 7;
    private static final int SHOOTING_ANIMATION_SPEED = 3;

    private Mouse mouse;
    private MovementController controller;
    private int viewportX = 800 / 2;
    private int viewportY = 600 / 2;
    private boolean isShooting = false;
    private BufferedImage[] idleFrames;
    private BufferedImage[] shootingFrames;
    private BufferedImage[] movingFrames;
    private int currentIdleFrame = 0;
    private int nextIdleFrame = IDLE_ANIMATION_SPEED;
    private int currentMovingFrame = 0;
    private int nextMovingFrame = MOVING_ANIMATION_SPEED;
    private int currentShootingFrame = 0;
    private int nextShootingFrame = SHOOTING_ANIMATION_SPEED;

    public Player(MovementController controller, Mouse mouse) {
        super(controller);
        this.controller = controller;
        this.mouse = mouse;
        setDimension(64, 64);
        setSpeed(4);
    }

    public void load() {
        loadAnimationFrames();
    }

    @Override
    public void draw(Buffer buffer) {
       if (!hasMoved()) {
           if (isShooting) {
               buffer.drawImage(buffer.rotateImage(shootingFrames[currentShootingFrame], findSpriteRotationAngle()), viewportX - width, viewportY - height);
           } else if (!isShooting) {
               buffer.drawImage(buffer.rotateImage(idleFrames[currentIdleFrame], findSpriteRotationAngle()), viewportX - width, viewportY - height);
           }
       } else if (hasMoved()) {
           if (isShooting) {
               buffer.drawImage(buffer.rotateImage(shootingFrames[currentShootingFrame], findSpriteRotationAngle()), viewportX - width, viewportY - height);
           } else if (!isShooting) {
               buffer.drawImage(buffer.rotateImage(movingFrames[currentMovingFrame], findSpriteRotationAngle()), viewportX - width, viewportY - height);
           }
       }
    }

    @Override
    public void update() {
        super.update();
        moveWithController();
        if (hasMoved()) {
            cycleMovingFrames();
            if (isShooting) {
                cycleShootingFrames();
            }
        } else {
            cycleIdleFrames();
            if (isShooting) {
                cycleShootingFrames();
            }
        }
    }

    public void setIsShooting(boolean value) {
        isShooting = value;
    }



    private void cycleShootingFrames() {
        --nextShootingFrame;
        if (nextShootingFrame == 0) {
            ++currentShootingFrame;
            if (currentShootingFrame >= shootingFrames.length) {
                currentShootingFrame = 0;
                isShooting = false;
            }
            nextShootingFrame = SHOOTING_ANIMATION_SPEED;
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

    private void loadAnimationFrames() {
        loadIdleFrames();
        loadShootingFrames();
        loadMovingFrames();
    }

    private void loadMovingFrames() {
        movingFrames = new BufferedImage[19];
        for (int i = 0; i < movingFrames.length; i++) {
            try {
                movingFrames[i] = scaleBufferedImage(ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("images/PMC/handgun/move/survivor-move_handgun_" + i + ".png")), 80, 128, 128);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void loadShootingFrames() {
        shootingFrames = new BufferedImage[3];
        try {
            for (int i = 0; i < shootingFrames.length; i++) {
                shootingFrames[i] = scaleBufferedImage(ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("images/PMC/handgun/shoot/survivor-shoot_handgun_" + i + ".png")), 80, 128, 128);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadIdleFrames() {
        idleFrames = new BufferedImage[19];
        try {
            for (int i = 0; i < idleFrames.length; i++) {
                idleFrames[i] = scaleBufferedImage(ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("images/PMC/handgun/idle/survivor-idle_handgun_" + i + ".png")), 80, 128, 128);
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
    private BufferedImage scaleBufferedImage(BufferedImage originalImage, int rotation, int width, int height) {
        Image image = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = scaledImage.createGraphics();
        g.rotate(rotation, scaledImage.getWidth() / 2, scaledImage.getHeight() / 2);
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return scaledImage;
    }


    private double findSpriteRotationAngle() {
        int deltaX = (viewportX) - mouse.getX();
        int deltaY = (viewportY) - mouse.getY();
        return -Math.atan2(deltaX, deltaY);
    }
}
