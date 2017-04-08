package com.helpme.app.character;

import com.helpme.app.item.IItem;
import com.helpme.app.tile.edge.IEdge;
import com.helpme.app.utils.Vector2f;

/**
 * Created by kopa on 2017-04-08.
 */
public interface IMonster {
    void rotateRight();
    void rotateLeft();
    void moveForward();
    void moveRight();
    void moveBackward();
    void moveLeft();
    void setPosition(Vector2f position);
    boolean traverse(IEdge edge);
    Vector2f getPosition();
    Vector2f getDirection();
    IItem[] getInventory();
    Monster clone();
}
