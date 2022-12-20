package cegepst.roomClearingGame;

import cegepst.engine.graphics.Buffer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class World {
    public final int worldSizeX;
    public final int worldSizeY;
    private final String MAP_PATH = "images/Supermarket.png";
    private BufferedImage background;
    private ArrayList<Boundary> boundaries;
    public int maxColSize;
    public int maxRowSize;

    public World() {
        load();
        worldSizeX = background.getWidth();
        worldSizeY = background.getHeight();
        maxColSize = background.getWidth() / 8;
        maxRowSize = background.getHeight() / 8;
        boundaries = new ArrayList<>();
        setMapWalls();
        setBuildingWalls();
        setInnerBuildingWalls();
        setShippingCrates();
        setPalmTrees();
        setBathroomFurniture();
        setBistroFurniture();
        setShoppingArea();
    }

    public void draw(Buffer buffer, int cameraX, int cameraY) {
        buffer.drawImage(background, cameraX, cameraY);
    }

    public ArrayList<Boundary> getBoundaries() {
        return boundaries;
    }

    public int getWidth() {
        return background.getWidth();
    }

    public int getHeight() {
        return background.getHeight();
    }

    public int getRandomCoordX() {
        return ThreadLocalRandom.current().nextInt(200, (worldSizeX - 400) + 1);
    }

    public int getRandomCoordY() {
        return ThreadLocalRandom.current().nextInt(200, (worldSizeY - 400) + 1);
    }


    private void load() {
        try {
            background = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(MAP_PATH));
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void setShoppingArea() {
        setBoundary(876, 2457, 80, 558);
        setBoundary(1168, 2457, 86, 555);
        setBoundary(1440, 2760, 206, 85);
        setBoundary(1440, 2450, 206, 85);
        setBoundary(1902, 2252, 29, 188);
        setBoundary(1892, 2440, 90, 204);
        setBoundary(1892, 2824, 90, 204);
        setBoundary(1902, 3028, 29, 187);
        setBoundary(1446, 3316, 146, 104);
        setBoundary(1582, 3316, 146, 104);
        setBoundary(1582, 3316, 146, 104);
        setBoundary(1298, 2072, 90, 138);
        setBoundary(1298, 2072, 187, 64);
    }

    private void setShippingCrates() {
        setBoundary(-348, 326, 422, 197);
        setBoundary(-344, 523, 211, 197);
        setBoundary(416, 528, 211, 197);
        setBoundary(416, 725, 211, 197);
        setBoundary(-348, 1321, 633, 197);
        setBoundary(-131, 1518, 211, 197);
        setBoundary(185, 1923, 211, 197);
        setBoundary(-171, 2287, 211, 197);
    }

    private void setPalmTrees() {
        setBoundary(28, 2804, 152, 148);
        setBoundary(512, 3244, 152, 148);
        setBoundary(164, 4304, 152, 148);
        setBoundary(436, 4468, 152, 148);
        setBoundary(32, 4644, 152, 148);
        setBoundary(2160, 4508, 152, 148);
        setBoundary(2176, 3556, 152, 148);
        setBoundary(1588, -32, 152, 148);
        setBoundary(2166, -202, 152, 148);
        setBoundary(-72, -120, 152, 148);
    }

    private void setBistroFurniture() {
        setBoundary(673, 998, 533, 34);
        setBoundary(673, 1360, 620, 81);
        setBoundary(782, 492, 80, 65);
        setBoundary(950, 525, 80, 65);
        setBoundary(862, 486, 102, 274);
        setBoundary(1716, 580, 80, 65);
        setBoundary(1796, 508, 135, 172);
        setBoundary(1796, 888, 145, 172);
        setBoundary(1796, 888, 145, 172);
        setBoundary(1724, 976, 80, 65);
        setBoundary(1931, 904, 80, 65);
        setBoundary(2155, 326, 87, 912);
        setBoundary(1727, 1276, 110, 110);
        setBoundary(1727, 1548, 110, 110);
        setBoundary(2159, 1958, 110, 270);
        setBoundary(2034, 2132, 206, 120);
        setBoundary(2166, 1820, 141, 76);
        setBoundary(1959, 1358, 189, 115);
    }

    private void setBathroomFurniture() {
        setBoundary(673, 1560, 155, 16);
        setBoundary(673, 1708, 155, 16);
        setBoundary(673, 1756, 72, 234);
        setBoundary(673, 2000, 72, 234);
        setBoundary(1113, 1885, 85, 105);
        setBoundary(1189, 1758, 32, 32);
        setBoundary(1190, 2008, 75, 80);
        setBoundary(1126, 2180, 32, 32);
    }

    private void setInnerBuildingWalls() {
        //lower restroom
        setBoundary(656, 2244, 195, 8);
        setBoundary(1040, 2244, 255, 8);
        //upper restroom
        setBoundary(656,1440, 640, 8);
        //right restroom
        setBoundary(1290, 1240, 8, 290);
        setBoundary(1290, 1710, 8, 540);
        //top stall
        setBoundary(1110, 1750, 180, 8);
        //middle stall
        setBoundary(1110, 2000, 180, 8);
        //left walls
        setBoundary(1110, 1750, 8, 50);
        setBoundary(1110, 1985, 8, 45);
        setBoundary(1110, 2205, 8, 30);

        //top freezer
        setBoundary(1865, 1228, 416, 8);
        //left freezer
        setBoundary(1865, 1228, 8, 756);
        setBoundary(1865, 2150, 8, 100);
        //bottom freezer
        setBoundary(1865, 2244, 365, 8);
        //middle wall
        setBoundary(1290,1762, 236, 8);
        setBoundary(1665,1762, 236, 8);
    }

    private void setBuildingWalls() {
        //top red walls
        setBoundary(636, 310, 736, 16);
        setBoundary(1585, 310, 736, 16);
        //left red walls
        setBoundary(657, 310, 16, 1250);
        setBoundary(657, 1710, 16, 1520);
        //right red wall
        setBoundary(2270, 310, 16, 2912);
        //bottom left walls
        setBoundary(657, 3220, 300, 16);
        setBoundary(945, 3220, 16, 176);
        setBoundary(945, 3380, 260, 16);
        //bottom right walls
        setBoundary(1744, 3220, 540, 16);
        setBoundary(1744, 3220, 16, 176);
        setBoundary(1470, 3380, 290, 16);
        //top shipping wall
        setBoundary(-380, 310, 1016, 16);
        //left shipping wall
        setBoundary(-380, 310, 32, 4304);
        //bottom empty building
        setBoundary(-380, 4590, 376, 32);
        //right empty building
        setBoundary(-40, 2720, 32, 1920);
        //top empty building
        setBoundary(-380, 2730, 340, 32);
        //bottom shipping walls
        setBoundary(-40, 2720, 270, 8);
        setBoundary(455, 2720, 200, 8);
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
