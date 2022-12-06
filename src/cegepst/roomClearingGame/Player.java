package cegepst.roomClearingGame;

import cegepst.engine.graphics.Buffer;
import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.controls.MovementController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends ControllableEntity {
    private static final String SPRITE_PATH = "images/Officer128.png";

    private BufferedImage spriteSheet;
    private Mouse mouse;
    private int viewportX = 800 / 2;
    private int viewportY = 600 / 2;
    private int cooldown = 0;

    public Player(MovementController controller, Mouse mouse) {
        super(controller);
        this.mouse = mouse;
        setDimension(128, 128);
        setSpeed(10);
    }

    public Bullet fire() {
        cooldown = 50;
        return new Bullet(this);
    }

    public boolean canFire() {
        return cooldown == 0;
    }

    public void load() {
        loadSpriteSheet();
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(buffer.rotateImage(spriteSheet, findSpriteRotationAngle()), viewportX - 52, viewportY - 69);
        buffer.drawRectangle((800 / 2), (600 / 2), 16, 16, new Color(255, 0, 0, 100));
    }

    @Override
    public void update() {
        super.update();
        moveWithController();
        cycleCooldown();
    }

    private void cycleCooldown() {
        cooldown--;
        if (cooldown < 0) {
            cooldown = 0;
        }
    }

    private void loadSpriteSheet() {
        try {
            spriteSheet = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(SPRITE_PATH));
        } catch (IOException e) {
            System.out.println("Sprite sheet couldn't load");
        }
    }

    private double findSpriteRotationAngle() {
        int deltaX = (viewportX) - mouse.getX();
        int deltaY = (viewportY) - mouse.getY();
        return -Math.atan2(deltaX, deltaY);
    }
}
