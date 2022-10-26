package cegepst.roomClearingGame;

import cegepst.engine.controls.MovementController;

import java.awt.event.KeyEvent;

public class GamePad extends MovementController {

    public GamePad() {
        bindKey(KeyEvent.VK_ESCAPE);
    }

    public boolean isQuitPressed() {
        return isKeyPressed(KeyEvent.VK_ESCAPE);
    }
}
