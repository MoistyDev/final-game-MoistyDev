package cegepst.roomClearingGame;

import cegepst.engine.controls.MovementController;

import java.awt.event.KeyEvent;
import java.security.Key;

public class GamePad extends MovementController {

    public GamePad() {
        bindKey(KeyEvent.VK_ESCAPE);
        bindKey(KeyEvent.VK_SPACE, 200);
        bindKey(KeyEvent.VK_R, 200);
    }

    public boolean isQuitPressed() {
        return isKeyPressed(KeyEvent.VK_ESCAPE);
    }

    public boolean isFirePressed() {
        return isKeyPressed(KeyEvent.VK_SPACE);
    }

    public boolean isReloadPressed() {
        return isKeyPressed(KeyEvent.VK_R);
    }
}
