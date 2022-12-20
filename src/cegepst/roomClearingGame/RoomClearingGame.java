package cegepst.roomClearingGame;

import cegepst.engine.graphics.Buffer;
import cegepst.engine.graphics.Camera;
import cegepst.engine.Game;
import cegepst.engine.graphics.RenderingEngine;

import java.util.Iterator;

public class RoomClearingGame extends Game {
    private GamePad gamePad;
    private Mouse mouse;
    private Player player;
    private ZombieRepository zombies;
    private World world;
    private Camera camera;
    private Hud hud;
    private int round;
    private boolean gameEnded;
    private int replayCounter = 0;

    @Override
    protected void initialize() {
        if (gamePad == null) {
            gamePad = new GamePad();
        }
        if (mouse == null) {
            mouse = new Mouse();
            mouse.load();
        }
        if (world == null) {
            world = new World();
        }
        if (player == null) {
            player = new Player(gamePad, mouse);
            player.load();
        }
        if (camera == null) {
            camera = new Camera(800, 600);
        }
        if (hud == null) {
            hud = new Hud();
        }
        startGame();
    }

    @Override
    protected void update() {
        hasPlayerWonGame();
        updateInputs();
        updatePlayerActions();
        updateZombieActions();
        if (roundEnded()) {
            startNextRound();
        }
    }

    @Override
    protected void restart() {
        ZombieRepository.killInstance();
        player.resetPlayer();
        replayCounter++;
        initialize();
    }

    @Override
    protected void drawOnBuffer(Buffer buffer) {
        world.draw(buffer, -camera.getCameraX(), -camera.getCameraY());
        if (!player.isDead()) {
            player.draw(buffer);
        }
        camera.updateCameraPosition(player.getX(), player.getY());
        buffer.translate(-player.getX() + 800 / 2, -player.getY() + 600 / 2);
        for (Zombie zombie : zombies) {
            zombie.draw(buffer);
        }
        buffer.translate(player.getX() - 800 / 2, player.getY() - 600 / 2);
        if (player.isDead()) {
            gameOver(buffer);
        }
        if (gameEnded) {
            wonGame(buffer);
        }
        mouse.drawCursor(buffer);
        if (!player.isDead() && !gameEnded) {
            hud.draw(buffer, player.getPistolAmmo(), player.getHealth());
        }
    }

    private void hasPlayerWonGame() {
        if (zombies.count() == 0 && round == 5) {
            gameEnded = true;
        }
    }

    private void gameOver(Buffer buffer) {
        Sound.THEME.stop();

        buffer.drawEndingScreen("GAME OVER", mouse);
        checkGameOptions(260, 315, "restart");
        checkGameOptions(365, 410, "quit");
    }

    private void wonGame(Buffer buffer) {
        buffer.drawEndingScreen("YOU WON", mouse);
        checkGameOptions(260, 315, "restart");
        checkGameOptions(365, 410, "quit");
    }

    private void startGame() {
        zombies = ZombieRepository.getInstance();
        player.teleport(1000, 600);
        camera.updateCameraPosition(player.getX(), player.getY());
        RenderingEngine.getInstance().getScreen().fullScreen();
        RenderingEngine.getInstance().getScreen().hideCursor();
        round = 1;
        gameEnded = false;
        createZombies();
        if (replayCounter == 0) {
            Sound.THEME.play(false);
        }
    }

    private void checkGameOptions(int optionMinHeight, int optionMaxHeight, String option) {
        if (mouse.getY() <= optionMaxHeight && mouse.getY() >= optionMinHeight) {
            if (gamePad.isConfirmPressed()) {
                if (option.equalsIgnoreCase("restart")) {
                    restart();
                } else if (option.equalsIgnoreCase("quit")) {
                    stop();
                }
            }
        }
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
        if (round == 5) {
            Sound.THEME.stop();
        } else {
            Sound.ROUND_CHANGE.play(false);
        }
        createZombies();
    }

    private boolean roundEnded() {
        return zombies.count() == 0;
    }

    private void createZombies() {
        switch (round) {
            case 1 -> {
                for (int i = 0; i < 10; i++) {
                    createZombie();
                }
            }
            case 2 -> {
                for (int i = 0; i < 15; i++) {
                    createZombie();
                }
            }
            case 3 -> {
                for (int i = 0; i < 20; i++) {
                    createZombie();
                }
            }
            case 4 -> {
                for (int i = 0; i < 25; i++) {
                    createZombie();
                }
            }
            case 5 -> {
                Sound.FINAL_ROUND_CHANGE.play(false);
                for (int i = 0; i < 30; i++) {
                    createZombie();
                }
                Sound.FINAL_ROUND.play(true);
            }
        }
    }

    private void createZombie() {
        Zombie zombie = new Zombie(player);
        int randomX = world.getRandomCoordX();
        int randomY = world.getRandomCoordY();
        for (Boundary boundary : world.getBoundaries()) {
            if (randomX <= boundary.getX() && randomX >= boundary.getX() + boundary.getWidth() && randomY <= boundary.getY() && randomY >= boundary.getY() + boundary.getHeight()) {
                do {
                    randomX = world.getRandomCoordX();
                    randomY = world.getRandomCoordY();
                } while (randomX <= boundary.getX() && randomX >= boundary.getX() + boundary.getWidth() && randomY <= boundary.getY() && randomY >= boundary.getY() + boundary.getHeight());
            }
        }
        zombie.teleport(randomX, randomY);
        zombies.registerZombie(zombie);
    }

    private void updateInputs() {
        if (gamePad.isQuitPressed()) {
            stop();
        }
        if (!player.isDead() && !gameEnded) {
            if (gamePad.isFirePressed() && player.getPistolAmmo() != 0) {
                Sound.PISTOL_FIRE.play(false);
                player.setIsShooting(true);
                player.lowerPistolAmmo();
            }

            if (gamePad.isReloadPressed()) {
                Sound.PISTOL_RELOAD.play(false);
                player.setIsReloading(true);
                player.resetPistolAmmo();
            }
        }
    }
}
