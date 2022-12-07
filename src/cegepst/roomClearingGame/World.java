package cegepst.roomClearingGame;

import cegepst.engine.graphics.Buffer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class World {
    public static int worldSizeX;
    public static int worldSizeY;
    private final String MAP_PATH = "images/Gas Station.png";
    private BufferedImage background;
    private BufferedImage bounds;
    private ArrayList<Boundary> boundaries;

    public World() {
        load();
        worldSizeX = background.getWidth();
        worldSizeY = background.getHeight();
        boundaries = new ArrayList<>();
        setMapWalls();
        setBuildingWalls();
        setInnerBuildingWalls();
    }

    public void draw(Buffer buffer, int cameraX, int cameraY) {
        buffer.drawImage(background, cameraX, cameraY);
    }

    public int getWidth() {
        return background.getWidth();
    }

    public int getHeight() {
        return background.getHeight();
    }

    private void load() {
        try {
            background = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(MAP_PATH));
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void setInnerBuildingWalls() {

    }

    private void setBuildingWalls() {

    }

    private void setMapWalls() {
        //top
        setBoundary(-400, -300, background.getWidth(), 20);
        //bottom
        setBoundary(-400, 4820, background.getWidth(), 10);
        //left
        setBoundary(-400, -300, 20, background.getHeight());
        //right
        setBoundary(2420, -300, 20, background.getHeight());
    }

    private void setBoundary(int teleportX, int teleportY, int dimensionX, int dimensionY) {
        Boundary boundary = new Boundary();
        boundary.teleport(teleportX, teleportY);
        boundary.setDimension(dimensionX, dimensionY);
        boundaries.add(boundary);
    }
}
