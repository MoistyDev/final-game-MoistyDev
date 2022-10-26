package cegepst.engine.graphics;

public class Camera {
    int worldSizeX = 0;
    int worldSizeY = 0;
    int viewportSizeX = 0;
    int viewportSizeY = 0;
    int offsetMaxX;
    int offsetMaxY;
    int cameraX;
    int cameraY;
    int speed;

    public Camera(int worldSizeX, int worldSizeY, int viewportSizeX, int viewportSizeY, int cameraX, int cameraY, int speed) {
        this.worldSizeX = worldSizeX;
        this.worldSizeY = worldSizeY;
        this.viewportSizeX = viewportSizeX;
        this.viewportSizeY = viewportSizeY;
        this.cameraX = cameraX;
        this.cameraY = cameraY;
        this.speed = speed;
        offsetMaxX = worldSizeX - viewportSizeY;
        offsetMaxY = worldSizeY - viewportSizeY;
    }

    public void updateCameraPosition(int playerX, int playerY) {
        cameraX = ((playerX - 52) - viewportSizeX / 2) * speed;
        cameraY = ((playerY - 69) - viewportSizeY / 2) * speed;
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
