package cegepst.finalGame;

import cegepst.engine.graphics.Buffer;
import cegepst.engine.entities.CollidableRepository;
import cegepst.engine.entities.StaticEntity;

import java.awt.*;

public class Blockade extends StaticEntity {

    public Blockade() {
        CollidableRepository.getInstance().registerEntity(this);
    }

    @Override
    public void draw(Buffer buffer) {

    }
}
