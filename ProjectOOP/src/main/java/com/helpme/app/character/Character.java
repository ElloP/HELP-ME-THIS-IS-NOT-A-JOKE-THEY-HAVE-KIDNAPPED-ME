package com.helpme.app.character;

import com.helpme.app.item.Item;
import com.helpme.app.utils.Vector2f;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Character {
    private Item[] inventory;
    private Vector2f position;
    private Vector2f direction;

    private final float RIGHT_ANGLE = (float) Math.PI / 2;

    public void rotateRight() {
        Vector2f.rotateI(direction, -RIGHT_ANGLE);
    }

    public void rotateLeft() {
        Vector2f.rotateI(direction, RIGHT_ANGLE);
    }

    private void move(Vector2f direction) {
        Vector2f.add(position, direction);
    }

    public void moveForward() {
        move(direction);
    }

    public void moveRight() {
        move(Vector2f.rotateI(direction, RIGHT_ANGLE));
    }

    public void moveBackward() {
        move(Vector2f.rotateI(direction, RIGHT_ANGLE * 2));
    }

    public void moveLeft() {
        move(Vector2f.rotateI(direction, RIGHT_ANGLE * 3));
    }

}
