package cegepst.engine.graphics;

public class Camera {
    int worldSizeX = 0;
    int worldSizeY = 0;
    int viewportSizeX = 0;
    int viewportSizeY = 0;
    int offsetMaxX;
    int offsetX;
    int offsetMaxY;
    int offsetY;
    int cameraX;
    int cameraY;

    public Camera(int worldSizeX, int worldSizeY, int viewportSizeX, int viewportSizeY, int cameraX, int cameraY) {
        this.worldSizeX = worldSizeX;
        this.worldSizeY = worldSizeY;
        this.viewportSizeX = viewportSizeX;
        this.viewportSizeY = viewportSizeY;
        this.cameraX = cameraX;
        this.cameraY = cameraY;
        offsetX = this.cameraX;
        offsetY = this.cameraY;
        offsetMaxX = worldSizeX - viewportSizeY;
        offsetMaxY = worldSizeY - viewportSizeY;
    }

    public void updateCameraPosition(int playerX, int playerY) {
        cameraX = playerX - ((viewportSizeX - worldSizeX) / 2);
        cameraY = playerY - ((viewportSizeY - worldSizeY) / 2);

        if (cameraX > offsetMaxX) {
            cameraX = offsetMaxX;
        } else if (cameraX < 0) {
            cameraX = 0;
        }
        if (cameraY > offsetMaxY) {
            cameraY = offsetMaxY;

        } else if (cameraY < 0) {
            cameraY = 0;
        }
    }

    public int getCameraX() {
        return cameraX;
    }

    public int getCameraY() {
        return cameraY;
    }
}
