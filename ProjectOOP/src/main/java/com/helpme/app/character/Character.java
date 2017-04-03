package com.helpme.app.character;

import com.helpme.app.item.Item;
import com.helpme.app.tile.Tile;
import com.helpme.app.utilities.Coordinate;

import static com.helpme.app.utilities.Type.*;

/**
 * Created by kopa on 2017-03-30.
 */
public abstract class Character {
    protected float hitpoints;
    protected float damage;
    protected Coordinate position;
    protected Direction direction;
    protected int equippedItemIndex;
    protected Item[] inventory;

    public void attack(Character target) {
        inventory[equippedItemIndex].applyAttackEffect(target);
    }

    public void selfie() {
        inventory[equippedItemIndex].applySelfEffect(this);
    }

    public void move(Direction moveDirection) {
        switch (moveDirection) {
            case NORTH:
                position.moveUp();
                break;
            case EAST:
                position.moveRight();
                break;
            case SOUTH:
                position.moveDown();
                break;
            case WEST:
                position.moveLeft();
                break;
        }
    }

    private void rotate(int i) {
        Direction[] directions = Direction.values();
        int length = directions.length;
        direction = directions[(direction.ordinal() + i) % length];
    }

    public void rotateRight() {
        rotate(1);
    }

    public void rotateLeft() {
        rotate(-1);
    }

    public Coordinate getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }
}
