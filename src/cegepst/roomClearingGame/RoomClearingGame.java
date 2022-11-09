package cegepst.roomClearingGame;

import cegepst.engine.graphics.Buffer;
import cegepst.engine.graphics.Camera;
import cegepst.engine.Game;

public class RoomClearingGame extends Game {
    private GamePad gamePad;
    private Mouse mouse;
    private Player player;
    private World world;
    private Camera camera;

    @Override
    protected void initialize() {
        gamePad = new GamePad();
        mouse = new Mouse();
        world = new World();
        player = new Player(gamePad, mouse, world);
        world.load();
        player.load();
        mouse.load();
        camera = new Camera(world.getWidth(), world.getHeight(), 800, 600, 200, 300);
        player.teleport(camera.getCameraX(), camera.getCameraY());
    }

    @Override
    protected void update() {
        if (gamePad.isQuitPressed()) {
            stop();
        }
        player.update();
    }

    @Override
    protected void drawOnBuffer(Buffer buffer) {
        world.draw(buffer, -camera.getCameraX(), -camera.getCameraY());
        player.draw(buffer);
        mouse.drawCursor(buffer);
        camera.updateCameraPosition(player.getX(), player.getY());
    }
}
