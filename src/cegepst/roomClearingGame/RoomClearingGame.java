package cegepst.roomClearingGame;

import cegepst.engine.graphics.Buffer;
import cegepst.engine.graphics.Camera;
import cegepst.engine.Game;
import cegepst.engine.graphics.RenderingEngine;

import java.awt.*;
import java.util.ArrayList;

public class RoomClearingGame extends Game {
    private GamePad gamePad;
    private Mouse mouse;
    private Player player;
    private Zombie zombie;
    private World world;
    private Camera camera;

    @Override
    protected void initialize() {
        gamePad = new GamePad();
        mouse = new Mouse();
        world = new World();
        player = new Player(gamePad, mouse);
        zombie = new Zombie();
        player.load();
        mouse.load();
        camera = new Camera(world.getWidth(), world.getHeight(), 800, 600, 500, 3000);
        player.teleport(camera.getCameraX(), camera.getCameraY());
        zombie.teleport(1396, 658);
        //RenderingEngine.getInstance().getScreen().fullScreen();
        RenderingEngine.getInstance().getScreen().hideCursor();
    }

    @Override
    protected void update() {
        updateInputs();
        player.update();
        zombie.update();
    }

    @Override
    protected void drawOnBuffer(Buffer buffer) {
        world.draw(buffer, -camera.getCameraX(), -camera.getCameraY());
        player.draw(buffer);
        camera.updateCameraPosition(player.getX(), player.getY());
        buffer.translate(-player.getX(), -player.getY());
        //System.out.println("x : " + player.getX() + " y : " + player.getY());
        zombie.draw(buffer);
        buffer.translate(player.getX(), player.getY());
        mouse.drawCursor(buffer);
    }

    private void updateInputs() {
        if (gamePad.isQuitPressed()) {
            stop();
        }
        if (gamePad.isFirePressed() && player.getPistolAmmo() != 0) {
            Sound.PISTOL_FIRE.play();
            player.setIsShooting(true);
            player.lowerPistolAmmo();
        }

        if (gamePad.isReloadPressed()) {
            Sound.PISTOL_RELOAD.play();
            player.setIsReloading(true);
            player.resetPistolAmmo();
        }
    }
}
