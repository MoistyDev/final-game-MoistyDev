package cegepst.roomClearingGame;

import cegepst.engine.graphics.Buffer;

import java.awt.*;

public class Hud {
    public void draw(Buffer buffer, int ammoCount, int health) {
        buffer.setFontSize(30f);
        buffer.drawString("Health : " + health, 100, 500, Color.BLACK);
        buffer.drawString("Health : " + health, 100 - 2, 500 - 2, Color.WHITE);
        buffer.drawString(ammoCount + " / 17", 600, 500, Color.BLACK);
        buffer.drawString(ammoCount + " / 17", 600 - 2, 500 - 2, Color.WHITE);
    }
}
