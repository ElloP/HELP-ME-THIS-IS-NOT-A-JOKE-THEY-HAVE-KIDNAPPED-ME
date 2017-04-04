package com.helpme.app.character;

import com.helpme.app.item.Item;
import com.helpme.app.utilities.Coordinate;
import com.helpme.app.utilities.Type;

import static com.helpme.app.utilities.Type.*;

/**
 * Created by kopa on 2017-03-30.
 */
public class Monster {
    protected float hitpoints;
    protected float damage;
    protected Coordinate position;
    protected Direction direction;
    protected int equippedItemIndex;
    protected Item[] inventory;

    public void attack(Monster target) {
        inventory[equippedItemIndex].applyAttackEffect(target);
    }

    public void selfie() {
        inventory[equippedItemIndex].applySelfEffect(this);
    }

    public void move(Direction moveDirection) {
        switch (moveDirection) {
            case NORTH:
                position = position.moveUp();
                break;
            case EAST:
                position = position.moveRight();
                break;
            case SOUTH:
                position = position.moveDown();
                break;
            case WEST:
                position = position.moveLeft();
                break;
        }
    }

    private void rotate(int i) {
        direction = Type.rotateDirection(direction, i);
    }

    public void rotateRight() {
        rotate(1);
    }

    public void rotateLeft() {
        rotate(-1);
    }

    public void setPosition(Coordinate position){
        this.position = position;
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setDirection(Direction direction){
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public Monster copy() {
        Monster monster = new Monster();
        monster.direction = direction;
        monster.position = position;
        monster.hitpoints = hitpoints;
        monster.damage = damage;
        return monster;
    }
}
