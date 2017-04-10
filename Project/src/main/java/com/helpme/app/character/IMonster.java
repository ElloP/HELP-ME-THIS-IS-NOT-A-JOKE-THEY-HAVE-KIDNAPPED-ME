package com.helpme.app.character;

import com.helpme.app.item.IItem;
import com.helpme.app.tile.edge.IEdge;
import com.helpme.app.utils.Vector2f;

/**
 * Created by Jacob on 2017-04-08.
 */
public interface IMonster extends IStats{
    void rotateRight();
    void rotateLeft();
    void moveForward();
    void moveRight();
    void moveBackward();
    void moveLeft();
    void setPosition(Vector2f position);
    void changeActiveItem(int itemIndex);
    void attack(IStats target);
    void selfie();
    Vector2f targetTile();
    boolean traverse(IEdge edge);
    Vector2f getPosition();
    Vector2f getDirection();
    Monster clone();
}
