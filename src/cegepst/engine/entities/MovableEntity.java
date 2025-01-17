package cegepst.engine.entities;

import cegepst.engine.controls.Direction;
import cegepst.engine.graphics.Buffer;
import cegepst.roomClearingGame.World;

import java.awt.*;

public abstract class MovableEntity extends StaticEntity {

    private int speed = 1;
    private Direction direction = Direction.UP;
    private int lastX = Integer.MIN_VALUE;
    private int lastY = Integer.MIN_VALUE;
    private boolean moved = false;
    private Collision collision;

    public MovableEntity() {
        collision = new Collision(this);
    }

    public void update() {
        moved = false;
    }

    public void move() {
        int allowedDistance = collision.getAllowedSpeed(direction);
        x += direction.calculateVelocityX(allowedDistance);
        y += direction.calculateVelocityY(allowedDistance);
        moved = (x != lastX || y != lastY);
        lastX = x;
        lastY = y;
    }

    public boolean hasMoved() {
        return moved;
    }

    public void setMoved(boolean value) {
        this.moved = value;
    }

    public void move(Direction direction) {
        this.direction = direction;
        move();
    }

    public Rectangle getHitBox() {
        switch (direction) {
            case UP: return getUpperHitBox();
            case DOWN: return getLowerHitBox();
            case LEFT: return getLeftHitBox();
            case RIGHT: return getRightHitBox();
        }
        return getBounds();
    }

    public boolean hitBoxIntersectWith(StaticEntity other) {
        if (other == null) {
            return false;
        }
        return getHitBox().intersects(other.getBounds());
    }

    public void drawHitBox(Buffer buffer) {
        Rectangle rectangle = getHitBox();
        Color color = new Color(255, 0, 0, 200);
        buffer.drawRectangle(rectangle.x, rectangle.y,
                rectangle.width, rectangle.height, color);
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public Direction getDirection() {
        return direction;
    }

    private Rectangle getUpperHitBox() {
        return new Rectangle(x, y - speed, width, speed);
    }

    private Rectangle getLowerHitBox() {
        return new Rectangle(x, y + height, width, speed);
    }

    private Rectangle getLeftHitBox() {
        return new Rectangle(x - speed, y, speed, height);
    }

    private Rectangle getRightHitBox() {
        return new Rectangle(x + width, y, speed, height);
    }
}
