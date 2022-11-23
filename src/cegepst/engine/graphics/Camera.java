package cegepst.engine.graphics;

public class Camera {
    private int worldSizeX = 0;
    private int worldSizeY = 0;
    private int viewportSizeX = 0;
    private int viewportSizeY = 0;
    private int cameraX;
    private int cameraY;

    public Camera(int worldSizeX, int worldSizeY, int viewportSizeX, int viewportSizeY, int cameraX, int cameraY) {
        this.worldSizeX = worldSizeX;
        this.worldSizeY = worldSizeY;
        this.viewportSizeX = viewportSizeX;
        this.viewportSizeY = viewportSizeY;
        this.cameraX = cameraX;
        this.cameraY = cameraY;
        //offsetX = this.cameraX;
        //offsetY = this.cameraY;
        //offsetMaxX = worldSizeX - viewportSizeY;
        //offsetMaxY = worldSizeY - viewportSizeY;
    }

    public void updateCameraPosition(int playerX, int playerY) {
        cameraX = playerX - ((viewportSizeX - worldSizeX) / 2);
        cameraY = playerY - ((viewportSizeY - worldSizeY) / 2);

        /*if (cameraX > offsetMaxX) {
            cameraX = offsetMaxX;
        } else if (cameraX < 0) {
            cameraX = 0;
        }
        if (cameraY > offsetMaxY) {
            cameraY = offsetMaxY;

        } else if (cameraY < 0) {
            cameraY = 0;
        }*/
    }

    public int getViewportSizeX() {
        return viewportSizeX;
    }

    public int getViewportSizeY() {
        return viewportSizeY;
    }

    public int getWorldSizeX() {
        return worldSizeX;
    }

    public int getWorldSizeY() {
        return worldSizeY;
    }

    public int getCameraX() {
        return cameraX;
    }

    public int getCameraY() {
        return cameraY;
    }
}
