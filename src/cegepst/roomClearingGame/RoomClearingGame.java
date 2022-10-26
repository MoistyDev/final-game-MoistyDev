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
        player = new Player(gamePad, mouse);
        world = new World();
        world.load();
        player.load();
        mouse.load();
        camera = new Camera(world.getWidth(), world.getHeight(), 800, 600, 800 / 2, 600 / 2, 5);
        player.teleport(200, 300);
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
        camera.updateCameraPosition(player.getX(), player.getY());
        world.draw(buffer, -camera.getCameraX(), -camera.getCameraY());
        player.draw(buffer);
        mouse.drawCursor(buffer);
    }
}
