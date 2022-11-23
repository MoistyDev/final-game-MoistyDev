package cegepst.roomClearingGame;

import cegepst.engine.entities.CollidableRepository;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.graphics.Buffer;

import java.awt.*;

public class Boundary extends StaticEntity {

    public Boundary() {
        CollidableRepository.getInstance().registerEntity(this);
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawRectangle(x, y, width, height, new Color(255, 0, 0, 100));
    }
}
