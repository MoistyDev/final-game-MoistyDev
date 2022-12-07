package cegepst.engine.graphics;

import java.awt.*;
import java.awt.geom.AffineTransform;
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
