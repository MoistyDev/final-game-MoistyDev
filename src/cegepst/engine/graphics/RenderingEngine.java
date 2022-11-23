package cegepst.engine.graphics;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class RenderingEngine {

    private static RenderingEngine instance;
    private Screen screen;
    private JPanel panel;
    private BufferedImage bufferedImage;

    public static RenderingEngine getInstance() {
        if (instance == null) {
            instance = new RenderingEngine();
        }
        return instance;
    }

    public Screen getScreen() {
        return screen;
    }

    public void start() {
        screen.start();
    }

    public void stop() {
        screen.end();
    }

    public Buffer buildBuffer() {
        bufferedImage = new BufferedImage(800, 600,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setRenderingHints(buildRenderingHints());
        return new Buffer(graphics);
    }

    public void drawBufferOnScreen() {
        Graphics2D graphics = (Graphics2D) panel.getGraphics();
        graphics.drawImage(screen.scaleImage(bufferedImage), 0, 0, panel);
        Toolkit.getDefaultToolkit().sync();
        graphics.dispose();
    }

    public void addKeyListener(KeyListener listener) {
        panel.addKeyListener(listener);
    }

    public void addMouseMovementListener(MouseMotionListener listener) {
        panel.addMouseMotionListener(listener);
    }

    public void addMouseClickListener(MouseInputListener listener) {
        panel.addMouseListener(listener);
    }

    private RenderingEngine() {
        initializeFrame();
        initializePanel();
    }

    private void initializePanel() {
        panel = new JPanel();
        panel.setBackground(Color.blue);
        panel.setFocusable(true);
        screen.setPanel(panel);
    }

    private void initializeFrame() {
        screen = new Screen();
        screen.setTitle("Test game");
    }

    private RenderingHints buildRenderingHints() {
        RenderingHints hints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        hints.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        return hints;
    }
}
