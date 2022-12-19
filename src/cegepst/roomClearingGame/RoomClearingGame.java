package cegepst.roomClearingGame;

import cegepst.engine.graphics.Buffer;
import cegepst.engine.graphics.Camera;
import cegepst.engine.Game;
import cegepst.engine.graphics.RenderingEngine;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class RoomClearingGame extends Game {
    private GamePad gamePad;
    private Mouse mouse;
    private Player player;
    private ZombieRepository zombies;
    private World world;
    private Camera camera;
    private int round;

    @Override
    protected void initialize() {
        gamePad = new GamePad();
        mouse = new Mouse();
        world = new World();
        player = new Player(gamePad, mouse);
        player.load();
        mouse.load();
        camera = new Camera(800, 600);
        zombies = ZombieRepository.getInstance();
        //zombie = new Zombie(world, player);
        //zombie.teleport(1100, 658);
        //player.getZombies(zombieFactory);
        player.teleport(1000, 600);
        camera.updateCameraPosition(player.getX(), player.getY());
        //RenderingEngine.getInstance().getScreen().fullScreen();
        RenderingEngine.getInstance().getScreen().hideCursor();
        round = 1;
        createZombies();
    }

    @Override
    protected void update() {
        updateInputs();
        updatePlayerActions();
        updateZombieActions();
        if (roundEnded()) {
            startNextRound();
            System.out.println("NEW ROUND : " + round);
        }
    }

    @Override
    protected void drawOnBuffer(Buffer buffer) {
        world.draw(buffer, -camera.getCameraX(), -camera.getCameraY());
        player.draw(buffer);
        camera.updateCameraPosition(player.getX(), player.getY());
        buffer.translate(-player.getX() + 800 / 2, -player.getY() + 600 / 2);
        for (Zombie zombie : zombies) {
            zombie.draw(buffer);
            //System.out.println("ZOMBIE : X - " + zombie.getX() + " Y - " + zombie.getY());
        }
        buffer.translate(player.getX() - 800 / 2, player.getY() - 600 / 2);
        mouse.drawCursor(buffer);
        //System.out.println("CURSOR : X - " + (mouse.getX() + player.getX() - 800 / 2) + " Y - " + (mouse.getY() + player.getY() - 600 / 2));

    }

    private void updatePlayerActions() {
        player.update();
    }

    private void updateZombieActions() {
        for (Iterator<Zombie> it = zombies.iterator(); it.hasNext();) {
            Zombie zombie = it.next();
            if (zombie.checkIfDead()) {
                ZombieRepository.getInstance().unregisterEntity(zombie);
                return;
            }
            zombie.update();
            zombie.tryDealingDamage(player);
        }
    }

    private void startNextRound() {
        round++;
        createZombies();
    }

    private boolean roundEnded() {
        return zombies.count() == 0;
    }

    private void createZombies() {
        switch (round) {
            case 1 :
                for (int i = 0; i < 10; i++) {
                    createZombie();
                }
                break;
            case 2 :
                for (int i = 0; i < 15; i++) {
                    createZombie();
                }
                break;
            case 3 :
                for (int i = 0; i < 20; i++) {
                    createZombie();
                }
                break;
            case 4 :
                for (int i = 0; i < 25; i++) {
                    createZombie();
                }
                break;
            case 5 :
                for (int i = 0; i < 30; i++) {
                    createZombie();
                }
                break;
        }
    }

    private void createZombie() {
        Zombie zombie = new Zombie(world, player);
        zombie.teleport(world.getRandomCoordX(), world.getRandomCoordY());
        zombies.registerZombie(zombie);
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
