package cegepst.finalGame;

import cegepst.engine.controls.MovementController;

import java.awt.event.KeyEvent;

public class GamePad extends MovementController {

    private int quitKey = KeyEvent.VK_Q;
    private int fireKey = KeyEvent.VK_SPACE;
    private int toggleKey = KeyEvent.VK_T;
    private int musicKey = KeyEvent.VK_M;

    public GamePad() {
        bindKey(quitKey);
        bindKey(fireKey, 500);
        bindKey(toggleKey, 2000);
        bindKey(musicKey, 100);
    }

    public boolean isQuitPressed() {
        return isKeyPressed(quitKey);
    }

    public boolean isFirePressed() {
        return isKeyPressed(fireKey);
    }

    public boolean isMusicPressed() {
        return isKeyPressed(musicKey);
    }

    public boolean isTogglePressed() {
        return isKeyPressed(toggleKey);
    }
}
