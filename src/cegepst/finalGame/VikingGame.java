package cegepst.finalGame;

import cegepst.engine.graphics.Buffer;
import cegepst.engine.Game;
import cegepst.engine.graphics.RenderingEngine;

public class VikingGame extends Game {

    private GamePad gamePad;
    private Player player;
    private World world;
    private Tree tree;
    private Hud hud;

    @Override
    protected void initialize() {
        gamePad = new GamePad();
        hud = new Hud();
        player = new Player(gamePad);
        player.load();
        world = new World();
        world.load();
        player.teleport(200, 200);
        tree = new Tree(300, 350);

        Music.WORLD_BACKGROUND.play();

        //RenderingEngine.getInstance().getScreen().fullScreen();
        RenderingEngine.getInstance().getScreen().hideCursor();
    }

    @Override
    protected void update() {
        updateInputs();
        player.update();
        if (player.getY() < tree.getY() + 52) {
            tree.blockadeFromTop();
        } else {
            tree.blockadeFromBottom();
        }
    }

    @Override
    protected void drawOnBuffer(Buffer buffer) {
        world.draw(buffer);
        // 80 (tree height) - 28 (max for layer switch)
        if (player.getY() < tree.getY() + 52) {
            player.draw(buffer);
            tree.draw(buffer);
        } else {
            tree.draw(buffer);
            player.draw(buffer);
        }

        hud.draw(buffer);
    }

    private void updateInputs() {
        if (gamePad.isQuitPressed()) {
            stop();
        }
        if (gamePad.isTogglePressed()) {
            RenderingEngine.getInstance().getScreen().toggleFullScreen();
        }
        if (gamePad.isMusicPressed()) {
            Music.WORLD_BACKGROUND.toggle();
        }
        if (gamePad.isFirePressed()) {
            Sound.FIRE.play();
        }
    }
}
