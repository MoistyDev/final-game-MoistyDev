package cegepst.roomClearingGame;

import cegepst.engine.graphics.Buffer;
import cegepst.engine.graphics.RenderingEngine;

import javax.imageio.ImageIO;
import javax.swing.event.MouseInputListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class Mouse implements MouseMotionListener, MouseInputListener {

    private HashMap<Integer, Boolean> clickedInputs;
    private static final String SPRITE_PATH = "images/Crosshair.png";
    private BufferedImage spriteSheet;
    private int x;
    private int y;
    private boolean hasMoved;

    public Mouse() {
        clickedInputs = new HashMap<>();
        RenderingEngine.getInstance().addMouseMovementListener(this);
        RenderingEngine.getInstance().addMouseClickListener(this);
        bindClick(MouseEvent.MOUSE_CLICKED);
    }


    public void load() {
        loadSpriteSheet();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isFireClicked() {
        return clickedInputs.containsKey(MouseEvent.MOUSE_CLICKED) && clickedInputs.get(MouseEvent.MOUSE_CLICKED);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int inputCode = e.getButton();
        if (clickedInputs.containsKey(inputCode)) {
            clickedInputs.put(inputCode, true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int inputCode = e.getButton();
        if (clickedInputs.containsKey(inputCode)) {
            clickedInputs.put(inputCode, false);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
       hasMoved = true;
       x = e.getX();
       y = e.getY();
    }

    public void drawCursor(Buffer buffer) {
        buffer.drawImage(spriteSheet, (x - 16), y - 16);
    }

    private void bindClick(int inputCode) {
        clickedInputs.put(inputCode, false);
    }

    private void loadSpriteSheet() {
        try {
            spriteSheet = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(SPRITE_PATH));
        } catch (IOException e) {
            System.out.println("Sprite sheet couldn't load");
        }
    }
}
