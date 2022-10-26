package cegepst.engine.controls;

import cegepst.engine.GameTime;
import cegepst.engine.graphics.RenderingEngine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public abstract class Controller implements KeyListener {

    private final HashMap<Integer, Boolean> pressedKeys;
    private final HashMap<Integer, Integer> cooldownKeys;
    private final HashMap<Integer, Long> keyLastUsages;

    public Controller() {
        pressedKeys = new HashMap<>();
        cooldownKeys = new HashMap<>();
        keyLastUsages = new HashMap<>();
        RenderingEngine.getInstance().addKeyListener(this);
    }

    protected void bindKey(int keyCode, int waitMs) {
        pressedKeys.put(keyCode, false);
        cooldownKeys.put(keyCode, waitMs);
        keyLastUsages.put(keyCode, 0L);
    }

    protected void bindKey(int keyCode) {
        pressedKeys.put(keyCode, false);
    }

    protected void clearKeys() {
        pressedKeys.clear();
        cooldownKeys.clear();
        keyLastUsages.clear();
    }

    protected void removeKey(int keyCode) {
        pressedKeys.remove(keyCode);
        cooldownKeys.remove(keyCode);
        keyLastUsages.remove(keyCode);
    }

    protected boolean isKeyPressed(int keyCode) {
        boolean pressed = pressedKeys.containsKey(keyCode) && pressedKeys.get(keyCode);
        boolean hasCooldownSettings = cooldownKeys.containsKey(keyCode);
        if (!hasCooldownSettings) {
            return pressed;
        }

        if (pressed) {
            int msBeforeTrigger = cooldownKeys.get(keyCode);
            if (GameTime.getCurrentTime() - keyLastUsages.get(keyCode) >= msBeforeTrigger) {
                keyLastUsages.put(keyCode, GameTime.getCurrentTime());
                return true;
            }
        }

        return false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (pressedKeys.containsKey(keyCode)) {
            pressedKeys.put(keyCode, true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (pressedKeys.containsKey(keyCode)) {
            pressedKeys.put(keyCode, false);
        }
    }
}
