package cegepst.roomClearingGame;

import cegepst.engine.controls.Direction;
import cegepst.engine.entities.CollidableRepository;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.graphics.Buffer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class Zombie extends MovableEntity {
    private static final int MOVING_ANIMATION_SPEED = 3;
    private static final int ATTACK_ANIMATION_SPEED = 5;
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
    private int attackDelay = 0;
    private int calloutCooldown = 0;
    private World world;
    private Player player;
    private int health = 100;
    private boolean attacking;
    private boolean idle;

    public Zombie(World world, Player player) {
        //CollidableRepository.getInstance().registerEntity(this);
        setDimension(128, 128);
        setSpeed(2);
        loadIdleFrames();
        loadMovingFrames();
        loadAttackingFrames();
        this.world = world;
        this.player = player;
    }

    @Override
    public void update() {
        super.update();
        determineDirection();
        findRotation();
        if (attackDelay != 0) {
            attackDelay--;
        }
        if (calloutCooldown != 0) {
            calloutCooldown--;
        }
        if (attacking) {
            cycleAttackingFrames();
        } else if (hasMoved()) {
            cycleMovingFrames();
        } else {
            cycleIdleFrames();
        }
    }

    @Override
    public void draw(Buffer buffer) {
            if (attacking) {
                drawAnimationFrame(buffer, attackingFrames[currentAttackFrame], rotation);
            } else if (hasMoved()) {
                drawAnimationFrame(buffer, movingFrames[currentMovingFrame], rotation);
            } else {
                drawAnimationFrame(buffer, idleFrames[currentIdleFrame], rotation);
            }
    }

    public void getDamaged(int damage) {
        if (calloutCooldown == 0) {
            playRandomHurtSound();
        }
        health -= damage;
    }

    public void tryDealingDamage(Player player) {
        if (attackDelay == 0) {
            if (x <= player.getX() && x + width >= player.getX() && y <= player.getY() && y + height >= player.getY()) {
                attacking = true;
                player.getDamaged(10);
                System.out.println("DAMAGING PLAYER");
                attackDelay = 50;
            }
        }
    }

    public boolean checkIfDead() {
        return health <= 0;
    }

    public void determineDirection() {
        if (x + width < player.getX()) {
            setDirection(Direction.RIGHT);
            setMoved(true);
            move();
        } else if (x > player.getX()) {
            setDirection(Direction.LEFT);
            setMoved(true);
            move();
        } else if (y + height < player.getY()) {
            setDirection(Direction.DOWN);
            setMoved(true);
            move();
        } else if (y > player.getY()) {
            setDirection(Direction.UP);
            setMoved(true);
            move();
        } else {
            tryDealingDamage(player);
        }
    }

    public void findRotation() {
        rotation = (int) findSpriteRotationAngle() + 30;
    }

    private void playRandomHurtSound() {
        calloutCooldown = 50;
        int number = ThreadLocalRandom.current().nextInt(1, 7 + 1);
        switch (number) {
            case 1:
                Sound.ZOMBIE_GRUNT_1.play();
                break;
            case 2:
                Sound.ZOMBIE_GRUNT_2.play();
                break;
            case 3:
                Sound.ZOMBIE_GRUNT_3.play();
                break;
            case 4:
                Sound.ZOMBIE_GRUNT_4.play();
                break;
            case 5:
                Sound.ZOMBIE_GRUNT_5.play();
                break;
            case 6:
                Sound.ZOMBIE_YELL_3.play();
                break;
            case 7:
                Sound.ZOMBIE_YELL_4.play();
                break;
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
                currentAttackFrame = 0;
                attacking = false;
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
                idle = false;
            }
            nextIdleFrame = IDLE_ANIMATION_SPEED;
        }
    }

    private void loadMovingFrames() {
        movingFrames = new BufferedImage[16];
        for (int i = 0; i < movingFrames.length; i++) {
            try {
                movingFrames[i] = scaleBufferedImage(ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("images/Zombie/move/skeleton-move_" + i + ".png")), 168, 168);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void loadAttackingFrames() {
        attackingFrames = new BufferedImage[8];
        for (int i = 0; i < attackingFrames.length; i++) {
            try {
                attackingFrames[i] = scaleBufferedImage(ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("images/Zombie/attack/skeleton-attack_" + i + ".png")), 168, 168);
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

    private double findSpriteRotationAngle() {
        int deltaX = x + width / 2 - player.getX();
        int deltaY = y + height / 2 - player.getY();
        return -Math.atan2(deltaX, deltaY);
    }
}
