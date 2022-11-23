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

    public void drawBounds(Buffer buffer) {
        for (Boundary boundary : boundaries) {
            boundary.draw(buffer);
        }
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
        setBoundary(-370, 20, 200, 1);
        setBoundary(50, 15, 220, 1);
        setBoundary(300, -520, 1, 530);
        setBoundary(-320, -790, 610, 1);
        setBoundary(300, -970, 1, 260);
        setBoundary(120, -480, 160, 1);
        setBoundary(120, -480, 1, 50);
        setBoundary(120, -250, 1, 30);
        setBoundary(120, -30, 1, 20);
        setBoundary(300, -470, 190, 1);
        setBoundary(680, -470, 190, 1);
        setBoundary(870, -470, 1, 212);
        setBoundary(870, -70, 1, 80);
        setBoundary(870, 20, 500, 1);
        setBoundary(870, -980, 1, 550);
        setBoundary(870, -1000, 500, 1);
    }

    private void setBuildingWalls() {
        setBoundary(-325, -1915, 680, 2);
        setBoundary(610, -1915, 624, 2);
        setBoundary(-330, -1915, 2, 1220);
        setBoundary(-330, -500, 2, 1490);
        setBoundary(-330, 990, 260, 2);
        setBoundary(-40, 1000, 2, 130);
        setBoundary(-40, 1140, 220, 2);
        setBoundary(500, 1140, 240, 2);
        setBoundary(738, 1000, 2, 140);
        setBoundary(738, 1000, 550, 2);
    }

    private void setMapWalls() {
        //top
        setBoundary(-getWidth() / 2, -2380, getWidth(), 48);
        //bottom
        setBoundary(-getWidth() / 2, 2380, getWidth(), 48);
        //left
        setBoundary(-1100, -getHeight() / 2, 48, getHeight());
        //right
        setBoundary(1300,  -getHeight() / 2, 48, getHeight());
    }

    private void setBoundary(int teleportX, int teleportY, int dimensionX, int dimensionY) {
        Boundary boundary = new Boundary();
        boundary.teleport(teleportX, teleportY);
        boundary.setDimension(dimensionX, dimensionY);
        boundaries.add(boundary);
    }
}
