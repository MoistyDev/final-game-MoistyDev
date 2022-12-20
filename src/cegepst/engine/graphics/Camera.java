package cegepst.engine.graphics;

public class Camera {
    private int viewportSizeX = 0;
    private int viewportSizeY = 0;
    private int cameraX;
    private int cameraY;

    public Camera(int viewportSizeX, int viewportSizeY) {
        this.viewportSizeX = viewportSizeX;
        this.viewportSizeY = viewportSizeY;
    }

    public void updateCameraPosition(int playerX, int playerY) {
        cameraX = playerX;
        cameraY = playerY;
    }

    public int getCameraX() {
        return cameraX;
    }

    public int getCameraY() {
        return cameraY;
    }
}
