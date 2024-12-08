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

    public void drawString(String text, int x, int y, Paint paint) {
        graphics.setPaint(paint);
        graphics.drawString(text, x, y);
    }

    public void drawEndingScreen(String title, Mouse mouse) {
        int width = RenderingEngine.getInstance().getPanel().getWidth();
        int height = RenderingEngine.getInstance().getPanel().getHeight();
        int retryMinHeight = 260;
        int retryMaxHeight = 315;
        int quitMinHeight = 365;
        int quitMaxHeight = 410;
        this.drawRectangle(0, 0, width, height, new Color(0, 0, 0, 150));
        this.setFontSize(Font.BOLD, 110f);
        String text = title;
        this.drawString(text, width / 12, height / 4, Color.black);
        this.drawString(text, width / 12 - 4, height / 4 - 4, Color.WHITE);
        this.setFontSize(50f);
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

    public void drawImage(Image image, int x, int y) {
        graphics.drawImage(image, x, y, null);
    }

    public void setFontSize(float fontSize) {
        graphics.setFont(graphics.getFont().deriveFont(fontSize));
    }

    public void setFontSize(int type, float fontSize) {
        graphics.setFont(graphics.getFont().deriveFont(type, fontSize));
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
