package cegepst.engine.graphics;

import cegepst.roomClearingGame.Mouse;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Buffer {

    private final Graphics2D graphics;

    public Buffer(Graphics2D graphics) {
        this.graphics = graphics;
    }

    public void drawRectangle(int x, int y, int width, int height, Paint paint) {
        graphics.setPaint(paint);
        graphics.fillRect(x, y, width, height);
    }

    public void drawCircle(int x, int y, int radius, Paint paint) {
        graphics.setPaint(paint);
        graphics.fillOval(x, y, radius * 2, radius * 2);
    }

    public void drawString(String text, int x, int y, Paint paint) {
        graphics.setPaint(paint);
        graphics.drawString(text, x, y);
    }

    public BufferedImage scaleBufferedImage(BufferedImage originalImage, int width, int height) {
        Image image = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = scaledImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return scaledImage;
    }

    public void drawEndingScreen(String title, Mouse mouse) {
        int width = RenderingEngine.getInstance().getPanel().getWidth();
        int height = RenderingEngine.getInstance().getPanel().getHeight();
        int retryMinHeight = 260;
        int retryMaxHeight = 315;
        int quitMinHeight = 365;
        int quitMaxHeight = 410;
        this.drawRectangle(0, 0, width, height, new Color(0, 0, 0, 150));
        graphics.setFont(graphics.getFont().deriveFont(Font.BOLD, 110f));
        String text = title;
        this.drawString(text, width / 10, height / 4, Color.black);
        this.drawString(text, width / 10 - 4, height / 4 - 4, Color.WHITE);
        graphics.setFont(graphics.getFont().deriveFont(50f));

        text = "Restart";
        Color textColor;
        if (mouse.getY() <= retryMaxHeight && mouse.getY() >= retryMinHeight) {
            if (title.equalsIgnoreCase("YOU WON")) {
                textColor = Color.GREEN;
            } else {
                textColor = Color.RED;
            }
        } else {
            textColor = Color.WHITE;
        }
        this.drawString(text, width / 10, height / 2, textColor);
        text = "Quit to desktop";
        if (mouse.getY() <= quitMaxHeight && mouse.getY() >= quitMinHeight) {
            if (title.equalsIgnoreCase("YOU WON")) {
                textColor = Color.GREEN;
            } else {
                textColor = Color.RED;
            }
        } else {
            textColor = Color.WHITE;
        }
        this.drawString(text, width / 10, height - 200, textColor);
    }

    public void drawLoadingScreen() {
        int width = RenderingEngine.getInstance().getPanel().getWidth();
        int height = RenderingEngine.getInstance().getPanel().getHeight();
        this.drawRectangle(0, 0, width, height, new Color(0, 0, 0, 150));
        graphics.setFont(graphics.getFont().deriveFont(Font.BOLD, 110f));
        String text = "LOADING.";
        this.drawString(text, width / 10, height / 8, Color.black);
        this.drawString(text, width / 10 - 4, height / 8 - 4, Color.WHITE);
        text = "LOADING..";
        this.drawString(text, width / 10, height / 8, Color.black);
        this.drawString(text, width / 10 - 4, height / 8 - 4, Color.WHITE);
        text = "LOADING...";
        this.drawString(text, width / 10, height / 8, Color.black);
        this.drawString(text, width / 10 - 4, height / 8 - 4, Color.WHITE);
    }

    public void drawImage(Image image, int x, int y) {
        graphics.drawImage(image, x, y, null);
    }

    public Graphics2D getGraphics() {
        return this.graphics;
    }

    public void translate(int x, int y) {
        graphics.translate(x, y);
    }

    public BufferedImage rotateImage(BufferedImage image, double angle) {
        BufferedImage rotated = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        Graphics2D graphic = rotated.createGraphics();
        graphic.rotate(angle, image.getWidth() / 2, image.getHeight() / 2);
        graphic.drawImage(image, null, 0, 0);
        graphic.dispose();
        return rotated;
    }
}
