package cegepst.roomClearingGame;

import cegepst.engine.controls.Direction;
import cegepst.engine.entities.CollidableRepository;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.graphics.Buffer;

import java.awt.*;

public class Bullet extends MovableEntity {
    private Direction playerDirection;

    public Bullet(Player player) {
        setSpeed(5);
        playerDirection = player.getDirection();
        setBulletDirection(player);
        CollidableRepository.getInstance().registerEntity(this);
    }

    @Override
    public void update() {
        move(playerDirection);
        if (x >= 820) {
            x = -20;
        }
        if (y >= 620) {
            y = -20;
        }
    }


    @Override
    public void draw(Buffer buffer) {
        buffer.drawRectangle(x, y, width, height, Color.YELLOW);
    }

    private void setBulletDirection(Player player) {
        if (playerDirection == Direction.RIGHT) {
            teleport(800 / 2 + player.getWidth() + 1,
                    600 / 2 + 15 - 2);
            setDimension(8, 4);
        } else if (playerDirection == Direction.LEFT) {
            teleport(800 / 2 - 9, 600 / 2 + 15 - 2);
            setDimension(8, 4);
        } else if (playerDirection == Direction.UP) {
            teleport(800 / 2 + 15 - 2, 600 / 2 - 9);
            setDimension(4, 8);
        } else if (playerDirection == Direction.DOWN) {
            teleport(800 / 2 + 15 - 2,
                    600 / 2 + player.getHeight() + 1);
            setDimension(4, 8);
        }
    }
}
