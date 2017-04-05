package com.helpme.app.character;

import com.helpme.app.item.Item;
import com.helpme.app.utils.Vector2f;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Monster {
    private Item[] inventory;
    private Vector2f position;
    private Vector2f direction;

    public Monster(Item[] inventory, Vector2f position, Vector2f direction) {
        this.inventory = inventory;
        this.position = position;
        this.direction = direction;
    }

    public void setPosition(Vector2f position) {
        this.position = position.toInt();
    }

    public void rotateRight() {
        direction = direction.rotateRightAngle(1);
    }

    public void rotateLeft() {
        direction = direction.rotateRightAngle(-1);
    }

    private void move(Vector2f direction) {
        position = Vector2f.add(position, direction);
    }

    public void moveForward() {
        move(direction.forward());
    }

    public void moveRight() {
        move(direction.right());
    }

    public void moveBackward() {
        move(direction.backward());
    }

    public void moveLeft() {
        move(direction.left());
    }

    public Vector2f getPosition() {
        return position.clone();
    }

    public Vector2f getDirection() {
        return direction.clone();
    }

    @Override
    public Monster clone() {
        return new Monster(inventory, position, direction);
    }

}
