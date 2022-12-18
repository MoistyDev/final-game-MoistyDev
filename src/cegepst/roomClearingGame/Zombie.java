package cegepst.roomClearingGame;

import cegepst.engine.controls.Direction;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.graphics.Camera;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Zombie extends MovableEntity {
    private static final int MOVING_ANIMATION_SPEED = 3;
    private static final int ATTACK_ANIMATION_SPEED = 3;
    private static final int IDLE_ANIMATION_SPEED = 7;

    private BufferedImage[] movingFrames;
    private BufferedImage[] attackingFrames;
    private BufferedImage[] idleFrames;
    private int currentIdleFrame = 0;
    private int nextIdleFrame = IDLE_ANIMATION_SPEED;
    private int currentMovingFrame = 0;
    private int nextMovingFrame = MOVING_ANIMATION_SPEED;
    private int currentAttackFrame = 0;
    private int nextAttackFrame = ATTACK_ANIMATION_SPEED;
    private int rotation = 90;
    private World world;
    private Player player;
    private int health = 100;
    private boolean attacking;

    public Zombie(World world, Player player) {
        setDimension(128, 128);
        setSpeed(1);
        loadIdleFrames();
        loadMovingFrames();
        loadAttackingFrames();
        this.world = world;
        this.player = player;
    }

    @Override
    public void update() {
        super.update();
        if (hasMoved()) {
            cycleMovingFrames();
        } else if (attacking) {
            cycleAttackingFrames();
            attacking = false;
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

    public void tryDealingDamage(Player player) {
        if ((x + width / 2 == player.getX() + 800 / 2) && (y + height / 2 == player.getY() + 600 / 2)) {
            attacking = true;
            player.getDamaged(1);
            System.out.println("damaged");
        }
    }

    public void determineDirection() {
        if (x + width / 2 < player.getX() + 800 / 2) {
            setDirection(Direction.RIGHT);
        } else
        if (x + width / 2 > player.getX() + 800 / 2) {
            setDirection(Direction.LEFT);
        } else
        if (y + height / 2 < player.getY() + 600 / 2) {
            setDirection(Direction.DOWN);
        } else
        if (y + height / 2 > player.getY() + 600 / 2) {
            setDirection(Direction.UP);
        } else {
            setDirection(Direction.NONE);
        }
    }

    public void findZombieRotation() {
        if (getDirection() == Direction.UP) {
            rotation = 270;
        } else if (getDirection() == Direction.DOWN) {
            rotation = 90;
        } else if (getDirection() == Direction.LEFT) {
            rotation = 160;
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

    private void cycleAttackingFrames() {
        --nextAttackFrame;
        if (nextAttackFrame == 0) {
            ++currentAttackFrame;
            if (currentAttackFrame >= attackingFrames.length) {
                currentIdleFrame = 0;
            }
            nextAttackFrame = ATTACK_ANIMATION_SPEED;
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

    private void loadAttackingFrames() {
        attackingFrames = new BufferedImage[8];
        for (int i = 0; i < attackingFrames.length; i++) {
            try {
                attackingFrames[i] = scaleBufferedImage(ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("images/Zombie/attack/skeleton-attack_" + i + ".png")), 128, 128);
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

    private void drawAnimationFrame(Buffer buffer, BufferedImage image, double rotation) {
        buffer.drawImage(buffer.rotateImage(image, rotation), x, y);
        //System.out.println("x :" + x + " y : " + y);
    }
}
