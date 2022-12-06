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
    }

    public void updateCameraPosition(int playerX, int playerY) {
        cameraX = playerX - ((viewportSizeX - worldSizeX) / 2);
        cameraY = playerY - ((viewportSizeY - worldSizeY) / 2);
    }

    public int getCameraX() {
        return cameraX;
    }

    public int getCameraY() {
        return cameraY;
    }
}
