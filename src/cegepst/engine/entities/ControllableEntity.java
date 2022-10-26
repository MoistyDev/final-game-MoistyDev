package cegepst.engine.entities;

import cegepst.engine.controls.Direction;
import cegepst.engine.controls.MovementController;

public abstract class ControllableEntity extends MovableEntity {

    private final MovementController controller;

    public ControllableEntity(MovementController controller) {
        this.controller = controller;
    }

    public void moveWithController() {
        Direction direction = controller.getMoveDirection();
        if (direction != null) {
            move(direction);
        }
    }
}
